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

    <jsp:attribute name="styles">
    	<link href="${context}/css/sala/asientos.css" rel="stylesheet"
              type="text/css">
    </jsp:attribute>

    <jsp:attribute name="scripts">
    	<script>
            const precioUnitario = ${precio};
            const funcionId = ${funcionId};
            var asientosDisponibles = ${asientosDisponibles};
            const sender = '${sender}';
        </script>
        <script src="${context}/lib/sockjs-client/dist/sockjs.min.js"></script>
        <script src="${context}/lib/stompjs/stomp.min.js"></script>
        <script src="${context}/js/sala/sala.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="d-flex flex-column text-center">
            <h1 class="text-white" style="margin-top:110px;">Seleccione los Asientos</h1>
            <small class="text-muted">Cantidad de asientos disponibles: <span
                    id="spanContadorAsientosDisponibles"></span></small>
            <p id="precioFinal" class="d-none text-white mt-2 mb-0"></p>
        </div>
        <div class="my-3 container">
            <div class="row">
                <p class="w-100 text-center text-white h5">PANTALLA</p>
            </div>
            <div id="sala"> <!-- borde de la sala -->
                <c:forEach var="i" begin="0" end="${fila}">
                    <c:set var="col" value='${1}'></c:set>
                    <div class="row justify-content-center">
                        <c:forEach var="j" begin="0" end="${columna}">
                            <c:choose>
                                <c:when test="${not empty formatoSala[i][j]}">
                                    <c:choose>
                                        <c:when test="${formatoSala[i][j].estadoAsientoId == libre.id}">
                                            <div class="seat">
                                                <input type="checkbox" fila="${i+1}" columna="${col}" id="${i+1}${col}"/>
                                                <label for="${i+1}${col}">${i+1}${formatoSala[i][j].columna}</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${formatoSala[i][j].estadoAsientoId == ocupado.id}">
                                            <div class="seatOcupado">
                                                <input type="checkbox" fila="${i+1}" columna="${col}" id="${i+1}${col}" disabled/>
                                                <label for="${i+1}${col}">${i+1}${formatoSala[i][j].columna}</label>
                                            </div>
                                        </c:when>
                                        <c:when test="${formatoSala[i][j].estadoAsientoId == reservado.id}">
                                            <div class="seatReservado">
                                                <input type="checkbox" fila="${i+1}" columna="${col}" id="${i+1}${col}" disabled/>
                                                <label for="${i+1}${col}">${i+1}${formatoSala[i][j].columna}</label>
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <div class="ml-4"></div>
                                </c:otherwise>
                            </c:choose>
                            <c:set var="col" value='${col+1}'></c:set>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
            <div class="d-flex align-items-center justify-content-md-end justify-content-center mt-3">
                <a href="#" class="text-white p-3 rounded shadow" style="background-color: #ff5860;">
                    Siguiente
                </a>
            </div>
        </div>
    </jsp:body>
</t:layout>