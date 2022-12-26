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
    <link href="<c:url value=" ../../../assets/themify-icons/themify-icons.css " />" rel="stylesheet">
    <title>Document</title>
</head>

<body>
<div class="container mt-4">
    <div class="mb-4 mt-4">
        <h5>Danh sách Admin</h5>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Tên tài khoản</th>
            <th scope="col">Email</th>
            <th scope="col">Chức vụ</th>
            <c:if test="${uStatus == 2}">
            <th class="col-4" scope="col">Action</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="account" items="${accounts}" varStatus="key">
            <tr>
                <td>${key.index+1}</td>
                <td scope="row">${account.getUsername()}</td>
                <td scope="row">${account.getEmail()}</td>
                <td>
                    <c:if test="${account.getStatus() == 0}">
                        Người dùng
                    </c:if>
                </td>
                <c:if test="${uStatus == 2}">
                <td class="text-center">
                    <a href="./user/up/${account.getId()}" class="btn btn-primary"> Bổ nhiệm làm quản lý
                        <i class="ti-arrow-circle-up"></i>
                    </a>
                </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

</html>