<%@page pageEncoding="UTF-8" %>
<html>
<body>
<p>hello, security!</p>
<p>maybe you should try the <a href="delorean">delorean page</a></p>

<p>
<form action="signin" method="post">
    <ul>
        <li><label>Username:</label><input type="text" name="user.name"/></li>
        <li><label>Password:</label><input type="password" name="user.password"/></li>
        <li><input type="submit"/></li>
    </ul>
</form>
</p>
</body>
</html>