<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/bootstrap/css/custom.css" rel="stylesheet">
</head>
<body>
<div class="container c-top70">
    <div class="row">
        <div class="col-md-offset-3 col-md-2">
            <img src="" width="150" height="200" alt="Product image" class="img-rounded">
        </div>
        <div class="col-md-3">
            <strong>
                ${requestScope.product.title}
            </strong><br>
            <small>
                by ${requestScope.product.author}
            </small>
            <br><br>
            <strong>
                Price: $${requestScope.product.price}
            </strong><br>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <br>
            <strong>
                <h3>Product Description</h3>
            </strong><br>
            <p>
                <% request.setAttribute("newLine", "\n");%>
                    <c:set var="description" value="${requestScope.product.description}"/>
                    <c:set var="list" value="${fn:split(description, newLine)}"/>
                    <c:forEach var="item" items="${list}">
                        <c:out value="${item}"/><br>
                    </c:forEach>
            </p><br>
            <strong>
                <h3>Product Details</h3>
            </strong><br>
            <ul class="list-unstyled">
                <li>
                    <c:set var="details" value="${requestScope.product.details}"/>
                    <c:set var="list" value="${fn:split(details, newLine)}"/>
                    <c:forEach var="item" items="${list}">
                        <c:out value="${item}"/><br>
                    </c:forEach>
                </li>
            </ul>
            <strong>
                <h3>Editorial Reviews</h3>
            </strong><br>
            <p>
                <c:set var="aboutAuthor" value="${requestScope.product.aboutAuthor}"/>
                    <c:set var="list" value="${fn:split(aboutAuthor, newLine)}"/>
                    <c:forEach var="item" items="${list}">
                        <c:out value="${item}"/><br>
                    </c:forEach>
            </p><br>
        </div>
    </div>
</div>
</body>
</html>