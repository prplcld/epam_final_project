<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="bootstrap-head-styles-scripts.jsp"%>
</head>
<body>
    <%@include file="bootstrap-body-styles-scripts.jsp" %>
    <%@ include file="header.jsp"%>


    <form id="cocktail-builder" action="controller?command=add_cocktail" method="post" enctype="multipart/form-data">
        <input type="text" name="name" placeholder="name">
        <input type="text" name="description" placeholder="description">
        <input type="submit" value="create">
        <input type="file" name="icon">
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
            select.append($('<option />', {text : val.name, value: val.ingredientId}));
        });
        form.append(select);
        form.append($('<input type="text" name="amount"/>'));
    }
</script>
</body>
</html>
