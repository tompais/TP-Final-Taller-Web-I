<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 16/7/2019
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Reserva #${numeroTicket}</title>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            const numeroTicket = '${numeroTicket}';
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="d-flex flex-column text-center" style="margin-top: 110px;">
            <h2 class="text-white">Reserva #${numeroTicket}</h2>
        </div>
        <div class="container mt-3 mb-5 mb-md-4">
            <div class="d-flex flex-column text-center">
                <h5 class="text-white-50">Cine: <span class="text-white">${funcion.cine.nombre}</span></h5>
                <h5 class="text-white-50">Pelicula: <span class="text-white">${funcion.pelicula.nombre}</span></h5>
                <h5 class="text-white-50">Asientos: <c:forEach items="${asientos}" var="asiento"><span class="text-white">${asiento.fila}${asiento.columna} </span></c:forEach></h5>
                <h5 class="text-white-50">Fecha: <span class="text-white">${funcion.fecha}</span></h5>
                <h5 class="text-white-50">Horario: <span class="text-white">${funcion.hora}</span></h5>
                <h5 class="text-white-50">Precio Total: <span class="text-white">$ ${precioTotal}</span></h5>
            </div>
        </div>
    </jsp:body>
</t:layout>
