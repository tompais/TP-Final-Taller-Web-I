<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date"/>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:invoke fragment="title"/>
    <!-- Font -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600%7CUbuntu:300,400,500,700" rel="stylesheet">

    <!-- CSS -->
    <link href="${context}/lib/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/bootstrap/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/bootstrap/css/bootstrap-reboot.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/daterangepicker/daterangepicker.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/alertify.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/themes/default.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/themes/semantic.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${context}/css/shared/estilos.css" type="text/css">


    <!-- Favicons -->
    <link rel="icon" type="image/png" href="${context}/icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="${context}/icon/favicon-32x32.png">
    <link rel="apple-touch-icon" sizes="72x72" href="${context}/icon/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="${context}/icon/apple-touch-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="144x144" href="${context}/icon/apple-touch-icon-144x144.png">

    <jsp:invoke fragment="styles"/>
</head>
<body>
<nav>
</nav>

<script src="${context}/lib/jquery/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${context}/lib/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${context}/lib/jquery-sizzle/dist/sizzle.min.js" type="text/javascript"></script>
<script src="${context}/lib/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="${context}/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${context}/lib/fontawesome/js/all.min.js" type="text/javascript"></script>
<script src="${context}/lib/popperjs/popper.min.js" type="text/javascript"></script>
<script src="${context}/lib/tooltipjs/tooltip.min.js" type="text/javascript"></script>
<script src="${context}/lib/validatejs/validate.min.js" type="text/javascript"></script>
<script src="${context}/lib/momentjs/moment-with-locales.js" type="text/javascript"></script>
<script src="${context}/lib/daterangepicker/daterangepicker.js" type="text/javascript"></script>
<script src="${context}/lib/alertifyjs/alertify.min.js" type="text/javascript"></script>
<script src="${context}/js/utilidades/constantes.js" type="text/javascript"></script>
<script src="${context}/js/utilidades/utilidades.js" type="text/javascript"></script>
<script src="${context}/js/shared/layout.js"></script>

<!-- header -->
<header class="header">
    <div class="header__wrap">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="header__content">
                        <!-- header logo -->
                        <a href="${context}" class="header__logo">
                            <img src="${context}/img/logo.svg" alt="">
                        </a>
                        <!-- end header logo -->

                        <!-- header nav -->
                        <ul class="header__nav">
                            <!-- dropdown -->
                            <li class="header__nav-item">
                                <a class="dropdown-toggle header__nav-link" href="#" role="button" id="dropdownMenuHome"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Home</a>

                                <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuHome">
                                    <li><a href="${context}">Home</a></li>
                                </ul>
                            </li>
                            <!-- end dropdown -->

                            <!-- dropdown -->
                            <li class="header__nav-item">
                                <a class="dropdown-toggle header__nav-link" href="#" role="button"
                                   id="dropdownMenuCatalog" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">Catalog</a>

                                <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuCatalog">
                                    <li><a href="catalog1.html">Catalog Grid</a></li>
                                    <li><a href="catalog2.html">Catalog List</a></li>
                                    <li><a href="details1.html">Details Movie</a></li>
                                    <li><a href="details2.html">Details TV Series</a></li>
                                </ul>
                            </li>
                            <!-- end dropdown -->

                            <li class="header__nav-item">
                                <a href="${context}/faq" class="header__nav-link">Help</a>
                            </li>

                            <!-- dropdown -->
                            <li class="dropdown header__nav-item">
                                <a class="dropdown-toggle header__nav-link header__nav-link--more" href="#"
                                   role="button" id="dropdownMenuMore" data-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false"><i class="icon ion-ios-more"></i></a>

                                <ul class="dropdown-menu header__dropdown-menu" aria-labelledby="dropdownMenuMore">
                                    <li><a href="${context}/about">About</a></li>
                                    <li><a href="${context}/signin">Sign In</a></li>
                                    <li><a href="${context}/signup">Sign Up</a></li>
                                </ul>
                            </li>
                            <!-- end dropdown -->
                        </ul>
                        <!-- end header nav -->

                        <!-- header auth -->
                        <div class="header__auth">
                            <button class="header__search-btn" type="button">
                                <i class="icon ion-ios-search"></i>
                            </button>

                            <a href="${context}/signin" class="header__sign-in">
                                <i class="icon ion-ios-log-in"></i>
                                <span>sign in</span>
                            </a>
                        </div>
                        <!-- end header auth -->

                        <!-- header menu btn -->
                        <button class="header__btn" type="button">
                            <span></span>
                            <span></span>
                            <span></span>
                        </button>
                        <!-- end header menu btn -->
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- header search -->
    <form action="#" class="header__search">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="header__search-content">
                        <input type="text" placeholder="Search for a movie, TV Series that you are looking for">

                        <button type="button">search</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- end header search -->
</header>
<!-- end header -->

<jsp:doBody/>

<jsp:invoke fragment="scripts"/>
</body>
</html>