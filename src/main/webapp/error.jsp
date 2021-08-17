<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="title.error" var="locale_error"/>

<html>
<head>
    <%@include file="pages/bootstrap-head-styles-scripts.jsp" %>
    <title>${locale_error}</title>
</head>
<body>
<%@include file="pages/bootstrap-body-styles-scripts.jsp" %>
<%@ include file="pages/header.jsp" %>

<p>${exception.getMessage()}</p>
<p><c:forEach var="stackTraceElem" items="${exception.stackTrace}">
    <c:out value="${stackTraceElem}"/><br/>
</c:forEach></p>
</body>
</html>
