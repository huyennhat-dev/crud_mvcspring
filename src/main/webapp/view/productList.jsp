<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <body>
        <h1>Product List</h1>
        <table class="table table-dark">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">CategoryID</th>
                    <th scope="col">ProductName</th>
                    <th scope="col">price</th>
                    <th scope="col">Description</th>
                    <th scope="col">Actions</th>
                </tr>
            </thead>
            <thead>
                <c:forEach var="products" items="${products}">
                    <tr>
                        <td scope="row">${products.getProductID()}</td>
                        <td >${products.getCategoryID()}</td>
                        <td >${products.getProductName()}</td>
                        <td >${products.getPrice()}</td>
                        <td>${products.getDescription()}</td>
                        <td><a href="../edit-product/${products.getProductID()}">
                            Edit product
                        </a> </td>

                    </tr>
                </c:forEach>
            </thead>
        </table>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
    </body>

    </html>