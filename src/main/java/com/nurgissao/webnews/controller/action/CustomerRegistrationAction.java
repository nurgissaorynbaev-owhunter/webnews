package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.model.entity.User;
import com.nurgissao.webnews.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class CustomerRegistrationAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            CustomerDAO customerDAO = daoFactory.getCustomerDAO();
            UserDAO userDAO = daoFactory.getUserDAO();
            Validator validator = new Validator();

            String fullName = req.getParameter("fullName");
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String homeAddress = req.getParameter("homeAddress");
            String phoneNumber = req.getParameter("phoneNumber");
            String email = req.getParameter("email");

            String userFirstName = req.getParameter("userFirstName");
            String userLastName = req.getParameter("userLastName");
            String userEmail = req.getParameter("userEmail");

            if (userFirstName != null && userEmail != null) {
                fullName = userFirstName + " " + userLastName;
                email = userEmail;
            }
            Map<String, String> formValue = new HashMap<>();
            formValue.put("fullName", fullName);
            formValue.put("email", email);
            formValue.put("country", country);
            formValue.put("city", city);
            formValue.put("homeAddress", homeAddress);
            formValue.put("phoneNumber", phoneNumber);

            Map<String, String> violations = validator.validateCustomerRegistrationForm(formValue);
            if (!violations.isEmpty()) {
                //TODO throw appropriate Exception

            } else {
                Customer customer = new Customer();
                customer.setFullName(fullName);
                customer.setCountry(country);
                customer.setCity(city);
                customer.setHomeAddress(homeAddress);
                customer.setPhoneNumber(phoneNumber);
                customer.setEmail(email);

                Customer tCustomer = customerDAO.create(customer);
                if (tCustomer != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("customer", tCustomer);
                    User user = (User) session.getAttribute("user");
                    user.setCustomerId(tCustomer.getId());

                    userDAO.update(user);

                } else {
                    //TODO throw appropriate Exception
                }
            }

            String signedUser = req.getParameter("signedUser");
            System.out.println("signedUser:" + signedUser);
            if (signedUser != null) {
                return "home";
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "showProductOrder";
    }
}