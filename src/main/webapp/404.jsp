<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="title.not_found" var="locale_not_found"/>


<html>
<head>
    <title>${locale_not_found}</title>
    <%@include file="pages/bootstrap-head-styles-scripts.jsp" %>
</head>
<body>
<%@include file="pages/bootstrap-body-styles-scripts.jsp" %>
<%@ include file="pages/header.jsp" %>

<p>${locale_not_found}</p>
<p>${message}</p>
</body>
</html>
