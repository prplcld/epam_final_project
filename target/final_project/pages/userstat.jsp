<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="locale"/>

<fmt:message key="stats.amount" var="locale_stats_amount"/>
<fmt:message key="stats.change_role" var="locale_change_role"/>
<fmt:message key="stats.login" var="locale_stats_login"/>
<fmt:message key="stats.role" var="locale_stats_role"/>
<fmt:message key="stats.mark" var="locale_stats_mark"/>

<fmt:message key="title.stats" var="locale_title_stats"/>
<!doctype html>
<html lang="en">
<head>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <title>${locale_title_stats}</title>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">${locale_stats_login}</th>
        <th scope="col">${locale_stats_mark}</th>
        <th scope="col">${locale_stats_amount}</th>
        <th scope="col">${locale_stats_role}</th>
        <th scope="col">${locale_change_role}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stat}" var="s">
        <tr>
            <td><c:out value="${s.login}"/></td>
            <td><c:out value="${s.averageMark}"/></td>
            <td><c:out value="${s.cocktailsAmount}"/></td>
            <td><c:out value="${s.role}"/></td>
            <td><a href="controller?command=change_user_role&userId=${s.userId}&upgrade=${s.role == 'USER' ? 'true' : 'false'}">${locale_change_role}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
