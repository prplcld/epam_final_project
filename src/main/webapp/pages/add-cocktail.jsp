<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="cocktail.name" var="locale_name"/>
<fmt:message key="cocktail.description" var="locale_description"/>
<fmt:message key="cocktail.create" var="locale_create"/>
<fmt:message key="title.add_cocktail" var="locale_title_add_cocktail"/>
<!doctype html>
<html lang="en">
<head>
    <title>${locale_title_add_cocktail}</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<c:out value="${message}"/>
<div class="container h-100">
    <div class="row h-100 justify-content-center">
        <div class="col-10 col-md-8 col-lg-6">
            <form id="cocktail-builder" action="controller?command=add_cocktail" method="post"
                  enctype="multipart/form-data">
                <input class="form-control" type="text" name="name" required="required" pattern=".{3,45}"
                       placeholder="<c:out value="${locale_name}"/> ">
                <input class="form-control" type="text" name="description" required="required"
                       placeholder="<c:out value="${locale_description}"/> ">
                <input class="form-control-file" type="file" required="required" name="icon">
                <input class="form-control" type="submit" value="<c:out value="${locale_create}"/> ">
            </form>
            <button id="plus">
                +
            </button>
        </div>
    </div>
</div>


<script>
    var ingredients;
    $(document).ready(function () {
        $.ajax({
            type: "get",
            url: "ajax?command=get_ingredients",
            success: function (result) {
                ingredients = JSON.parse(result);
                add_dropdown();
            }
        });


        $('#plus').click(function () {
            add_dropdown();
        })
    });

    function add_dropdown() {
        var form = $('#cocktail-builder');
        var p = $('<p/>');
        var select = $('<select class="form-control" name="dropdown" id="ingredients"/>');
        p.append(select);
        $.each(ingredients, function (i, val) {
            select.append($('<option />', {text: val.name + ", " + val.amountScale, value: val.ingredientId}));
        });
        form.append(p);
        form.append($('<input class="form-control" type="text" name="amount" required="required" pattern="[0-9]{1,4)"/>'));
    }
</script>
</body>
</html>
