<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">OTP Login</h1>
        <hr />
    </div>
    <div id="userinfo-msg"></div>.: <a id="logout-btn" href="#" onclick="logout();">Logout</a> :.
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
    </div>
    <div class="sixteen columns">
        <form action="login" method="post">

            <label>Username:</label>
            <input type="text" name="user.id"/>
            <label>Password:</label>
            <input type="password" name="user.password"/>
            <label>OTP:</label>
            <input type="text" name="user.otp"/>
            <input type="submit"/>

        </form>
    </div>
    <div id="otp-secret-div"><a id="get-secret" href="secret">Generate OTP</a></div>

</div>
<jsp:include page="../../template/footer.jsp" />