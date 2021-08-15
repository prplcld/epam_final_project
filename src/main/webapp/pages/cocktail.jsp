<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="title.cocktail" var="locale_title_cocktail"/>
<fmt:message key="cocktail.creator" var="locale_creator"/>
<fmt:message key="cocktail.delete" var="locale_delete"/>
<fmt:message key="cocktail.send" var="locale_send"/>
<fmt:message key="comment.comments" var="locale_comments"/>
<fmt:message key="comment.your_comment" var="locale_your_comment"/>

<!doctype html>
<html lang="en">
<title>${locale_title_cocktail}</title>
<head>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <link rel="stylesheet" href="pages/static/css/cocktail.css">
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-5">
            <img src="data:image/jpg;base64,${cocktail.base64Icon}">
        </div>
        <div class="col-md-7">
            <h2>${cocktail.name}</h2>
            <h2><a href="controller?command=profile&id=${cocktail.userId}">${locale_creator}</a> </h2>
            <p>${cocktail.description}</p>
            <c:forEach items="${ingredients}" var="i">
                <p>${i.name}, ${i.amount} ${i.amountScale}</p>
            </c:forEach>
            <a href="controller?command=delete_cocktail&id=${cocktail.cocktailId}&creator=${cocktail.userId}">${locale_delete}</a>
            <a href="controller?command=edit_cocktail&id=${cocktail.cocktailId}&creator=${cocktail.userId}">edit</a>
        </div>
    </div>
</div>

<c:if test="${sessionScope.user != null }">
    <div class="container">
        <div class="post-comments">
            <form action="controller?command=leave_comment&cocktailId=${cocktail.cocktailId}" method="post">
                <div class="form-group">
                    <label>${locale_your_comment}</label>
                    <textarea name="comment" class="form-control" rows="3"></textarea>
                </div>

                <select name="rating" class="form-select">
                    <option selected value="1">1*</option>
                    <option value="2">2*</option>
                    <option value="3">3*</option>
                    <option value="4">4*</option>
                    <option value="5">5*</option>
                </select>

                <button type="submit" class="btn btn-default">${locale_send}</button>
            </form>
        </div>
    </div>
</c:if>

<div class="container">
    <div class="row">
        <h2>${locale_comments}</h2>
    </div>
    <hr>
    <c:forEach items="${comments}" var="c">
        <div class="row comment">
            <div class="head">
                <small><a class='user' href="controller?command=profile&id=${c.userId}"><c:out value="${c.login}"/></a></small>
                <c:if test="${sessionScope.user.userId == comment.userId}">
                    <p>
                        <small><a href="controller?command=delete_comment&id=${c.commentId}&cocktailId=${cocktail.cocktailId}&userId=${c.userId}">${locale_delete}</a> </small>
                    </p>

                </c:if>
                ${c.mark}*
            </div>
            <p>${c.text}</p>
        </div>
    </c:forEach>
    <hr>
</div>
</body>
</html>
