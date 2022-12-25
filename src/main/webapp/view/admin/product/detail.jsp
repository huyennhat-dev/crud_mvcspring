<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="<c:url value=" ../../../assets/themify-icons/themify-icons.css " />" rel="stylesheet">
    <title>Document</title>
</head>

<body>
<div class="container mt-4">

    <div class="form-group">
        <label>Tên sản phẩm</label>
        <div class="form-control">${product.getProductName()}</div>

    </div>
    <div class="form-group">
        <label>Hình ảnh</label>
        <img src="${product.getProductPhoto()}" class="img-thumbnail ml-5" style="height: 120px">

    </div>

    <div class="form-group">
        <label>Giá sản phẩm</label>
        <div class="form-control">${product.getPrice()}</div>

    </div>
    <div class="form-group">
        <label>Số lượng trong kho</label>
        <div class="form-control">${product.getQuantity()}</div>
    </div>
    <div class="form-group">
        <label>Số lượng đã bán</label>
        <div class="form-control">${product.getQuantityPurchased()}</div>
    </div>
    <div class="form-group">
        <label>Danh mục</label>
        <div class="form-control">${category.getCategoryName()}</div>
    </div>
    <div class="form-group">
        <label>Mô tả sản phẩm</label>
        <div class="form-control" > ${product.getDescription()}</div>
    </div>
</div>
</body>

</html>