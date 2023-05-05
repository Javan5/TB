import ax
function getCurrency() {
        var result = "";
        const axios = require('axios').default;
        alert("Привет, мир!");
    console.log("wefqwevgfwrb")
        axios.get('http://localhost:8080/currency/getall')
        .then(function (response) {
        alert(response);
        result = response;
        console.log(response);
    })
        .catch(function (error) {
        alert(error);
        // обработка ошибки
        console.log(error);
    })
        .finally(function () {
        // выполняется всегда
    });
        const div = document.getElementsByClassName("dollars");
        div.innerHTML = result;
}