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


</head>
<body>
<h1>Login Form</h1>
<p>

    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="telefon raqamingizni yozing"><br>
    <input type="text" id="password" name="password" placeholder="parolingizni kiriting"><br>

    <!-- по нажатию на эту кнопку данные уйдут на сервер -->
    <button onclick="sendJSON()">Проверить JSON</button>
    <!-- а вот тут они появятся снова, но уже после обработки сервером -->
<p class="result" style="color:blue"></p>
</p>
<script>
    function sendJSON() {
        // с помощью jQuery обращаемся к элементам на странице по их именам
        let password = document.querySelector('#password');
        let phoneNumber = document.querySelector('#phoneNumber');
        let result = document.querySelector('.result');
        let answer;
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
                let parse=JSON.parse(xhr.responseText);
                console.log("this answer"+parse);
                console.log('result', JSON.parse(xhr.responseText).token)
                result.innerHTML = this.responseText;
                // xhr.setRequestHeader("Authorization","Bearer ")
            }
        };
        // преобразуем наши данные JSON в строку

        var data = JSON.stringify({
            "phoneNumber": phoneNumber.value, "password": password.value
        });
        // когда всё готово, отправляем JSON на сервер
        xhr.send(data);
    }
    class ApiResponse{
        constructor(message,token,success) {
            this.message=message
            this.token=token
            this.success=success
        }
    }
</script>
</body>
</html>