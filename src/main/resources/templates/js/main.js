
$(document).ready(function () {
    // $("br")[1].after("<p>toptim</p>");
    console.log($("br")[1]);
    console.log($(".break1"));
    $("#loginBtn").click(function () {
        let logValue=$("#login").val();
        let passValue=$("#password2").val();
        if (logValue===""){
            $(".log").text("Login qatorini to'ldiring!").addClass("text-danger text-uppercase ");
            $(".break1").remove();
        }else {
            // console.log($(".log").next()[0]==$("#passLabel"));
            $(".log").text("").after("<br>");

        }
        if (passValue===""){
            $(".pass").text("Parol qatorini to'ldiring!").addClass("text-danger text-uppercase");
            $(".break2").remove();
        }else {
            $(".pass").text("");

        }
    })

    


})
function sendJSON() {
    // с помощью jQuery обращаемся к элементам на странице по их именам
    let firstName = document.querySelector('#name');
    let lastName = document.querySelector('#lastname');
    let password = document.querySelector('#password');
    let phoneNumber = document.querySelector('#phoneNumber');
    let role = document.querySelector('#role');
    // а вот сюда мы поместим ответ от сервера
    let result = document.querySelector('.result');
    // создаём новый экземпляр запроса XHR
    let xhr = new XMLHttpRequest();
    // адрес, куда мы отправим нашу JSON-строку
    let url = "http://localhost:8080/psy/user/register";
    // открываем соединение
    xhr.open("POST", url, true);
    // устанавливаем заголовок — выбираем тип контента, который отправится на сервер, в нашем случае мы явно пишем, что это JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
    xhr.onreadystatechange = function () {
        // если запрос принят и сервер ответил, что всё в порядке
        if (xhr.readyState === 4 && xhr.status === 200) {
            // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
            result.innerHTML = this.responseText;
        }
    };
    // преобразуем наши данные JSON в строку
    console.log("ishladi")
    var data = JSON.stringify({
        "firstName": firstName.value, "lastName": lastName.value, "phoneNumber": phoneNumber.value, "password": password.value, "role": role.value
    });
    // когда всё готово, отправляем JSON на сервер
    xhr.send(data);
}