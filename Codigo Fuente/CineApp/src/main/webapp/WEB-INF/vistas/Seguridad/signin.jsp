<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-seguridad>

    <jsp:attribute name="title">
		<title>Iniciar Sesión</title>
	</jsp:attribute>

    <jsp:body>
        <div class="sign section--bg" data-bg="${context}/img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="sign__content">
                            <!-- authorization form -->
                            <form:form action="validar-login" method="POST" modelAttribute="usuario" class="sign__form">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="sign__group">
                                    <form:input path="email" type="text" class="sign__input" id="inputEmail" placeholder="Email"/>
                                </div>
                                <div class="sign__group w-100">
                                <div id="errorEmail" class="error bg-danger"><i class="fas fa-exclamation-triangle"></i> Ingrese su Email </div>
                                <div id="errorEmail2" class="error bg-danger"><i class="fas fa-exclamation-triangle"></i> Escriba un Email
					                válido
					            </div>
					            </div>

                                <div class="sign__group">
                                    <form:input path="uPassword" type="password" id="inputPassword" class="sign__input" placeholder="Contraseña"/>
                                </div>
                                <div class="sign__group w-100">
                                <div id="errorPass" class="error bg-danger"><i class="fas fa-exclamation-triangle"></i> Por favor ingrese su
					                contraseña
					            </div>
					            <div id="errorPass2" class="error bg-danger"><i class="fas fa-exclamation-triangle"></i> Su contraseña debe ser
					                entre 6-15 digitos
					            </div>
					            </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox" checked="checked">
                                    <label for="remember">Recordarme</label>
                                </div>

                                <button class="sign__btn" id="btnIngresar" type="button">Ingresar</button>

                                <span class="sign__text">¿No tienes una cuenta? <a href="${context}/signup">Regístrate!</a></span>

                                <span class="sign__text"><a href="#">¿Olvidaste tu contraseña?</a></span>
                            </form:form>
                            <!-- end authorization form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="${context}/js/seguridad/validarLogin.js"></script>
    </jsp:body>
</t:layout-seguridad>