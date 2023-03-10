<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                <meta http-equiv="X-UA-Compatible" content="ie=edge">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
                <link href="<c:url value=" ../../../assets/themify-icons/themify-icons.css " />" rel="stylesheet">
                <title>Document</title>
            </head>

            <body>
                <div class="container mt-4">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="./products/add">Thêm danh sản phẩm mới </a>
                        </li>
                    </ul>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col ">STT</th>
                                <th class="col-1" scope="col">Hình ảnh</th>
                                <th class="col-2" scope="col">Tên sản phẩm</th>
                                <th class="" scope="col">Tên danh mục</th>
                                <th class="" scope="col">Giá sản phẩm</th>
                                <th class="" scope="col">Trong kho</th>
                                <th class="" scope="col">Đã bán</th>
                                <th class="col-3" scope="col">Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${products}" varStatus="key">
                                <tr>
                                    <td scope="row">
                                        ${key.index+1}
                                    </td>
                                    <td scope="row">
                                        <img src="${product.getProductPhoto()}" height="70">
                                    </td>
                                    <td>${product.getProductName()}</td>
                                    <c:forEach var="category" items="${categories}">
                                        <c:if test="${category.getId() == product.getCategoryID()}">
                                            <td>${category.getCategoryName()}</td>
                                        </c:if>
                                    </c:forEach>

                                    <td>${product.getPrice()}</td>
                                    <td>${product.getQuantity()}</td>
                                    <td>${product.getQuantityPurchased()}</td>
                                    <td class="text-center">
                                        <c:if test="${product.getStatus() == 0}">
                                            <a class="btn btn-success">
                                                <i class="ti-face-smile"></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${product.getStatus() == 1}">
                                            <a class="btn btn-warning">
                                                <i class="ti-face-sad"></i>
                                            </a>
                                        </c:if>
                                        <a href="./products/${product.getId()}" class="btn btn-info"><i class="ti-eye"></i></a>
                                        <a href="./products/edit/${product.getId()}" class="btn btn-primary"><i class="ti-pencil"></i></a>
                                        <c:if test="${uStatus ==2}">
                                            <a  href="./products/delete/${product.getId()}" class="btn btn-danger"><i class="ti-trash"></i></a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </body>

            </html>