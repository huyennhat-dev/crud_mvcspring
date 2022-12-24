<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Chuyện chưa kể</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="<c:url value=" ../assets/themify-icons/themify-icons.css " />" rel="stylesheet">

    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/grid.css">
    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/slider.css">
    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/base.css">

    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/main.css">
    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/responsive.css">

    <link rel="icon" href="http://localhost:8081/tcv/public/uploads/logo/logo.wepb" type="image/gif" sizes="100x100">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="http://localhost:8081/tcv/public/assets/css/home_css.css">
    <style>
        .ck.ck-content {
            min-height: 400px
        }
        .header-search_group input[type="text"]{
            right: 0%;
            left: 0%;
        }
        .header-search_group button[type="submit"]{
            right: 6%;
        }
    </style>
</head>

<body>
    <header class="header" id="header" style="top: 0px;">
        <div class="grid wide">
            <div class="header-container row pr-15 pl-15">
                <div class="header-menu-bar  col l-0 m-4 c-2">
                    <a class="header-menu-bar__icon btn-modal-active" style="position: relative;z-index: 9999;">
                        <i id="menu_btn" onclick="menu_btn()" class="ti-menu"></i>
                    </a>
                </div>
                <div class="header-logo col l-2 m-4 c-8 ">
                    <div class="header-logo-wrap">
                        <a href="/" class="header-logo-link">
                            <img src="http://localhost:8081/tcv/public/uploads/logo/logo-1.wepb" title="Truyện Convert" alt="Truyện Convert" class="header-logo-link-img">
                        </a>
                    </div>
                </div>
                <div class="header-logo col l-3 m-0 c-0 "></div>
                <div class="header-menu  col l-7 m-0 c-0">
                    <ul class="header-menu__list mb-0 ">
                        <li class="header-menu__list-items">
                            <a class="header-menu__list-items-link">
                                <i class="ti-home"></i> <span class="">Home</span>
                            </a>
                        </li>
                        <li class="header-menu__list-items">
                            <a class="header-menu__list-items-link">
                                <span class="header_items">Chuyện nghề nghiệp</span>
                            </a>
                            <ul class="row header-submenu__list ">
                                <c:forEach var="category" items="${categories}">
                                    <li class="col l-6 header-submenu__list-items ">
                                        <a href="" class="header-submenu__list-items-link">${category.getCategoryName()}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="header-menu__list-items" style="padding: 0 10px 0 10px;">
                            <div class="header-search_group">
                                <form method="GET" action="">
                                    <input  autocomplete="off" type="text" class="position-relative" placeholder="Tìm kiếm...">
                                    <button type="submit" class="position-absolute"><i class="ti-search"></i></button>
                                </form>
                            </div>
                        </li>
                        <c:if test="${account.getStatus() == 0}">
                        <li class="header-menu__list-items">
                            <a href="" class="header-menu__list-items-link">
                                 <span class="header_items">Giỏ hàng</span>
                                <i class="ti-shopping-cart-full"></i></a>
                        </li>
                        </c:if>
                        <c:if test="${empty account.getEmail()}">
                        <li class="header-menu__list-items">
                            <a href="./auth/login" class="header-menu__list-items-link">
                                 <span class="">Đăng nhập</span>
                            </a>
                        </li>
                        </c:if>
                        <c:if test="${account.getStatus() >= 1}">
                            <li class="header-menu__list-items">
                                <a href="./admin/home" class="header-menu__list-items-link">
                                    <span class="">Quản trị</span>
                                </a>
                            </li>
                        </c:if>

                    </ul>
                </div>
                <div class="header-group-btn  col l-0 m-4 c-2">
                    <ul class="header-group-btn__list mb-0">
                        <li id="cl1" onclick="search_btn();" class="header-group-btn__list-items" style="height: 36px; position: relative; z-index: 9999;">
                            <div class="header-group-btn__list-items-link header-group-btn__list-items-search btn-modal-active">
                                <i id="search_icon_x" class="header-group-btn__list-items-link-icon ti-search"></i>
                            </div>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </header>




    <script>
        var prevScrollpos = window.pageYOffset;
        window.onscroll = function() {
            var currentScrollPos = window.pageYOffset;
            if (prevScrollpos >= currentScrollPos) {
                document.getElementById("header").style.top = "0px";
            } else {
                document.getElementById("header").style.top = "-91px";
            }
            prevScrollpos = currentScrollPos;
        }
    </script>
</body>

</html>