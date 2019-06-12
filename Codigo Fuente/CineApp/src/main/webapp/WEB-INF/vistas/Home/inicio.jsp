<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="title">
		<title>CineApp</title>
	</jsp:attribute>

    <jsp:attribute name="styles">
		<link rel="stylesheet" href="${context}/lib/OwlCarousel/dist/assets/owl.carousel.min.css">
		<link rel="stylesheet" href="${context}/lib/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="${context}/lib/noUiSlider/distribute/nouislider.min.css">
        <link rel="stylesheet" href="${context}/lib/ionicons/docs/css/ionicons.min.css">
		<link rel="stylesheet" href="${context}/lib/plyr/dist/plyr.css">
		<link rel="stylesheet" href="${context}/lib/PhotoSwipe/dist/photoswipe.css">
		<link rel="stylesheet" href="${context}/lib/PhotoSwipe/dist/default-skin/default-skin.css">
	</jsp:attribute>

    <jsp:attribute name="scripts">
        <script src="${context}/lib/OwlCarousel/dist/owl.carousel.min.js"></script>
        <script src="${context}/lib/jquery-mousewheel/jquery.mousewheel.min.js"></script>
        <script src="${context}/lib/malihu-custom-scrollbar-plugin/js/minified/jquery.mCustomScrollbar.min.js"></script>
        <script src="${context}/lib/wnumb/wNumb.js"></script>
        <script src="${context}/lib/noUiSlider/distribute/nouislider.min.js"></script>
        <script src="${context}/lib/plyr/dist/plyr.min.js"></script>
        <script src="${context}/lib/morelines/jquery.morelines.min.js"></script>
        <script src="${context}/lib/PhotoSwipe/dist/photoswipe.min.js"></script>
        <script src="${context}/lib/PhotoSwipe/dist/photoswipe-ui-default.min.js"></script>
    </jsp:attribute>

    <jsp:body>
        <!-- home -->
        <section class="home home--bg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h1 class="home__title"><b>NEW ITEMS</b> OF THIS SEASON</h1>

                        <button class="home__nav home__nav--prev" type="button">
                            <i class="icon ion-ios-arrow-round-back"></i>
                        </button>
                        <button class="home__nav home__nav--next" type="button">
                            <i class="icon ion-ios-arrow-round-forward"></i>
                        </button>
                    </div>

                    <div class="col-12">
                        <div class="owl-carousel home__carousel">
                            <div class="item">
                                <!-- card -->
                                <div class="card card--big">
                                    <div class="card__cover">
                                        <img src="${context}/img/covers/cover.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                <!-- end card -->
                            </div>

                            <div class="item">
                                <!-- card -->
                                <div class="card card--big">
                                    <div class="card__cover">
                                        <img src="${context}/img/covers/cover2.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
                                    </div>
                                    <div class="card__content">
                                        <h3 class="card__title"><a href="#">Benched</a></h3>
                                        <span class="card__category">
										<a href="#">Comedy</a>
									</span>
                                        <span class="card__rate"><i class="icon ion-ios-star"></i>7.1</span>
                                    </div>
                                </div>
                                <!-- end card -->
                            </div>

                            <div class="item">
                                <!-- card -->
                                <div class="card card--big">
                                    <div class="card__cover">
                                        <img src="${context}/img/covers/cover3.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
                                    </div>
                                    <div class="card__content">
                                        <h3 class="card__title"><a href="#">Whitney</a></h3>
                                        <span class="card__category">
										<a href="#">Romance</a>
										<a href="#">Drama</a>
									</span>
                                        <span class="card__rate"><i class="icon ion-ios-star"></i>6.3</span>
                                    </div>
                                </div>
                                <!-- end card -->
                            </div>

                            <div class="item">
                                <!-- card -->
                                <div class="card card--big">
                                    <div class="card__cover">
                                        <img src="${context}/img/covers/cover4.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                <!-- end card -->
                            </div>
                        </div>
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
                            <h2 class="content__title">New items</h2>
                            <!-- end content title -->

                            <!-- content tabs nav -->
                            <ul class="nav nav-tabs content__tabs" id="content__tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tab-1" role="tab"
                                       aria-controls="tab-1"
                                       aria-selected="true">NEW RELEASES</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2"
                                       aria-selected="false">MOVIES</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3"
                                       aria-selected="false">TV SERIES</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tab-4" role="tab" aria-controls="tab-4"
                                       aria-selected="false">CARTOONS</a>
                                </li>
                            </ul>
                            <!-- end content tabs nav -->

                            <!-- content mobile tabs nav -->
                            <div class="content__mobile-tabs" id="content__mobile-tabs">
                                <div class="content__mobile-tabs-btn dropdown-toggle" role="navigation" id="mobile-tabs"
                                     data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <input type="button" value="New items">
                                    <span></span>
                                </div>

                                <div class="content__mobile-tabs-menu dropdown-menu" aria-labelledby="mobile-tabs">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item"><a class="nav-link active" id="1-tab" data-toggle="tab"
                                                                href="#tab-1" role="tab" aria-controls="tab-1"
                                                                aria-selected="true">NEW RELEASES</a></li>

                                        <li class="nav-item"><a class="nav-link" id="2-tab" data-toggle="tab"
                                                                href="#tab-2"
                                                                role="tab" aria-controls="tab-2"
                                                                aria-selected="false">MOVIES</a></li>

                                        <li class="nav-item"><a class="nav-link" id="3-tab" data-toggle="tab"
                                                                href="#tab-3"
                                                                role="tab" aria-controls="tab-3" aria-selected="false">TV
                                            SERIES</a></li>

                                        <li class="nav-item"><a class="nav-link" id="4-tab" data-toggle="tab"
                                                                href="#tab-4"
                                                                role="tab" aria-controls="tab-4"
                                                                aria-selected="false">CARTOONS</a></li>
                                    </ul>
                                </div>
                            </div>
                            <!-- end content mobile tabs nav -->
                        </div>
                    </div>
                </div>
            </div>

            <div class="container">
                <!-- content tabs -->
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                        <div class="row">
                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">I Dream in Another Language</a></h3>
                                                <span class="card__category">
												<a href="#">Action</a>
												<a href="#">Triler</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>8.4</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>It is a long established fact that a reader will be distracted by
                                                        the
                                                        readable content of a page when looking at its layout. The point
                                                        of
                                                        using Lorem Ipsum is that it has a more-or-less normal
                                                        distribution of
                                                        letters, as opposed to using 'Content here, content here',
                                                        making it
                                                        look like readable English.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->

                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover2.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">Benched</a></h3>
                                                <span class="card__category">
												<a href="#">Comedy</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>7.1</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting
                                                        industry. Lorem Ipsum has been the industry's standard dummy
                                                        text ever
                                                        since the 1500s, when an unknown printer took a galley of type
                                                        and
                                                        scrambled it to make a type specimen book.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->

                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover3.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">Whitney</a></h3>
                                                <span class="card__category">
												<a href="#">Romance</a>
												<a href="#">Drama</a>
												<a href="#">Music</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>6.3</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>It has survived not only five centuries, but also the leap into
                                                        electronic typesetting, remaining essentially unchanged. It was
                                                        popularised in the 1960s with the release of Letraset sheets
                                                        containing
                                                        Lorem Ipsum passages, and more recently with desktop publishing
                                                        software
                                                        like Aldus PageMaker including versions of Lorem Ipsum.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->

                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover4.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">Blindspotting</a></h3>
                                                <span class="card__category">
												<a href="#">Comedy</a>
												<a href="#">Drama</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>7.9</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>Many desktop publishing packages and web page editors now use
                                                        Lorem Ipsum
                                                        as their default model text, and a search for 'lorem ipsum' will
                                                        uncover
                                                        many web sites still in their infancy. Various versions have
                                                        evolved
                                                        over the years, sometimes by accident, sometimes on purpose
                                                        (injected
                                                        humour and the like).</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->

                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover5.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">I Dream in Another Language</a></h3>
                                                <span class="card__category">
												<a href="#">Action</a>
												<a href="#">Triler</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>8.4</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>There are many variations of passages of Lorem Ipsum available,
                                                        but the
                                                        majority have suffered alteration in some form, by injected
                                                        humour, or
                                                        randomised words which don't look even slightly believable. If
                                                        you are
                                                        going to use a passage of Lorem Ipsum, you need to be sure there
                                                        isn't
                                                        anything embarrassing hidden in the middle of text.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->

                            <!-- card -->
                            <div class="col-6 col-sm-12 col-lg-6">
                                <div class="card card--list">
                                    <div class="row">
                                        <div class="col-12 col-sm-4">
                                            <div class="card__cover">
                                                <img src="${context}/img/covers/cover6.jpg" alt="">
                                                <a href="#" class="card__play">
                                                    <i class="icon ion-ios-play"></i>
                                                </a>
                                            </div>
                                        </div>

                                        <div class="col-12 col-sm-8">
                                            <div class="card__content">
                                                <h3 class="card__title"><a href="#">Benched</a></h3>
                                                <span class="card__category">
												<a href="#">Comedy</a>
											</span>

                                                <div class="card__wrap">
                                                    <span class="card__rate"><i class="icon ion-ios-star"></i>7.1</span>

                                                    <ul class="card__list">
                                                        <li>HD</li>
                                                        <li>16+</li>
                                                    </ul>
                                                </div>

                                                <div class="card__description">
                                                    <p>All the Lorem Ipsum generators on the Internet tend to repeat
                                                        predefined
                                                        chunks as necessary, making this the first true generator on the
                                                        Internet. It uses a dictionary of over 200 Latin words, combined
                                                        with a
                                                        handful of model sentence structures, to generate Lorem Ipsum
                                                        which
                                                        looks reasonable. The generated Lorem Ipsum is therefore always
                                                        free
                                                        from repetition, injected humour, or non-characteristic words
                                                        etc.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- end card -->
                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
                        <div class="row">
                            <!-- card -->
                            <div class="col-6 col-sm-4 col-lg-3 col-xl-2">
                                <div class="card">
                                    <div class="card__cover">
                                        <img src="${context}/img/covers/cover.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                        <img src="${context}/img/covers/cover2.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                        <img src="${context}/img/covers/cover3.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                        <img src="${context}/img/covers/cover4.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                        <img src="${context}/img/covers/cover5.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                                        <img src="${context}/img/covers/cover6.jpg" alt="">
                                        <a href="#" class="card__play">
                                            <i class="icon ion-ios-play"></i>
                                        </a>
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
                        </div>
                    </div>
                </div>
                <!-- end content tabs -->
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
                                <img src="${context}/img/covers/cover.jpg" alt="">
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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
                                <img src="${context}/img/covers/cover3.jpg" alt="">
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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
                                <a href="#" class="card__play">
                                    <i class="icon ion-ios-play"></i>
                                </a>
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

        <!-- footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <!-- footer list -->
                    <div class="col-12 col-md-3">
                        <h6 class="footer__title">Download Our App</h6>
                        <ul class="footer__app">
                            <li><a href="#"><img src="${context}/img/Download_on_the_App_Store_Badge.svg" alt=""></a>
                            </li>
                            <li><a href="#"><img src="${context}/img/google-play-badge.png" alt=""></a></li>
                        </ul>
                    </div>
                    <!-- end footer list -->

                    <!-- footer list -->
                    <div class="col-6 col-sm-4 col-md-3">
                        <h6 class="footer__title">Resources</h6>
                        <ul class="footer__list">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Pricing Plan</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                    <!-- end footer list -->

                    <!-- footer list -->
                    <div class="col-6 col-sm-4 col-md-3">
                        <h6 class="footer__title">Legal</h6>
                        <ul class="footer__list">
                            <li><a href="#">Terms of Use</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Security</a></li>
                        </ul>
                    </div>
                    <!-- end footer list -->

                    <!-- footer list -->
                    <div class="col-12 col-sm-4 col-md-3">
                        <h6 class="footer__title">Contact</h6>
                        <ul class="footer__list">
                            <li><a href="tel:+18002345678">+1 (800) 234-5678</a></li>
                            <li><a href="mailto:support@moviego.com">support@flixgo.com</a></li>
                        </ul>
                        <ul class="footer__social">
                            <li class="facebook"><a href="#"><i class="icon ion-logo-facebook"></i></a></li>
                            <li class="instagram"><a href="#"><i class="icon ion-logo-instagram"></i></a></li>
                            <li class="twitter"><a href="#"><i class="icon ion-logo-twitter"></i></a></li>
                            <li class="vk"><a href="#"><i class="icon ion-logo-vk"></i></a></li>
                        </ul>
                    </div>
                    <!-- end footer list -->

                    <!-- footer copyright -->
                    <div class="col-12">
                        <div class="footer__copyright">
                            <small><a href="https://templatespoint.net" target="_blank">TemplatesPoint</a></small>

                            <ul>
                                <li><a href="#">Terms of Use</a></li>
                                <li><a href="#">Privacy Policy</a></li>
                            </ul>
                        </div>
                    </div>
                    <!-- end footer copyright -->
                </div>
            </div>
        </footer>
        <!-- end footer -->
    </jsp:body>
</t:layout>
