console.log("読み込み成功");
function brankCheck(){
	console.log("Message");
		var inputMessage  = document.getElementById('input_message_text').value;
		var canSubmit = true;
		if(inputMessage == ""){
			alert("メッセージを入力してください。");
			canSubmit = false;
		}
		return canSubmit;
}