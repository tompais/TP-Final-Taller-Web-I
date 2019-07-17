<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-seguridad>
    <jsp:attribute name="title">
		<title>Regístrese</title>
	</jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
            const pathRegistrar = "registrarUsuario";
            const pathHome = "${context}";
        </script>
        <script src="${context}/js/seguridad/registrar.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="sign section--bg" data-bg="${context}/img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="sign__content my-3">
                            <!-- registration form -->
                            <div class="sign__form">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="form-group sign__group">
                                    <input type="text" id="inputNombre" class="form-control sign__input" placeholder="Nombre"/>
                                    <div id="errorNombre" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>
                                
                                <div class="form-group sign__group">
                                    <input type="text" id="inputApellido" class="form-control sign__input" placeholder="Apellido"/>
                                    <div id="errorApellido" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="form-group sign__group">
                                    <input type="text" id="inputEmail" class="form-control sign__input" placeholder="Email"/>
                                    <div id="errorEmail" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>
                                
                                <div class="form-group sign__group">
                                    <input type="text" id="inputNickname" class="form-control sign__input" placeholder="Nickname"/>
                                    <div id="errorNickname" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="form-group sign__group">
                                    <div class="input-group" style="width: 280px;">
                                        <input type="password" id="inputPassword" class="form-control pwd sign__input" placeholder="Contraseña"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary rounded-0 border-0" onclick="showPassword(this)" style="background-color: #2b2b31;" type="button"><i class="fas fa-eye"></i></button>
                                        </div>
                                    </div>
                                    <div id="errorPassword" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="form-group sign__group">
                                    <div class="input-group" style="width: 280px;">
                                        <input type="password" id="inputRePassword" class="form-control pwd sign__input" placeholder="Confirmar Contraseña"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary rounded-0 border-0" onclick="showPassword(this)" style="background-color: #2b2b31;" type="button"><i class="fas fa-eye"></i></button>
                                        </div>
                                    </div>
                                    <div id="errorRePassword" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="form-group sign__group">
                                    <label for="inputFechaNacimiento" class="text-white-50">Fecha de Nacimiento</label>
                                    <div class="input-group" style="width: 280px;">
                                        <input type="text" name="fechaNacimiento" id="inputFechaNacimiento" class="form-control sign__input"/>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-secondary rounded-0 border-0" style="background-color: #2b2b31;" id="btnInputFechaNacimiento" type="button"><i class="fas fa-calendar-alt"></i></button>
                                        </div>
                                    </div>
                                    <div id="errorFechaNacimiento" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <div class="form-check sign__group sign__group--checkbox p-0">
                                    <input id="checkboxAgreePolitics" class="form-check-input" name="agreePolitics" type="checkbox">
                                    <label class="form-check-label" for="checkboxAgreePolitics">Estoy de acuerdo con las <a href="#">Políticas de privacidad</a></label>
                                    <div id="errorPoliticas" class="error bg-danger text-white-50 shadow rounded p-3 mt-2 text-center">
                                        <i class="fas fa-exclamation-triangle mr-2"></i>
                                        <span></span>
                                    </div>
                                </div>

                                <button id="btnRegistrar" class="sign__btn" type="button">Registrarse</button>

                                <span class="sign__text">¿Ya tienes cuenta? <a href="${context}/signin">¡Ingresa!</a></span>
                            </div>
                            <!-- registration form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout-seguridad>

