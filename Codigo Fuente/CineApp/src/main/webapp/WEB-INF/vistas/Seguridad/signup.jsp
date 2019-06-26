<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout-seguridad>
    <jsp:attribute name="title">
		<title>Regístrese</title>
	</jsp:attribute>
    <jsp:body>
        <div class="sign section--bg" data-bg="${context}/img/section/section.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="sign__content">
                            <!-- registration form -->
                            <form:form action="registro" method="POST" modelAttribute="usuario">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="sign__group">
                                    <form:input path="nombre" type="text" class="sign__input" placeholder="Nombre"/>
                                </div>
                                
                                <div class="sign__group">
                                    <form:input path="apellido" type="text" class="sign__input" placeholder="Apellido"/>
                                </div>

                                <div class="sign__group">
                                    <form:input path="email" type="text" class="sign__input" placeholder="Email"/>
                                </div>
                                
                                <div class="sign__group">
                                    <form:input path="username" type="text" class="sign__input" placeholder="Nickname"/>
                                </div>

                                <div class="sign__group">
                                    <form:input path="uPassword" type="password" class="sign__input" placeholder="Contraseña"/>
                                </div>
                                
                                <div class="sign__group">
                                    <div class="input-group">
                            			<form:input path="fechaNacimiento" type="text" name="fechaNacimiento" id="inputFechaNacimiento" class="form-control"/>
			                            <div class="input-group-append">
			                                <button class="btn btn-outline-secondary" id="btnInputFechaNacimiento" type="button"><i class="fas fa-calendar-alt"></i></button>
			                            </div>
			                        </div>
                                </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox" checked="checked">
                                    <label for="remember">Estoy de acuerdo con las <a href="#">Políticas de privacidad</a></label>
                                </div>

                                <button class="sign__btn" type="submit">Registrarse</button>

                                <span class="sign__text">Ya tienes cuenta? <a href="${context}/signin">Ingresa!</a></span>
                            </form:form>
                            <!-- registration form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout-seguridad>

