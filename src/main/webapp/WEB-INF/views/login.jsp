<%--
  Created by IntelliJ IDEA.
  User: Developer
  Date: 4/8/2023
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
<h1>Login Page</h1>

<form method="post" action="/login">
    <input type="text" name="username" placeholder="Username"/>
    <br/>
    <input type="password" name="password" placeholder="Password"/>
    <br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>


