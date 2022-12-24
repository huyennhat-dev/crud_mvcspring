<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <%--@elvariable id="category" type=""--%>
    <form:form method="POST" action="/admin/categories/edit/${category.getId()}" modelAttribute="category">
        <div class="form-group">
            <form:label path="categoryName">Tên danh mục</form:label>
            <form:input type="text" class="form-control"  path="categoryName" value="${category.getCategoryName()}"  placeholder="Nhập tên danh mục..."/>
        </div>
        <div class="form-group">
            <form:select path="status" class="form-control">
                <form:option value="0">Active</form:option>
                <form:option value="1">Un-active</form:option>
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
</body>
</html>