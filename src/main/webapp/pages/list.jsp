<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="bootstrap-styles-scripts.jsp" %>
    <script type="text/javascript" src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
    <link rel="stylesheet" href="pages/static/css/list.css">
</head>
<body>
<%@ include file="header.jsp"%>

<!--- Cards -->
<div id="content" class="container-fluid padding">
    <div class="row padding">
        <c:forEach items="${cocktails}" var="c">
            <div class="col-md-4">
                <div class="card">
                    <img class="card-img-top" src="data:image/jpg;base64,${c.base64Icon}">
                    <div class="card-body">
                        <h4 class="card-title">${c.name}</h4>
                        <p class="card-text">${c.description}</p>
                        <a href="#" class="btn-outline-secondary">More</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div id="page-selection"></div>

<script>
    $('#page-selection').bootpag({
        total: 53,
        page: 2,
        maxVisible: 5,
        leaps: true,
        firstLastUse: true,
        first: '←',
        last: '→',
        wrapClass: 'pagination',
        activeClass: 'active',
        disabledClass: 'disabled',
        nextClass: 'next',
        prevClass: 'prev',
        lastClass: 'last',
        firstClass: 'first'
    }).on('page', function (event, num) {
        $("#content").html("Page " + num); // or some ajax content loading...
    });
</script>
</body>
</html>