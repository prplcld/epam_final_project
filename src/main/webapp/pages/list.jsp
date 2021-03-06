<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<fmt:message key="title.list" var="locale_title"/>
<fmt:message key="button.info" var="locale_info"/>
<fmt:message key="button.search" var="locale_search"/>

<!doctype html>
<html lang="en">
<head>
    <%@include file="bootstrap-head-styles-scripts.jsp"%>
    <link rel="stylesheet" href="pages/static/css/bs-pagination.min.css">
    <link rel="stylesheet" href="pages/static/css/list.css">


    <title><c:out value="${locale_title}"/></title>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<script src="pages/static/js/pagination.min.js"></script>
<%@ include file="header.jsp"%>

<div  class="container-fluid padding">
    <div class="input-group">
        <input id="search" type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
               aria-describedby="search-addon" />
        <button id="search-btn" type="button" class="btn btn-outline-primary"><c:out value="${locale_search}"/></button>
    </div>
    <div id="content" class="row padding">

    </div>
</div>
<ul id="paginator" class="pagination"></ul>

<script>
    var items_per_page = 8;
    $(document).ready(function () {
        init_pagination();
        $.ajax({
            type: "post",
            url: "ajax?command=get_cocktails&page=1",
            success: function (result) {
                print_cocktails(result);
            }
        });
        init_search();
    });

    function init_search(){
        $("#search-btn").click(function (){
            var searchValue = $("#search").val();
            if (searchValue === "") {
                init_pagination();
                $.ajax({
                    type: "post",
                    url: "ajax?command=get_cocktails&page=1",
                    success: function (result) {
                        print_cocktails(result);
                    }
                });
            } else {
                $.ajax({
                    type: "post",
                    url: "ajax?command=search&value=" + searchValue,
                    success: function (result) {
                        $("#paginator").html("");
                        print_cocktails(result);
                    }
                });
            }
        });
    }

    function init_pagination(){
        $.ajax({
            type:"get",
            url: "ajax?command=get_cocktails_amount",
            success: function (result) {
                $('#paginator').pagination({
                    total: result,
                    current: 1,
                    length: 1,
                    size: 2,
                    prev:"&lt;",
                    next:"&gt;",
                    click:function (e) {
                        $.ajax({
                            type: "post",
                            url: "ajax?command=get_cocktails&page=" + this.current,
                            success: function (result) {
                                print_cocktails(result);
                            }
                        });
                    }
                });
            }
        });
    }

    function print_cocktails(res) {
        var content = $('#content');
        content.html("");
        var json = JSON.parse(res);
        $.each(json, function (index, value) {
           var data = "<div class=\"col-md-3\">" +
                "<div class=\"card\">" +
                "<img class=\"card-img-top\" src=\"data:image/jpg;base64," + value.base64Icon +"\" style=\"width: 30%\">" +
                "<div class=\"card-body\">" +
               "<h4 class=\"card-title\">" + value.name + "</h4>" +
               "<p class=\"card-text\">" + value.description +"</p>" +
               "<a href=\"controller?command=cocktail_info&id="+ value.cocktailId +"\" class=\"btn-outline-secondary\">" + "${locale_info}" + "</a>" +
               "</div>" +
               "</div>" +
               "</div>";
            content.append(data);
        });
    }
</script>

<%@include file="footer.jsp"%>
</body>
</html>