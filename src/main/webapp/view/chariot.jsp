<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <%@ page import="java.text.DecimalFormat" %>

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
                                <li class="breadcrumb-item active"><a>Giỏ hàng</a></li>
                            </ol>
                        </nav>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Hình ảnh</th>
                                    <th class="col-2" scope="col">Tên sản phẩm</th>
                                    <th class="col-2" scope="col">Giá</th>
                                    <th class="col-2" scope="col">Số lượng</th>
                                    <th class="col-2" scope="col">Tổng giá</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="chariot" items="${chariots}" varStatus="key">
                                    <tr>
                                        <th scope="row">${key.index+1}</th>
                                        <td>
                                            <img src="${products[key.index].getProductPhoto()}" height="70">
                                        </td>
                                        <td>${products[key.index].getProductName()}</td>
                                        <td>${DecimalFormat("###,###,###,###").format(products[key.index].getPrice()) } VND</td>
                                        <td>
                                            <a href="./cart/change/0/${chariot.getId()}" class="btn btn-outline-secondary rounded-circle"><i class="ti-minus"></i></a> ${chariot.getQuantity()}
                                            <a href="./cart/change/1/${chariot.getId()}" class="btn btn-outline-secondary rounded-circle"><i class="ti-plus"></i></a>

                                        </td>
                                        <td>${ DecimalFormat("###,###,###,###").format(products[key.index].getPrice() * chariot.getQuantity())} VND</td>
                                        <td>
                                            <a href="./cart/delete/${chariot.getId()}" class="btn btn-outline-danger rounded-circle"><i class="ti-trash"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <%--@elvariable id="oder" type=""--%>
                        <form:form action="./cart" method="POST" modelAttribute="oder">
                        <div class="row container">

                                <div class="col col-8">
                                    <h5>Nhập thông tin nhận hàng</h5>
                                    <div class="form-group">
                                        <label >Họ và tên</label>
                                       <div class="form-control">${username}</div>
                                    </div>
                                    <div class="form-group">
                                        <label >Số điện thoại</label>
                                        <form:input path="numberPhone" type="number" class="form-control"  placeholder="VD: 096xxxx857"  />
                                    </div>
                                    <div class="form-group">
                                        <label >Địa chỉ nhận hàng</label>
                                        <form:input path="address" type="text" class="form-control"  placeholder="VD: 470 Nguyễn Văn A, Đà Nẵng" />
                                    </div>
                                </div>
                                <div class="col col-4">
                                    <h5>Tổng giá giỏ hàng</h5>
                                    <h6>${DecimalFormat("###,###,###,###").format(totalPrice)} VND</h6>
                                    <button type="submit" class="btn btn-outline-danger mt-4 mb-4">Đặt hàng</button>
                                </div>
                        </div>
                        </form:form>

                    </div>
                </body>

                </html>