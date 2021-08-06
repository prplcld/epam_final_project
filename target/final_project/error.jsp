<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<p>${exception.getMessage()}</p>
<p><c:forEach var="stackTraceElem" items="${exception.stackTrace}">
    <c:out value="${stackTraceElem}"/><br/>
</c:forEach></p>
</body>
</html>
