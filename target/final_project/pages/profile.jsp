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

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
            <div class="well profile">
                <div class="col-sm-12">
                    <div class="col-xs-12 col-sm-8">
                        <h2>${user.login}</h2>
                        <p><strong>Email: </strong> ${user.email} </p>
                        <p><strong>Role: </strong>
                            <span class="tags">${user.role}</span>
                        </p>

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
