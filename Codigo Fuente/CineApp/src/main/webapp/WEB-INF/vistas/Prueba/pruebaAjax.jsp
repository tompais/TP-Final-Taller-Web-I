<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<select id="cineElegido">
<c:forEach items="${cines}" var="cine">
<option value="${cine.idCine}">${cine.nombreCine}</option>
</c:forEach>
</select>

<div id="listaFunciones"></div>
<script src="${context}/prueba/prueba.js"></script>
</body>
</html>