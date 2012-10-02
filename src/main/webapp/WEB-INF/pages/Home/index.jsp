<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Simple page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
        <p><a href="login">Login</a></p>
        <p><a href="register">Register</a></p>
    </div>
    <div class="sixteen columns">
        <form action="cars" method="post">
            <ul>
                <li><label>Color:</label><input type="text" name="car.color"/></li>
                <li><label>Brand:</label><input type="text" name="car.brand"/></li>
                <li><input type="submit"/></li>
            </ul>
        </form>
    </div>

</div>
<jsp:include page="../../template/footer.jsp" />