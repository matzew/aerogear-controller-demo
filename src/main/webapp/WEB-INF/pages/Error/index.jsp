<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">General error page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>${requestScope['org.jboss.aerogear.controller.exception']}</p>
    </div>
</div>
<jsp:include page="../../template/footer.jsp" />