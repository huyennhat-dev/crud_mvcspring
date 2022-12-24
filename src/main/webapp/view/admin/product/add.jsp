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
    <%--@elvariable id="product" type=""--%>
    <form:form method="POST" action="/admin/products/add" modelAttribute="product" enctype="multipart/form-data">
        <div class="form-group">
            <form:label path="productName">Tên sản phẩm</form:label>
            <form:input type="text" class="form-control" path="productName" placeholder="Nhập tên sản phẩm..."/>
        </div>
        <div class="form-group">
            <label>Hình ảnh</label>
            <input type="file" name="image" class="form-control-file" />
        </div>
        <div class="form-group">
            <form:label path="categoryID">Danh mục</form:label>
            <form:select path="categoryID" class="form-control">
                <c:forEach var="category" items="${categories}">
                    <form:option value="${category.getId()}">
                        ${category.getCategoryName()}
                    </form:option>
                </c:forEach>
            </form:select>
        </div>
        <div class="form-group">
            <form:label path="description">Mô tả sản phẩm</form:label>
            <form:textarea class="form-control" path="description"></form:textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
</body>
</html>