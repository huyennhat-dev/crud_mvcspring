<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <meta http-equiv="X-UA-Compatible" content="ie=edge">
            <meta name="Description" content="Enter your description here" />
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
            <title>Title</title>
        </head>

        <body class="m-0">
            <p>Do you really want to assign product:
                <strong>${product.getProductName()}</strong> to other category ?
            </p>
            <%--@elvariable id="products" type=""--%>
                <form:form method="POST" class="form-group" action="/products/update/${product.getProductID()}" modelAttribute="products">

                    <form:input class="form-control" path="productName" type="text" value="${product.getProductName()}"></form:input>
                    <form:errors class="text-dange" path="productName"></form:errors>
                    <form:select class="form-control " path="categoryID">
                        <c:forEach var="category" items="${categories}">
                            <form:option value="${category.getCategoryID()}">${category.getCategoryName()}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:input class="form-control" path="description" type="text" value="${product.getDescription()}"></form:input>
                    <form:errors class="text-danger" path="description"></form:errors>

                    <form:input class="form-control" path="price" type="text" value="${product.getPrice()}"></form:input>
                    <form:errors class="text-danger " path="price"></form:errors>


                    <form:button type="submit" class="btn btn-primary">Update</form:button>
                </form:form>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
        </body>

        </html>