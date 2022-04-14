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

    <%--    <script type="text/javascript" src="script.js"></script>--%>
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

<script>
    // эта функция сработает при нажатии на кнопку
    function sendJSON() {
        // с помощью jQuery обращаемся к элементам на странице по их именам
        let firstName = document.querySelector('#name');
        let lastName = document.querySelector('#lastname');
        let age = document.querySelector('#age');
        let password = document.querySelector('#password');
        let phoneNumber = document.querySelector('#phoneNumber');
        let role = document.querySelector('#role');
        // console.log(role.value)
        // а вот сюда мы поместим ответ от сервера
        let result = document.querySelector('.result');
        // создаём новый экземпляр запроса XHR
        let xhr = new XMLHttpRequest();
        // адрес, куда мы отправим нашу JSON-строку
        let url = "";
        // открываем соединение
        xhr.open("POST", url, true);
        // устанавливаем заголовок — выбираем тип контента, который отправится на сервер, в нашем случае мы явно пишем, что это JSON
        xhr.setRequestHeader("Content-Type", "application/json");
        // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
        xhr.onreadystatechange = function () {
            // если запрос принят и сервер ответил, что всё в порядке
            if (xhr.readyState === 4 && xhr.status === 200) {
                // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
                result.innerHTML = this.responseText
            }
        };
        // преобразуем наши данные JSON в строку
        var data = JSON.stringify({
            "firstName": firstName.value,
            "lastName": lastName.value,
            "age": age.value,
            "phoneNumber": phoneNumber.value,
            "password": password.value,
            "role": role.value
        });
        // когда всё готово, отправляем JSON на сервер
        xhr.send(data);
    }


</script>


</body>
</html>
