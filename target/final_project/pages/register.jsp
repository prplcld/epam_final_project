<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Register</title>
    <%@include file="bootstrap-head-styles-scripts.jsp" %>
    <link rel="stylesheet" href="pages/static/css/register.css">
</head>
<body>
<%@include file="bootstrap-body-styles-scripts.jsp" %>
<%@ include file="header.jsp" %>

<div class="container register-form">
    <div class="form">
        <form class="form-content" action="controller?command=register_user" method="post">
            <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="text" name="login" class="form-control" placeholder="Login" value=""/>
                        </div>
                        <div class="form-group">
                            <input type="email" name="email" class="form-control" placeholder="Email" value=""/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Your Password *" value=""/>
                        </div>
                        <div class="form-group">
                            <input type="password" name="confirmPassword" class="form-control" placeholder="Confirm Password *" value=""/>
                        </div>
                    </div>
            </div>
            <button type="submit" class="btnSubmit">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
