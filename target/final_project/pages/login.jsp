<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
<h3> Login here </h3>
<form action="loginServlet">
    <table style="width: 20%">
        <tr>
            <td>UserName</td>
            <td>
                <input type="text" name="username" />
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="password" name="password" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Login" />
</form>
</body>
</html>
