<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">OTP Login</h1>
        <hr />
    </div>
    <p> <a href="logout">Logout</a></p>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
    </div>
    <div class="sixteen columns">
        <form action="otplogin" method="post">

            <label>Username:</label>
            <input type="text" name="user.id"/>
            <label>Password:</label>
            <input type="password" name="user.password"/>
            <label>OTP:</label>
            <input type="text" name="user.otp"/>
            <input type="submit"/>

        </form>
    </div>
</div>
<jsp:include page="../../template/footer.jsp" />