<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Register</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
        <p>you can also try the <a href="admin">restricted admin page</a></p>
    </div>
    <div class="sixteen columns">
        <form action="register" method="post">

            <label>Username:</label>
            <input autocomplete="off" type="text" name="aeroGearUser.id"/>
            <label>Password:</label>
            <input autocomplete="off" type="password" name="aeroGearUser.password"/>
            <input type="submit"/>

        </form>
    </div>

</div>
<jsp:include page="../../template/footer.jsp" />