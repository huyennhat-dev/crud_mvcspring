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
                <link href="<c:url value=" ../assets/themify-icons/themify-icons.css " />" rel="stylesheet">
                <title>Document</title>
            </head>

            <body>

                <div class="container mt-4">
                    <nav aria-label="breadcrumb " class="d-flex justify-content-between">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="/">Home</a></li>
                            <li class="breadcrumb-item active"><a>${category}</a></li>
                        </ol>
                        <div>
                            <a href="" class="text-primary">
                                <i class="ti-shopping-cart"></i> Giỏ hàng
                            </a>
                        </div>
                    </nav>
                    <div class="row ">
                        <div class="col col-4">
                            <img src="${product.getProductPhoto()}" alt="" class="img-thumbnail">
                        </div>
                        <div class="col col-8">
                            <div class="">
                                <div class="card-body">
                                    <h5 class="card-title">${product.getProductName()}</h5>
                                    <h6 class="card-subtitle  mb-2 text-danger" style="font-size: 16px">${product.getPrice()} đ</h6>
                                    <%--@elvariable id="product" type=""--%>
                                        <form:form method="POST" action="./${product.getId()}" modelAttribute="product">
                                            <form:input path="id" value="${product.getId()}" type="hidden" />
                                            <button type="submit" class="btn btn-outline-primary mt-4">Thêm vào giỏ hàng</button>
                                        </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-4">
                        ${product.getDescription()}
                    </div>
                </div>
            </body>

            </html>