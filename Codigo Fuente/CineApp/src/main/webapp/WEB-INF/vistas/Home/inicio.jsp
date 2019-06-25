<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="title">
		<title>CineApp</title>
	</jsp:attribute>

    <jsp:body>
        <!-- home -->
        <section class="home home--bg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h1 class="home__title"><b>RECIÉN</b> ESTRENADAS</h1>

                        <c:if test="${estrenos != null && estrenos.size() > 0}">
                            <button class="home__nav home__nav--prev" type="button">
                                <i class="icon ion-ios-arrow-round-back"></i>
                            </button>
                            <button class="home__nav home__nav--next" type="button">
                                <i class="icon ion-ios-arrow-round-forward"></i>
                            </button>
                        </c:if>
                    </div>

                    <div class="col-12">
                        <c:choose>
                            <c:when test="${estrenos != null && estrenos.size() > 0}">
                                <div class="owl-carousel home__carousel">
                                    <c:forEach items="${estrenos}" var="estreno">
                                        <div class="item">
                                            <div class="card card--big">
                                                <a href="#" class="card__cover">
                                                    <img src="${context}/img/covers/${estreno.poster.nombre}"
                                                         alt="${estreno.nombre}" class="img-fluid">
                                                </a>
                                                <div class="card__content">
                                                    <h3 class="card__title">
                                                        <a href="#">${estreno.nombre}</a>
                                                    </h3>
                                                    <span class="card__category">
                                                        <c:forEach items="${estreno.peliculaGeneroPeliculas}" var="peliculaGeneroPelicula">
                                                            <a href="#">${peliculaGeneroPelicula.generoPelicula.nombre}</a>
                                                        </c:forEach>
                                                    </span>
                                                    <ul class="card__list ml-0">
                                                        <li>${estreno.clasificacion.nombre}</li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <h5 class="text-white-50 text-center mt-5">No se han encontrado estrenos recientes</h5>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </section>
        <!-- end home -->

        <!-- content -->
        <section class="content">
            <div class="content__head">
                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <!-- content title -->
                            <h2 class="content__title mb-4">Mejor Calificadas</h2>
                            <!-- end content title -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <!-- card -->
                    <c:forEach var="publicacion" items="${publicaciones}">
                        <div class="col-6 col-sm-12 col-lg-6">
                            <div class="card card--list">
                                <div class="row">
                                    <div class="col-12 col-sm-4">
                                        <div class="card__cover">
                                            <img src="${context}/img/covers/${publicacion.pelicula.poster.nombre}" alt="">
                                        </div>
                                    </div>

                                    <div class="col-12 col-sm-8">
                                        <div class="card__content">
                                            <h3 class="card__title"><a href="#">${publicacion.pelicula.nombre}</a></h3>
                                            <span class="card__category">
                                                <c:forEach var="peliculaGeneroPelicula" items="${publicacion.pelicula.peliculaGeneroPeliculas}">
                                                    <a href="#">${peliculaGeneroPelicula.generoPelicula.nombre}</a>
                                                </c:forEach>
											</span>

                                            <div class="card__wrap">
                                                <span class="card__rate"><i class="icon ion-ios-star"></i>${publicacion.promedioCalificacion}</span>

                                                <ul class="card__list">
                                                    <li>${publicacion.pelicula.clasificacion.nombre}</li>
                                                </ul>
                                            </div>

                                            <div class="card__description">
                                                <p>${publicacion.pelicula.sinopsis}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- end content -->

        <!-- expected premiere -->
        <section class="section section--bg" data-bg="img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <!-- section title -->
                    <div class="col-12">
                        <h2 class="section__title">Expected premiere</h2>
                    </div>
                    <!-- end section title -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover7.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">Avengers: Endgame</a></h3>
                                <span class="card__category">
								<a href="#">Action</a>
								<a href="#">Triler</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>8.4</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover3.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">Benched</a></h3>
                                <span class="card__category">
								<a href="#">Comedy</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>7.1</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover2.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">Whitney</a></h3>
                                <span class="card__category">
								<a href="#">Romance</a>
								<a href="#">Drama</a>
								<a href="#">Music</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>6.3</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover6.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">Blindspotting</a></h3>
                                <span class="card__category">
								<a href="#">Comedy</a>
								<a href="#">Drama</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>7.9</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover4.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">I Dream in Another Language</a></h3>
                                <span class="card__category">
								<a href="#">Action</a>
								<a href="#">Triler</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>8.4</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- card -->
                    <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                        <div class="card">
                            <div class="card__cover">
                                <img src="${context}/img/covers/cover5.jpg" alt="">
                            </div>
                            <div class="card__content">
                                <h3 class="card__title"><a href="#">Benched</a></h3>
                                <span class="card__category">
								<a href="#">Comedy</a>
							</span>
                                <span class="card__rate"><i class="icon ion-ios-star"></i>7.1</span>
                            </div>
                        </div>
                    </div>
                    <!-- end card -->

                    <!-- section btn -->
                    <div class="col-12">
                        <a href="#" class="section__btn">Show more</a>
                    </div>
                    <!-- end section btn -->
                </div>
            </div>
        </section>
        <!-- end expected premiere -->
    </jsp:body>
</t:layout>
