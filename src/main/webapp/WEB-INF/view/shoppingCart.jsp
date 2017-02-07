<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container c-wrapper">
    <table class="table">
        <div class="row">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th></th>
            </tr>
        </div>
        <c:forEach var="product" items="${requestScope.products}">
            <div class="row">
                <tr>
                    <td><c:out value="${product.title}"/></td>
                    <td>$<c:out value="${product.price}"/></td>
                    <td>
                        <c:set var="pQuantity" value="${requestScope.productQuantity}"/>
                        <c:set var="productId" value="${product.id}"/>
                        <form action="/pages/shoppingCart" method="post">
                            <input type="number" min="0" style="width: 45px;" name="pQuantity" value="${pQuantity[productId]}">
                            <input type="hidden" name="productId" value="${productId}">
                            <input type="submit" class="btn btn-primary btn-xs" value="save">
                        </form>
                    </td>
                    <td>
                        <form action="/pages/shoppingCart" method="post">
                            <input type="hidden" name="deleteProductId" value="${product.id}">
                            <input type="submit" class="btn btn-primary btn-xs" value="Delete">
                        </form>
                    </td>
                </tr>
            </div>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
