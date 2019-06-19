<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        <title>Login</title>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
                    <%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
                    <%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
                <form:form action="validar-login" method="POST" modelAttribute="usuario">
                    <h3 class="form-signin-heading">Taller Web I</h3>
                    <hr class="colorgraph">
                    <br>

                    <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
                    <form:input path="email" id="email" type="email" class="form-control"/>
                    <form:input path="uPassword" type="password" id="password" class="form-control"/>

                    <button class="btn btn-lg btn-primary btn-block" Type="Submit">Login</button>
                </form:form>

                    <%--Bloque que es visible si el elemento error no está vacío	--%>
                <c:if test="${not empty error}">
                    <h4><span>${error}</span></h4>
                    <br>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:layout>