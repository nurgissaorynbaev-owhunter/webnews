package com.nurgissao.webnews.model.dao;

public class H2DAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new H2UserDAO();
    }
}