<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.05.2021
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contact list JSP Spring</title>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <%--    <script type="text/javascript" src="${pageContext.request.contextPath}"></script>--%>
</head>
<body>
<h1>Registration Form</h1>
<br>

<p>
    <input type="text" id="name" name="name" placeholder="Ismingizni yozing"><br>
    <input type="text" id="lastname" name="surname" placeholder="Familyangizni yozing"><br>
    <input type="text" id="age" name="age" placeholder="Yoshingizni kiriting"><br>
    <input type="text" id="password" name="password" placeholder="yangi parol kiriting"><br>
    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="telefon raqamingizni yozing"><br>
    <input type="text" id="role" name="role" placeholder="rolingiz bunga tegmang"><br>
    <!-- по нажатию на эту кнопку данные уйдут на сервер -->
    <button type="button" onclick="sendJSON()">Ro'yxatdan o'tish</button>
    <!-- а вот тут они появятся снова, но уже после обработки сервером -->
<p class="result" style="color:blue"></p>
</p>

<div class="container">
    <div class="row py-5">
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <label id="loginLabel" for="login">Loginni kiriting</label>
                    <br>
                    <input class="form-control" id="login" type="text">
                    <br class="break1">
                    <p class="log"></p>

                    <label id="passLabel" for="password">Parolni kiriting</label>
                    <br>
                    <input class="form-control" type="password" id="password2">
                    <br class="break2">
                    <p class="pass"></p>

                    <div class="text-right">
                        <button id="loginBtn" class="btn btn-success">Tizimga kirish</button>
                    </div>
                </div>
            </div>


            </form>
        </div>
    </div>

</div>


<%--<script type="text/javascript" src="${jakarta.servlet.jsp.PageContext}/js/index.js"></script>--%>

<jsp:include page="script.js"/>
<jsp:include page="bootstrap.css"/>
<%--</jsp:include>--%>


<%--</script>--%>


</body>
</html>
