<%--
  ~ JBoss, Home of Professional Open Source
  ~ Copyright 2012, Red Hat, Inc., and individual contributors
  ~ by the @authors tag. See the copyright.txt in the distribution for a
  ~ full listing of individual contributors.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<jsp:include page="../../template/header.jsp" />
<div class="container">
    <div class="sixteen columns">
        <h1 class="remove-bottom" style="margin-top: 40px">Otp Login</h1>
        <hr />
    </div>
    <div class="sixteen columns">
        <p>maybe you should try the <a href="delorean">restricted delorean page</a></p>
    </div>
    <div class="sixteen columns">
        <form action="otp" method="post">

            <label>Username:</label>
            <input type="text" name="aeroGearUser.id"/>
            <label>Password:</label>
            <input type="password" name="aeroGearUser.password"/>
            <label>Otp:</label>
            <input type="text" name="aeroGearUser.otp"/>
            <input type="submit"/>

        </form>
    </div>

</div>
<jsp:include page="../../template/footer.jsp" />