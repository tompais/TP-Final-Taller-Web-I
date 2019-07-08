<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 13/6/2019
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:layout>
	<jsp:attribute name="title">
        <title>Reservar Asiento</title>
    </jsp:attribute>

	<jsp:attribute name="styles">
    	<link href="${context}/css/sala/asientos.css" rel="stylesheet"
			type="text/css">
    </jsp:attribute>
    
    <jsp:attribute name="scripts">
    	<script> var precioFinal = ${precio}; </script>
        <script src="${context}/js/sala/sala.js"></script>
    </jsp:attribute>

	<jsp:body>
		<h1 class="mx-auto text-center text-white" style="margin-top:110px;">Sala</h1>
        <div class="my-5 container">
        	<div class="row">
	        	<p id="precioFinal" class="text-center text-white h5"></p>
			  	<p class="w-100 text-center text-white h5">PANTALLA</p>
		  	</div>
		    <div class="fuselage container" id="sala"> <!-- borde de la sala -->
				<c:forEach var="i" begin="0" end="${fila}">
					<c:set var="col" value='${1}'></c:set>
					<div class="row justify-content-center">
					    <c:forEach var="j" begin="0" end="${columna}">
					    	<c:choose>
					      		<c:when test="${not empty formatoSala[i][j]}">
					      			<c:choose>
							      		<c:when test="${formatoSala[i][j].estadoAsiento == libre}">
							      			<div class="seat">
									      		<input type="checkbox" id="${i+1}${col}" />
												<label for="${i+1}${col}">${i+1}${formatoSala[i][j].columna}</label>
											</div>
							      		</c:when>
							      		<c:when test="${formatoSala[i][j].estadoAsiento == ocupado}">
							      			<div class="seatOcupado">
							      				<input type="checkbox" id="${i+1}${col}" />
												<label for="${i+1}${col}">${i+1}${formatoSala[i][j].columna}</label>
											</div>
							      		</c:when>
						      		</c:choose>
						      		<c:set var="col" value='${col+1}'></c:set>
					      		</c:when>
					      		<c:otherwise>
					      			<div class="ml-4"></div>
					      		</c:otherwise>
						    </c:choose>
						</c:forEach>
					</div>
				</c:forEach>
		    </div>
		    <a href="#" class="header__sign-in ml-auto mt-5">
            	 <span>Siguiente</span>
            </a>
		</div>
    </jsp:body>
</t:layout>