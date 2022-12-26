<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
                <meta http-equiv="X-UA-Compatible" content="ie=edge">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
                <title>Document</title>
            </head>

            <body>
                <div class="container mt-4">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <a href="./admin/categories">Quản lý danh mục</a>
                        </li>
                        <li class="list-group-item">
                            <a href="./admin/products">Quản lý sản phẩm</a>
                        </li>
                        <c:if test="${uStatus>=1}">
                            <li class="list-group-item">
                                <a href="./admin/accounts">Quản lý tài khoản</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </body>

            </html>