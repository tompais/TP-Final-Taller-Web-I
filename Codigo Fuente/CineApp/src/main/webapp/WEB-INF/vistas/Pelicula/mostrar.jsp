<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 1/7/2019
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Película</title>
    </jsp:attribute>
    <jsp:body>
        <div class="container-fluid mb-4" style="margin-top: 110px;">
            <h1 class="mx-auto text-center text-white">${pelicula.nombre}</h1>
            <div class="row">
                <div class="col-sm">
                    <h3 class="text-white mb-4">Trailer</h3>
                    <div class="plyr__video-embed" id="player">
                        <iframe width="1280" height="720" src="https://www.youtube.com/embed/${pelicula.trailer.embed}?origin=${context}&amp;iv_load_policy=3&amp;modestbranding=1&amp;playsinline=1&amp;showinfo=0&amp;rel=0&amp;enablejsapi=1" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowtransparency allowfullscreen></iframe>
                    </div>
                </div>
                <div class="col-sm">
                    <h3 class="text-white mb-4">Compra</h3>

                </div>
            </div>
            <div class="row">
                <div class="col-sm">
                    <h3 class="text-white my-4">Sinopsis</h3>
                    <p class="text-white-50 text-justify">${pelicula.sinopsis}</p>
                </div>
                <div class="col-sm">
                    <h3 class="text-white my-4">Información</h3>
                    <p class="text-white-50 text-justify m-0 p-0"><span class="text-white font-weight-bold">Género:</span> ${listaGeneros}</p>
                    <p class="text-white-50 text-justify m-0 p-0"><span class="text-white font-weight-bold">Reparto:</span> ${listaActores}</p>
                    <p class="text-white-50 text-justify m-0 p-0"><span class="text-white font-weight-bold">Clasificación:</span> ${pelicula.clasificacion.nombre}</p>
                    <p class="text-white-50 text-justify m-0 p-0"><span class="text-white font-weight-bold">Duración:</span> ${pelicula.duracion} minutos</p>
                    <p class="text-white-50 text-justify m-0 p-0"><span class="text-white font-weight-bold">País de Origen:</span> ${pelicula.pais.nombre}</p>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
