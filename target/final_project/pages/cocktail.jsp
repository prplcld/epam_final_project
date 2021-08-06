<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<title>Cocktail</title>
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
            <h2><a href="controller?command=profile&id=${cocktail.userId}">creator</a> </h2>
            <p>${cocktail.description}</p>
            <c:forEach items="${ingredients}" var="i">
                <p>${i.name}, ${i.amount}, ${i.amountScale}</p>
            </c:forEach>
        </div>
    </div>
</div>

<c:if test="${sessionScope.user != null }">
    <div class="container">
        <div class="post-comments">
            <form action="controller?command=leave_comment&cocktailId=${cocktail.cocktailId}" method="post">
                <div class="form-group">
                    <label>Your Comment</label>
                    <textarea name="comment" class="form-control" rows="3"></textarea>
                </div>

                <select name="rating" class="form-select">
                    <option selected value="1">1*</option>
                    <option value="2">2*</option>
                    <option value="3">3*</option>
                    <option value="4">4*</option>
                    <option value="5">5*</option>
                </select>

                <button type="submit" class="btn btn-default">Send</button>
            </form>
        </div>
    </div>
</c:if>

<div class="container">
    <div class="row">
        <h2>Comments</h2>
    </div>
    <hr>
    <c:forEach items="${comments}" var="c">
        <div class="row comment">
            <div class="head">
                <small><a class='user' href="controller?command=profile&id=${c.userId}">${c.login}</a> 30.10.2017 12:13</small>
                ${c.mark}*
            </div>
            <p>${c.text}</p>
        </div>
    </c:forEach>
    <hr>
</div>
</body>
</html>
