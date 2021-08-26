<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="button.info" var="locale_info"/>
<fmt:message key="title.cocktail_approval" var="locale_cocktail_approval"/>

<!doctype html>
<html lang="en">
<head>
    <title>${locale_cocktail_approval}</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
</head>
<body>

<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>



<div class="container-fluid padding">
    <div id="content" class="row padding">
        <c:forEach items="${cocktails}" var="c">
            <div class="col-md-3">
                <div class="card">
                    <img class="card-img-top" src="data:image/jpg;base64,${c.base64Icon}" style="width: 30%">
                    <div class="card-body">
                        <h4 class="card-title"><c:out value="${c.name}"/></h4>
                        <p class="card-text"><c:out value="${c.description}"/></p>
                        <a href="controller?command=cocktail_info&id=${c.cocktailId}"
                           class="btn-outline-secondary"><c:out value="${locale_info}"/></a>
                        <a href="controller?command=approve_cocktail&id=${c.cocktailId}"
                           class="btn-outline-secondary"><c:out value="approve"/></a>
                        <a href="controller?command=delete_cocktail&id=${c.cocktailId}&creator=${c.userId}"
                           class="btn-outline-secondary"><c:out value="delete"/></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
