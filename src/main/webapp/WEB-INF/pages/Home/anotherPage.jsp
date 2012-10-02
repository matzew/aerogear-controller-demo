<jsp:include page="../../template/header.jsp" />
<%@page pageEncoding="UTF-8" %>
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Restricted page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        hello, this is a ${car.color} ${car.brand} page!
    </div>
</div>
<jsp:include page="../../template/footer.jsp" />