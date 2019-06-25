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
                            <form:form action="validar-login" method="POST" modelAttribute="usuario">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="sign__group">
                                    <form:input path="email" type="text" class="sign__input" placeholder="Email"/>
                                </div>

                                <div class="sign__group">
                                    <form:input path="uPassword" type="password" class="sign__input" placeholder="Contraseña"/>
                                </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox" checked="checked">
                                    <label for="remember">Recordarme</label>
                                </div>

                                <button class="sign__btn" type="submit">Ingresar</button>

                                <span class="sign__text">¿No tienes una cuenta? <a href="${context}/signup">Regístrate!</a></span>

                                <span class="sign__text"><a href="#">¿Olvidaste tu contraseña?</a></span>
                            </form:form>
                            <!-- end authorization form -->
                            <c:if test="${not empty error}">
			        			<h4><span>${error}</span></h4>
			        			<br>
		        			</c:if>	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout-seguridad>