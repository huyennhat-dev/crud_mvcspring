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
            <th scope="col">STT</th>
            <th scope="col">Tên danh mục</th>
            <th scope="col">Slug</th>
            <th class="col-2" scope="col">Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}" varStatus="key">
            <tr>
                <td>${key.index+1}</td>
                <td scope="row">${category.getCategoryName()}</td>
                <td>${category.getSlug()}</td>
                <td class="text-center">
                    <c:if test="${category.getStatus() == 0}">
                        <a class="btn btn-success">
                            <i class="ti-face-smile"></i>
                        </a>
                    </c:if>
                    <c:if test="${category.getStatus() == 1}">
                        <a class="btn btn-danger">
                            <i class="ti-face-sad"></i>
                        </a>
                    </c:if>
                    <a href="./categories/edit/${category.getId()}" class="btn btn-primary">
                        <i class="ti-pencil"></i>
                    </a>
                    <c:if test="${uStatus ==2}">
                        <a href="./categories/delete/${category.getId()}" class="btn btn-warning ">
                            <i class="ti-trash"></i>
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>