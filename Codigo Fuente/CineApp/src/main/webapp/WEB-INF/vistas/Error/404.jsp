<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-seguridad>
	<jsp:attribute name="title">
		<title>Error 404</title>
	</jsp:attribute>

    <jsp:body>
        <!-- page 404 -->
        <div class="page-404 section--bg" data-bg="img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="page-404__wrap">
                            <div class="page-404__content">
                                <h1 class="page-404__title">404</h1>
                                <p class="page-404__text">The page you are looking for not available!</p>
                                <a href="Home/index.html" class="page-404__btn">go back</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- end page 404 -->
    </jsp:body>
</t:layout-seguridad>

