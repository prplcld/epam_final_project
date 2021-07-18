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
            <p>${cocktail.description}</p>
        </div>
    </div>
</div>
</body>
</html>
