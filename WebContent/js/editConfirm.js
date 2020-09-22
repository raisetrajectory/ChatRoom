"use strict";

function editConfirm(){
	var editMessage = prompt("編集内容を入力してください。",document.getElementById("message_text").textContent);
	if(editMessage == ""){
		alert("空文字は投稿できません。");
		return false;
	}else if(editMessage == document.getElementById("message_text").textContent){
		alert("同じ内容です。");
		return false;
	}else if(editMessage == null){
		return false;
	}else{
		document.getElementById("edit_message_text").value = editMessage;
		return true;
	}

}