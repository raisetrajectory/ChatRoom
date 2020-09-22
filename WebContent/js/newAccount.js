"use strict";

function newAccount(){
	var newAccountId = prompt("ユーザーIDを入力してください。\n※5文字以上10文字以下");
	console.log(newAccountId );
	if(newAccountId.length>=5 && newAccountId.length<=10){
		var newAccountPass = prompt("パスワードを入力してください。\n8文字以上20文字以下");
		if(newAccountPass.length>=8 && newAccountPass.length<=10){
			var newAccountName = prompt("ユーザー名を入力してください。\n3文字以上20文字以下");
			if(newAccountName.length >=3 && newAccountName.length<=20){
				document.getElementById("new_userId").value = newAccountId;
				document.getElementById("new_password").value = newAccountPass;
				document.getElementById("new_username").value = newAccountName;
				return true;
			}else{
				alert("ユーザー名は3文字以上20文字以下で入力してください。");
				return false;
			}
		}else{
			alert("パスワードは8文字以上20文字以下で入力してください。");
			return false;
		}
	}else{
		alert("ユーザーIDは5文字以上10文字以下で入力してください。")
		return false;
	}
}