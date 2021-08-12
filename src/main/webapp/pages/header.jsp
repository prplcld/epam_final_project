<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<link rel="stylesheet" href="pages/static/css/list.css">

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>


<fmt:message key="header.add" var="locale_add"/>
<fmt:message key="header.language" var="locale_lang"/>
<fmt:message key="header.list" var="locale_list"/>
<fmt:message key="header.login" var="locale_login"/>
<fmt:message key="header.logout" var="locale_logout"/>
<fmt:message key="header.register" var="locale_register"/>
<fmt:message key="header.stat" var="locale_stat"/>
<fmt:message key="header.unapproved" var="locale_unapproved"/>

<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
    <div class="container-fluid">
        <%--    <a class="navbar-brand" href="#">--%>
        <%--      <img src="pages/static/image/logo.png"/>--%>
        <%--    </a>--%>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=users_stat"><c:out value="${locale_stat}"/> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=unapproved_cocktails"><c:out value="${locale_unapproved}"/></a>
                    </li>
                </c:if>

                <li class="nav-item">
                    <a class="nav-link" href="controller?command=go_to_add_cocktail"><c:out value="${locale_add}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=go_to_cocktails_list"><c:out value="${locale_list}"/></a>
                </li>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=go_to_login"><c:out value="${locale_login}"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=go_to_register"><c:out
                                value="${locale_register}"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="controller?command=profile&id=${sessionScope.user.userId}"><c:out
                                value="${sessionScope.user.login}"/></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=logout_user"><c:out value="${locale_logout}"/></a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:out value="${locale_lang}"/>
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <button form="locale" type="submit" name="locale" value="en">en</button>
                            <button form="locale" type="submit" name="locale" value="ru">ru</button>
                        </div>
                    </div>
                </li>
                <form id="locale" action="controller" method="post">
                    <input type="hidden" name="command" value="change_locale">
                </form>
            </ul>
        </div>
    </div>
</nav>
