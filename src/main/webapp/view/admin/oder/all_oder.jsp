<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.text.DecimalFormat" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value=" ../../../assets/themify-icons/themify-icons.css " />" rel="stylesheet">
    <link rel="stylesheet" href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css" />

    <title>Document</title>
</head>
<body>
<div class=" mt-4">

    <table class="table table-striped" id="oderTable">
        <thead>
        <tr>
            <th scope="col">STT</th>
            <th scope="col">Hình ảnh</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Giá</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Tổng giá</th>
            <th scope="col">Người đặt</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Địa chỉ</th>
            <th class="col-2" scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="oder" items="${oders}" varStatus="key">
    <tr>
        <td>${key.index+1}</td>
        <td>
            <img src="${products[key.index].getProductPhoto()}" height="60">
        </td>
        <td>${products[key.index].getProductName()}</td>
        <td>${DecimalFormat("###,###,###,###").format(products[key.index].getPrice())}</td>
        <td>${oder.getQuantity()}</td>
        <td>${DecimalFormat("###,###,###,###").format(products[key.index].getPrice()*oder.getQuantity())}</td>
        <td>${accs[key.index].getUsername()}</td>
        <td>${oder.getNumberPhone()}</td>
        <td>${oder.getAddress()}</td>
        <td>
            <c:if test="${oder.getStatus()==0}" >
               <p> Đang chờ duyệt</p>
            </c:if>
            <c:if test="${oder.getStatus()==1}" >
                <p> Đang chuuẩn bị hàng </p>
            </c:if>
            <c:if test="${oder.getStatus()==2}" >
                <p> Đang vận chuyển</p>
            </c:if>
            <c:if test="${oder.getStatus()==3}" >
                <p> Đang nhận hàng</p>
            </c:if>


        </td>
    </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready( function () {
        $('#oderTable').DataTable();
    } );
</script>
</body>
</html>