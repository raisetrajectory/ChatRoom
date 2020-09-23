'use strict';

function logincheck(){
	var elmAge = document.getElementById("userId");
	var elmMessage = document.getElementById("password");
	var canSubmit = true;

	if(elmAge.value =="" || elmMessage.value==""){
		alert("ユーザーIDとパスワードを入力してください。");
		canSubmit = false;
	}
	return canSubmit;
}