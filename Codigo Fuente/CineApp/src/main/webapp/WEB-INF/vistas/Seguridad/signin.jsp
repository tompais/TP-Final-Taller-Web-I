<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="baseURL" value="${pageContext.request.requestURL}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-seguridad>

    <jsp:attribute name="title">
		<title>Iniciar Sesión</title>
	</jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
            const pathHome = "${context}";
            const pathLogin = "loguearUsuario";
        </script>
        <script src="${context}/js/seguridad/signin.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="sign section--bg" data-bg="${context}/img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="sign__content">
                            <!-- authorization form -->
                            <div class="sign__form">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="form-group sign__group">
                                    <input type="text" class="form-control sign__input" id="inputEmailOrNick" placeholder="Email o Username"/>
                                    <div id="errorEmailOrNick" class="error mt-2 p-3 bg-danger shadow rounded text-white-50"><i class="fas fa-exclamation-triangle mr-2"></i><span></span></div>
                                </div>

                                <div class="form-group sign__group">
                                    <input type="password" class="form-control sign__input" id="inputPassword" placeholder="Contraseña"/>
                                    <div id="errorPass" class="error mt-2 p-3 shadow rounded text-white-50 bg-danger">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox">
                                    <label for="remember">Recordarme</label>
                                </div>

                                <button class="sign__btn" type="button" id="singInButton">Ingresar</button>

                                <span class="sign__text">¿No tienes una cuenta? <a href="${context}/signup">¡Regístrate!</a></span>

                                <span class="sign__text"><a href="#">¿Olvidaste tu contraseña?</a></span>
                            </div>
                            <!-- end authorization form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout-seguridad>