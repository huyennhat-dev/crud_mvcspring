<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>

            <html lang="vi">

            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <meta http-equiv="X-UA-Compatible" content="ie=edge">
                <meta name="Description" content="Enter your description here" />
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
                <link href="<c:url value=" ../assets/toast/toast.css " />" rel="stylesheet">
                <title>Register</title>
            </head>

            <body>
                <c:if test="${not empty error}">
                    <div class=" alert-form">
                        <div id="toast-container" class="toast-top-right">
                            <div class="alert toast toast-warning" aria-live="assertive">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <div class="toast-message">${error}</div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <section class="vh-100">
                    <div class="container py-5 h-100">
                        <div class="row d-flex align-items-center justify-content-center h-100">
                            <div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
                                <%--@elvariable id="account" type=""--%>
                                    <form:form method="POST" action="/auth/register" modelAttribute="account">
                                        <!-- Username input --->
                                        <div class="form-outline mb-4">
                                            <form:label class="form-label" path="username">Username</form:label>
                                            <form:input type="text" class="form-control form-control" path="username" />
                                            <form:errors class="text-danger" path="username">
                                                <p class="text-danger">Tên của bạn không hợp lệ!</p>
                                            </form:errors>
                                        </div>

                                        <!-- Email input -->
                                        <div class="form-outline mb-4">
                                            <form:label class="form-label" path="email">Email</form:label>
                                            <form:input type="email" class="form-control form-control" path="email" />
                                            <form:errors class="text-danger" path="email">
                                                <p class="text-danger m-0">Email của bạn không hợp lệ!</p>
                                            </form:errors>
                                        </div>

                                        <!-- Password input -->
                                        <div class="form-outline mb-4">
                                            <form:label class="form-label" path="password">Password</form:label>
                                            <form:input type="password" class="form-control form-control" path="password" />
                                            <form:errors class="text-danger" path="password">
                                                <p class="text-danger m-0">Mật khẩu của bạn không hợp lệ!</p>
                                            </form:errors>
                                        </div>
                                        <!-- Submit button -->
                                        <button type="submit" class="btn btn-primary btn-block">Đăng ký</button>
                                    </form:form>
                            </div>
                            <div class="col-md-8 col-lg-7 col-xl-6">
                                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp" class="img-fluid" alt="Sample image">
                                <div class="text-center">
                                    <p>
                                        Bạn đã có tài khoản?
                                        <a href="./login">Đăng nhập</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                <script src="<c:url value=" ../assets/toast/toast.js " />"></script>
                <script>
                    $(document).ready(function() {
                        $('.alert-form').fadeOut(10000)
                    })
                </script>
            </body>

            </html>