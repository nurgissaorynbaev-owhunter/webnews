<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <h1>My orders</h1>
    <table class="table c-top10">
        <div class="row">
            <tr>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
        </div>
        <c:set var="productQuantityMap" value="${sessionScope.productQuantity}"/>
        <c:forEach var="product" items="${sessionScope.products}">
            <div class="row">
                <tr>
                    <td>
                        <small style="color: green">${product.title}</small>
                    </td>
                    <td>
                        <small style="color: green">$${product.price}</small>
                    </td>
                    <td>
                        <small style="color: green">${productQuantityMap[product.id]}</small>
                    </td>
                </tr>
            </div>
        </c:forEach>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
