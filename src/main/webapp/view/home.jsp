<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Chuyện chưa kể</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&display=swap"
          rel="stylesheet">

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

        .header-search_group input[type="text"] {
            right: 0%;
            left: 0%;
        }

        .header-search_group button[type="submit"] {
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
                        <img src="http://localhost:8081/tcv/public/uploads/logo/logo-1.wepb" title="Truyện Convert"
                             alt="Truyện Convert" class="header-logo-link-img">
                    </a>
                </div>
            </div>
            <div class="header-logo col l-3 m-0 c-0 "></div>
            <div class="header-menu  col l-7 m-0 c-0" style="display: flex; justify-content: end;">
                <ul class="header-menu__list mb-0 ">
                    <li class="header-menu__list-items">
                        <a href="/" class="header-menu__list-items-link">
                            <i class="ti-home"></i> <span class="">Home</span>
                        </a>
                    </li>
<%--                    <li class="header-menu__list-items">--%>
<%--                        <a class="header-menu__list-items-link">--%>
<%--                            <span class="header_items">Danh mục</span>--%>
<%--                        </a>--%>
<%--                        <ul class="row header-submenu__list ">--%>
<%--                            <c:forEach var="category" items="${categories}">--%>
<%--                                <li class="col l-6 header-submenu__list-items ">--%>
<%--                                    <a href="" class="header-submenu__list-items-link">${category.getCategoryName()}</a>--%>
<%--                                </li>--%>
<%--                            </c:forEach>--%>
<%--                        </ul>--%>
<%--                    </li>--%>
                    <li class="header-menu__list-items" style="padding: 0 10px 0 10px;">
                        <div class="header-search_group">
                            <form method="GET" action="">
                                <input autocomplete="off" type="text" class="position-relative"
                                       placeholder="Tìm kiếm...">
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
                            <a href="./admin" class="header-menu__list-items-link">
                                <span class="">Quản trị</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </div>
            <div class="header-group-btn  col l-0 m-4 c-2">
                <ul class="header-group-btn__list mb-0">
                    <li id="cl1" onclick="search_btn();" class="header-group-btn__list-items"
                        style="height: 36px; position: relative; z-index: 9999;">
                        <div class="header-group-btn__list-items-link header-group-btn__list-items-search btn-modal-active">
                            <i id="search_icon_x" class="header-group-btn__list-items-link-icon ti-search"></i>
                        </div>
                    </li>
                </ul>
            </div>

        </div>
    </div>
</header>
<main class="main">
    <c:forEach var="category" items="${categories}">
        <div class="container grid wide mb-20">
            <div class="row">
                <div class="appreciate w-100">
                    <div class="heading">
                        <div class="title">${category.getCategoryName()}</div>
                        <a href="" class="link pr-20">Xem tất cả</a>
                    </div>
                </div>
                <c:forEach var="product" items="${products}">
                    <c:if test="${product.getCategoryID()==category.getId()}">
                        <div class="col l-4 m-4 c-12  border-bottom pb-15 pt-15 pl-15 pr-15">
                            <div class="media">
                                <a href=""
                                   class="nh-thumb nh-thumb--90 shadow mr-3">
                                    <img src="${product.getProductPhoto()}"
                                         title="${product.getProductName()}" alt="${product.getProductName()}"
                                         width="72">
                                </a>
                                <div class="media-body">
                                    <h2 class="fz-13 fw-500 text-overflow-2-lines mb-8">
                                        <a href=""
                                           class="d-block">${product.getProductName()}</a>
                                    </h2>
                                    <div class="d-flex align-items-center mb-8">
                                        <div class="text-danger fz-15"> ${product.getPrice()} đ</div>
                                    </div>
                                    <div class="d-flex align-items-center mt-2 py-1">
                                        <a href="" class="mr-4">
                                            <span class="d-inline-block border fz-12 border-primary small px-2 py-8 text-primary truncate-150">
                                                Chi tiết
                                            </span>
                                        </a>
                                        <a href="">
                                            <span class="d-inline-block border fz-12 border-primary small px-2 py-8 text-primary truncate-150">
                                                Thêm vào giỏ hàng
                                            </span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>

            </div>
        </div>
    </c:forEach>
</main>
<footer class="footer text-center"
        style="background: url(http://localhost:8081/tcv/public/uploads/logo/footer-bg.wepb) no-repeat; background-size: 100% 100%;">
    <div class="grid wide footer-body">
        <div class="footer-logo">
            <a href="" class="logo-link">
                <img src="http://localhost:8081/tcv/public/uploads/logo/logo.wepb" alt="Truyện Convert">
            </a>
        </div>
        <div class="footer-desc">
            Truyện Convert là nền tảng mở trực tuyến, miễn phí đọc truyện chữ được convert hoặc dịch kỹ lưỡng.
            Là sân chơi mở và free cho cộng đồng fan kiếm hiệp, tiên hiệp, ngôn tình...
        </div>
    </div>
</footer>


<script>
    var prevScrollpos = window.pageYOffset;
    window.onscroll = function () {
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