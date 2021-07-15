<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="pages/static/css/bs-pagination.min.css">
    <link rel="stylesheet" href="pages/static/css/list.css">

    <title>Hello, world!</title>
</head>
<body>
<script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="pages/static/js/pagination.min.js"></script>
<%@ include file="header.jsp"%>

<!--- Cards -->
<div  class="container-fluid padding">
    <div id="content" class="row padding">
<%--        <c:forEach items="${cocktails}" var="c">--%>
<%--            <div class="col-md-3">--%>
<%--                <div class="card">--%>
<%--                    <img class="card-img-top" src="data:image/jpg;base64,${c.base64Icon}" style="width: 30%">--%>
<%--                    <div class="card-body">--%>
<%--                        <h4 class="card-title">${c.name}</h4>--%>
<%--                        <p class="card-text">${c.description}</p>--%>
<%--                        <a href="#" class="btn-outline-secondary">More</a>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
    </div>
</div>
<ul id="paginator" class="pagination"></ul>

<script>
    var items_per_page = 8;
    $(document).ready(function (){
        init_pagination();
        $.ajax({
            type: "post",
            url: "ajax?page=1",
            success: function (result) {
                print_cocktails(result);
            }
        });
    });

    function init_pagination(){
        $.ajax({
            type:"get",
            url: "ajax",
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
                            url: "ajax?page=" + this.current,
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
        $.each(JSON.parse(res), function (index, value) {
           var data = "<div class=\"col-md-3\">" +
                "<div class=\"card\">" +
                "<img class=\"card-img-top\" src=\"data:image/jpg;base64," + value.base64Icon +"\" style=\"width: 30%\">" +
                "<div class=\"card-body\">" +
               "<h4 class=\"card-title\">" + value.name + "</h4>" +
               "<p class=\"card-text\">" + value.description +"</p>" +
               "<a href=\"#\" class=\"btn-outline-secondary\">More</a>" +
               "</div>" +
               "</div>" +
               "</div>";
            content.append(data);
        });
    }

</script>
</body>
</html>