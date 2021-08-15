<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="register.login" var="locale_login"/>
<fmt:message key="register.password" var="locale_password"/>
<fmt:message key="register.submit" var="locale_submit"/>


<head>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <link rel="stylesheet" href="pages/static/css/login-page.css">
</head>

<body>

<div class="wrapper fadeInDown">
    <div id="formContent">

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="pages/static/image/user.png" id="icon" alt="User Icon"/>
        </div>

        ${message}
        <!-- Login Form -->
        <form action="controller?command=login_user" method="post">
            <input type="text" id="login" class="fadeIn second" name="username" required="required" placeholder="${locale_login}">
            <input type="password" id="password" class="fadeIn third" name="password" required="required" placeholder="${locale_password}">
            <input type="submit" class="fadeIn fourth" value="${locale_submit}">
        </form>

    </div>
</div>
</body>