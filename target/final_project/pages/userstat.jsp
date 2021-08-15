<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<!doctype html>
<html lang="en">
<head>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <title>Stats</title>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<table class="table table-bordered">
    <thead>
    <tr>
        <th scope="col">Login</th>
        <th scope="col">Average mark</th>
        <th scope="col">Cocktails amount</th>
        <th scope="col">Role</th>
        <th scope="col">Change role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${stat}" var="s">
        <tr>
            <td><c:out value="${s.login}"/></td>
            <td><c:out value="${s.averageMark}"/></td>
            <td><c:out value="${s.cocktailsAmount}"/></td>
            <td><c:out value="${s.role}"/></td>
            <td><a href="controller?command=change_user_role&userId=${s.userId}&upgrade=${s.role == 'USER' ? 'true' : 'false'}">change role</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
