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
    <link href="${context}/lib/alertifyjs/css/themes/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${context}/lib/OwlCarousel/dist/assets/owl.theme.default.min.css">
    <link rel="stylesheet" href="${context}/lib/OwlCarousel/dist/assets/owl.carousel.min.css">
    <link rel="stylesheet" href="${context}/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="${context}/lib/noUiSlider/distribute/nouislider.min.css">
    <link rel="stylesheet" href="${context}/lib/ionicons/docs/css/ionicons.min.css">
    <link rel="stylesheet" href="${context}/lib/plyr/dist/plyr.css">
    <link rel="stylesheet" href="${context}/lib/PhotoSwipe/dist/photoswipe.css">
    <link rel="stylesheet" href="${context}/lib/PhotoSwipe/dist/default-skin/default-skin.css">
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
<script src="${context}/lib/jquery-mask/dist/jquery.mask.min.js" type="text/javascript"></script>
<script src="${context}/lib/alertifyjs/alertify.min.js" type="text/javascript"></script>
<script src="${context}/lib/OwlCarousel/dist/owl.carousel.min.js"></script>
<script src="${context}/lib/jquery-mousewheel/jquery.mousewheel.min.js"></script>
<script src="${context}/lib/malihu-custom-scrollbar-plugin/js/minified/jquery.mCustomScrollbar.min.js"></script>
<script src="${context}/lib/wnumb/wNumb.js"></script>
<script src="${context}/lib/noUiSlider/distribute/nouislider.min.js"></script>
<script src="${context}/lib/plyr/dist/plyr.min.js"></script>
<script src="${context}/lib/morelines/jquery.morelines.min.js"></script>
<script src="${context}/lib/PhotoSwipe/dist/photoswipe.min.js"></script>
<script src="${context}/lib/PhotoSwipe/dist/photoswipe-ui-default.min.js"></script>
<script src="${context}/js/utilidades/constantes.js" type="text/javascript"></script>
<script src="${context}/js/utilidades/utilidades.js" type="text/javascript"></script>
<script src="${context}/js/shared/layout.js"></script>


<jsp:doBody/>

<jsp:invoke fragment="scripts"/>
</body>
</html>