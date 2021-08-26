<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${not empty sessionScope.locale}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>
<fmt:setBundle basename="locale"/>

<fmt:message key="cocktail.name" var="locale_name"/>
<fmt:message key="cocktail.description" var="locale_description"/>
<fmt:message key="button.save" var="locale_save"/>
<fmt:message key="title.edit_cocktail" var="locale_title_edit_cocktail"/>

<html>
<head>
    <title>${locale_title_edit_cocktail}</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>
<div class="container h-100">
    <div class="row h-100 justify-content-center">
        <form id="cocktail-builder" action="controller?command=save_cocktail" method="post"
              enctype="multipart/form-data">
            <input class="form-control" value="${cocktail.name}" type="text" pattern=".{3,45}" required="required" name="name"
                   placeholder="<c:out value="${locale_name}"/> ">
            <input class="form-control" value="${cocktail.description}" type="text" required="required" name="description"
                   placeholder="<c:out value="${locale_description}"/> ">

            <input class="form-control-file" type="file" required="required" name="icon">
            <input type="hidden" name="id" value="${cocktail.cocktailId}">
            <p>Old ingredients :</p>
            <c:forEach items="${ingredients}" var="i">
                <p>${i.name}, ${i.amount} ${i.amountScale}</p>
            </c:forEach>
            <input class="form-control" type="submit" value="<c:out value="${locale_save}"/>">
        </form>
        <button id="plus">
            +
        </button>

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
        var select = $('<select class="form-control" name="dropdown" id="ingredients"/>');
        $.each(ingredients, function (i, val) {
            select.append($('<option />', {text: val.name + ", " + val.amountScale, value: val.ingredientId}));
        });
        form.append(select);
        form.append($('<input class="form-control" type="text" name="amount" required="required" pattern="\d{1,4}"/>'));
    }
</script>
</body>
</html>
