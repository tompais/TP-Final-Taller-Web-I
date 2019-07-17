<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 16/7/2019
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Reserva Exitosa</title>
    </jsp:attribute>
    <jsp:body>
        <div class="d-flex flex-column text-center" style="margin-top: 110px;">
            <h3 class="text-white">¡Su reserva ha sido realizada con éxito!</h3>
        </div>
        <div class="container mt-3 mb-5 mb-md-4">
            <div class="d-flex flex-column text-center">
                <small class="text-white-50">Le hemos enviado un email con un link para consultar los detalles de su compra</small>
                <small class="text-white-50">Su número de ticket es: <span class="text-white">#${numeroTicket}</span></small>
                <small class="text-white-50">Por favor, guarde este número</small>
            </div>
        </div>
    </jsp:body>
</t:layout>
