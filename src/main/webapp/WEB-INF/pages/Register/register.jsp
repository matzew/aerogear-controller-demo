<jsp:include page="../../template/header.jsp" />
<%@page pageEncoding="UTF-8" %>
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Logged in</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">delorean page</a></p>

        hello ${user.id} to the authentication page!
    </div>
</div>
<jsp:include page="../../template/footer.jsp" />