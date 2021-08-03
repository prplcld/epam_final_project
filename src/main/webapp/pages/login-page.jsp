<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <link rel="stylesheet" href="pages/static/css/login-page.css">
</head>

<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="pages/static/image/user.png" id="icon" alt="User Icon"/>
        </div>

        ${message}
        <!-- Login Form -->
        <form action="controller?command=login_user" method="post">
            <input type="text" id="login" class="fadeIn second" name="username" placeholder="login">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>

    </div>
</div>
</body>