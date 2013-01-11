<jsp:include page="../../template/header.jsp" />
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Restricted Admin page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <form action="remove" method="post">
            <p>${aeroGearUser.id}</p>
            <input type="hidden" id="aeroGearUser.id" name="aeroGearUser.id" value="${aeroGearUser.id}">
            <input type="submit" value="Delete"/> or go <a href="../admin">back</a>.
        </form>

    </div>

</div>
<jsp:include page="../../template/footer.jsp" />