"use strict";

function escapeChar(message){
	// 「&」を変換して無害化
	inputText = inputText.replace("&", "&amp;");
	// 「<」を変換して無害化
	inputText = inputText.replace("<", "&lt;");
	// 「>」を変換して無害化
	inputText = inputText.replace(">", "&gt;");
	// 「"」を変換して無害化
	inputText = inputText.replace("\"", "&quot;");
	// 「'」を変換して無害化
	inputText = inputText.replace("'", "&#039;");

	return inputText;
}