<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="register.login" var="locale_login"/>
<fmt:message key="register.email" var="locale_email"/>
<fmt:message key="register.password" var="locale_password"/>
<fmt:message key="register.confirm_password" var="locale_confirm_password"/>
<fmt:message key="register.submit" var="locale_submit"/>
<fmt:message key="title.register" var="locale_title_register"/>

<!doctype html>
<html lang="en">
<head>
    <title>${locale_title_register}</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <link rel="stylesheet" href="pages/static/css/register.css">
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>



<div class="container register-form">
    <div class="form">
        <form class="form-content" action="controller?command=register_user" method="post">
            <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" name="username"  required="required" pattern=".{3,45}" class="form-control" placeholder="${locale_login}" value=""/>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" required="required" pattern="^(?=.{3,30}$)[^\s]+@[^\s]+\.[^\s]+$" class="form-control" placeholder="${locale_email}" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="password" name="password" required="required" class="form-control" placeholder="${locale_password}" value=""/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="confirmPassword" required="required" class="form-control" placeholder="${locale_confirm_password}" value=""/>
                        </div>
                    </div>
            </div>
            <button type="submit" class="btnSubmit">${locale_submit}</button>
        </form>
    </div>
</div>
</body>
</html>
