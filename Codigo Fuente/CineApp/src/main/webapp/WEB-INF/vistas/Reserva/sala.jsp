<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 13/6/2019
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Reservar Asiento</title>
    </jsp:attribute>

    <jsp:body>
        <h1 class="text-center">Llegué a reserva</h1>
    </jsp:body>
</t:layout>