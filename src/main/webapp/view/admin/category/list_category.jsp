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
    <title>Document</title>
</head>
<body>
<div class="container mt-4">
    <ul class="list-group">
        <li class="list-group-item">
            <a href="./categories/add">Thêm danh mục mới </a>
        </li>
    </ul>
    <div class="mb-4 mt-4">
        <h5>Danh sách danh mục</h5>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Tên danh mục</th>
            <th scope="col">Slug</th>
            <th scope="col">Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
        <tr>
            <th scope="row">${category.getCategoryName()}</th>
            <td>${category.getSlug()}</td>
            <td>
                <c:if test="${category.getStatus() == 0}">
                    <a class="btn btn-success">active</a>
                </c:if>
                <c:if test="${category.getStatus() == 1}">
                    <a  class="btn btn-danger">un-active</a>
                </c:if>
                <a href="./categories/edit/${category.getId()}" class="btn btn-primary ml-4">Chỉnh sửa</a>
                <c:if test="${uStatus ==2}">
                <a href="./categories/delete/${category.getId()}" class="btn btn-warning ml-4">Xóa</a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>