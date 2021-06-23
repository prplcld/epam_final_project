<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<head>
    <%@ include file="bootstrap-styles-scripts.jsp" %>
    <link rel="stylesheet" href="static/css/login-page.css">
</head>

<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="static/image/user.png" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
        <form>
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