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
    <jsp:attribute name="scripts">
        <script>
            const pathGetFuncionIdByConfiguracion = '${context}' + '/getFuncionIdByConfiguracion';
            const pathGetHorariosLibresFuncion = '${context}' + '/getHorariosLibresFuncion';
            const pathGetTipoFuncionesDisponibles = '${context}' + '/getTipoFuncionesDisponibles';
            const pathGetRangoFechaCompra = '${context}' + '/getRangoFechaCompra';
            const peliculaId = "${pelicula.id}";
            const url = "${context}";
        </script>
        <script src="${context}/js/pelicula/mostrar.js"></script>
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
                    <div class="row">
                        <div class="col-sm">
                            <div class="form-group">
                                <label class="text-white-50" for="selectCine">Seleccione el Cine:</label>
                                <select name="cine" id="selectCine" class="form-control border-dark rounded-0 text-white" style="background: #343a40;">
                                    <option value="0" class="bg-black text-white" disabled selected>Seleccione un cine...</option>
                                    <c:forEach items="${cines}" var="cine">
                                        <option value="${cine.id}" class="bg-dark text-white">${cine.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm">
                            <div id="divFormGroupTipoFuncion" class="form-group d-none">
                                <label for="selectTipoFuncion" class="text-white-50">Seleccione el Tipo de Función:</label>
                                <select name="tipoFuncion" id="selectTipoFuncion" class="form-control border-dark rounded-0 text-white" style="background: #343a40;">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm">
                            <div id="divFormGroupFechaCompra" class="form-group d-none">
                                <label class="text-white-50" for="inputFechaCompra">Escoja una Fecha:</label>
                                <div class="input-group">
                                    <input disabled id="inputFechaCompra" name="fechaCompra" type="text" class="form-control text-white rounded-0 bg-dark border-dark">
                                    <div class="input-group-append">
                                        <button id="btnInputFechaCompra" class="btn btn-outline-secondary rounded-0 border-0 bg-dark" type="button">
                                            <i class="fas fa-calendar-alt"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm">
                            <h6 id="textSinHorariosCompra" class="d-none mt-4 text-center mx-auto text-white-50">No hay horarios con asientos libres para esta fecha</h6>
                            <div id="divFormGroupHorarioCompra" class="form-group d-none">
                                <label for="selectHorarioCompra" class="text-white-50">Seleccione un horario:</label>
                                <select name="horarioCompra" id="selectHorarioCompra" class="form-control border-dark rounded-0 text-white" style="background: #343a40;">
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row my-2 justify-content-center align-items-center">
                        <a id="anchorSeleccionarSala" href="#" class="d-none p-3 text-white rounded shadow" style="background-color: #ff5860;">Seleccionar Asiento</a>
                    </div>
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
