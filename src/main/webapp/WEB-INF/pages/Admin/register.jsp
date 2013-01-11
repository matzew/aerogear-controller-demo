<jsp:include page="../../template/header.jsp" />
<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Restricted Admin page</h1>
        <hr />
    </div>
    <div class="sixteen columns">
       <p><a href="admin"> Register another user!</a></p>
    </div>
    <div class="sixteen columns">
        <p>This is the list of registered users :</p>
        <ul>
            <c:forEach var="user" items="${arrayList}">
                <li><a href="show/${user.id}">${user.id}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<jsp:include page="../../template/footer.jsp" />