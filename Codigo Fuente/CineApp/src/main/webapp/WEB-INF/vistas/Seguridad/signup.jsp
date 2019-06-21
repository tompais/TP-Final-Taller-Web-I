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
                            <form action="#" class="sign__form">
                                <a href="${context}" class="sign__logo">
                                    <h2 class="text-center text-white">Cine<span style="color: #ff5860">App</span></h2>
                                </a>

                                <div class="sign__group">
                                    <input type="text" class="sign__input" placeholder="Name">
                                </div>

                                <div class="sign__group">
                                    <input type="text" class="sign__input" placeholder="Email">
                                </div>

                                <div class="sign__group">
                                    <input type="password" class="sign__input" placeholder="Password">
                                </div>

                                <div class="sign__group sign__group--checkbox">
                                    <input id="remember" name="remember" type="checkbox" checked="checked">
                                    <label for="remember">I agree to the <a href="#">Privacy Policy</a></label>
                                </div>

                                <button class="sign__btn" type="button">Sign up</button>

                                <span class="sign__text">Already have an account? <a href="${context}/signin">Sign in!</a></span>
                            </form>
                            <!-- registration form -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout-seguridad>

