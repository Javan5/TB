function validateForm() {

    var url = document.forms["addItemForm"]["furl"].value;
    var title = document.forms["addItemForm"]["ftitle"].value;
    var author = document.forms["addItemForm"]["fauthor"].value;
    var publish = document.forms["addItemForm"]["fpublish"].value;
    var month = document.forms["addItemForm"]["fyear"].value;
    var annotation = document.forms["addItemForm"]["fannotation"].value;

    if(url == "") {
        alert("Необходимо ввести url");
        return false;
    }
    else if(!checkBank(title)) {
        alert("Необходимо ввести название банка");
        return false;
    }    
    else if(!checkSum(author)) {
        alert("Сумма не должна содержать что либо, кроме цифр");
        return false;
    }
    else if(!checkProcent(publish)) {
        alert("Введите целое число или дробь");
        return false;
    }
    else if(month < 1 || month > 36) {
        alert("Выберите корректный минимальный срок вклада.");
        return false;
    } 
    else if(annotation == "") {
        alert("Необходимо ввести описание продукта");
        return false;
    }
    else {
        var checkboxes = document.getElementsByName("id[]");
        var checkCount = 0;
        for(var i = 0; i < checkboxes.length; i++) {
            if(checkboxes[i].checked) {
                checkCount++;
            }
        }
		
        if(checkCount == 0) {
            alert ("Необходимо выбрать тип банковского продукта");
            return false;
        }
        else {
            return true;
        }
    }

}

function checkSum(stroka) {
    if(stroka.trim() != '') {
        if(/[^0-9\.]/i.test(stroka)) {
            return false;
        }
        return true;
    }
    else {
        return false;
    }
}

function checkBank(stroka) {
    if(stroka.trim() != '') {
        if(/[^\A-zА-яЁё]/i.test(stroka)) {
            return false;
        }
        else {
            var words = stroka.split(' ');
            words[0].trim();
            var ch = words[0][0];

            if(ch === ch.toUpperCase()) {
                return true;
            }
            else {
                return false;
            }
        }
    }
    else {
        return false;
    }
}

function checkProcent(stroka) {
    if(stroka.trim() != '') {
        if(/[^\d{1,3}(?:,\d{3})*(?:\.\d{1,2})]/i.test(stroka)) {
				return false;
        }
		if((stroka < 0 || stroka > 20)) {
			return false;
		}
		return true;
    }
    else {
        return false;
    }
}