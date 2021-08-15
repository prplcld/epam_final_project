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

<html>
<head>
    <title>${locale_title_add_cocktail}</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<c:out value="${message}"/>
<form id="cocktail-builder" action="controller?command=add_cocktail" method="post" enctype="multipart/form-data">
    <input type="text" name="name" required="required" placeholder="<c:out value="${locale_name}"/> ">
    <input type="text" name="description" required="required" placeholder="<c:out value="${locale_description}"/> ">
    <input type="submit" value="<c:out value="${locale_create}"/> ">
    <input type="file" required="required" name="icon">
</form>
<button id="plus">
    +
</button>
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
        var select = $('<select name="dropdown" id="ingredients"/>');
        $.each(ingredients, function (i, val) {
            select.append($('<option />', {text: val.name, value: val.ingredientId}));
        });
        form.append(select);
        form.append($('<input type="text" name="amount" required="required" pattern="\d{1,4)"/>'));
    }
</script>
</body>
</html>
