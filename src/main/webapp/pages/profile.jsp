<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <title>Profile</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="profile.email" var="locale_email"/>
<fmt:message key="profile.role" var="locale_role"/>
<fmt:message key="profile.rating" var="locale_rating"/>

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
            <div class="well profile">
                <div class="col-sm-12">
                    <div class="col-xs-12 col-sm-8">
                        <h2><c:out value="${user.login}"/></h2>
                        <p><strong><c:out value="${locale_email}"/>: </strong> ${user.email} </p>
                        <p><strong><c:out value="${locale_role}"/>: </strong>
                            <span class="tags">${user.role}</span>
                        </p>
                        <p><strong><c:out value="${locale_rating}"/>:</strong>${mark}*</p>
                        <c:if test="${sessionScope.user != null}">
                            <c:if test="${sessionScope.user.userId != user.userId}">
                                <p>
                                    <strong>Your rating:</strong>
                                    <c:if test="${userMark != null}">
                                        ${userMark.mark}*
                                    </c:if>
                                    <c:if test="${userMark == null}">
                                        You haven't rated this user yet
                                    </c:if>
                                </p>
                                <form action="controller?command=rate_user&id=${user.userId}" method="post">
                                    <select name="rating" class="form-select">
                                        <option selected value="1">1*</option>
                                        <option value="2">2*</option>
                                        <option value="3">3*</option>
                                        <option value="4">4*</option>
                                        <option value="5">5*</option>
                                    </select>

                                    <button type="submit" class="btn btn-default">Rate</button>
                                </form>

                            </c:if>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container-fluid padding">
    <div id="content" class="row padding">
        <c:forEach items="${cocktails}" var="c">
            <div class="col-md-3">
                <div class="card">
                    <img class="card-img-top" src="data:image/jpg;base64,${c.base64Icon}" style="width: 30%">
                    <div class="card-body">
                        <h4 class="card-title">${c.name}</h4>
                        <p class="card-text">${c.description}</p>
                        <a href="controller?command=cocktail_info&id=${c.cocktailId}"
                           class="btn-outline-secondary">More</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
