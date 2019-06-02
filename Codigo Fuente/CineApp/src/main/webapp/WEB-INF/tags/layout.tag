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
    <jsp:invoke fragment="title" />
    <link href="${context}/lib/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/daterangepicker/daterangepicker.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/alertify.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/themes/default.min.css" rel="stylesheet" type="text/css">
    <link href="${context}/lib/alertifyjs/css/themes/semantic.min.css" rel="stylesheet" type="text/css">

    <jsp:invoke fragment="styles"/>
</head>
<body>
<nav>
</nav>

<script src="${context}/lib/jquery/jquery-3.4.1.min.js" type="text/javascript"></script>
<script src="${context}/lib/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="${context}/lib/jquery-sizzle/dist/sizzle.min.js" type="text/javascript"></script>
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

<jsp:doBody/>

<jsp:invoke fragment="scripts"/>
</body>
</html>