<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedReader" %>

<!DOCTYPE html>
<%
	List<MessageDto> dtoList = (List<MessageDto>)request.getAttribute("ShowAllMessage");
	HttpSession sess           = request.getSession();
	UserInfoDto userInfoOnSession = (UserInfoDto)sess.getAttribute("LOGIN_INFO");
	if(userInfoOnSession != null){
%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta charset="UTF-8">
	<title>chatroom</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/room.css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">ChatRoom</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
      </li>
      <li class="nav-item">
      </li>
      <li class="nav-item">
      </li>
    </ul>
    <span class="navbar-text">
      ログインユーザー：<%=userInfoOnSession.getUserName() %>
    </span>
    <span class="navbar-button">
  	<form action ="DoLogout">
  		<input type="submit" class="exit_button btn btn-warning" value="LOGOUT">
  	</form>
    </span>
  </div>
</nav>


	<div class="chat-container">


		<div class="submit_box">
				<form action="InputMessage" enctype="multipart/form-data" method="post">
						<div class="textarea_box">
							<textarea id="input_message_text" name="message" size="40" maxlength="500" class="input_message_text form-control"></textarea>
						</div>
						<input type="submit" id="message_submit"value="投稿する" class="send_message_button btn btn-info" onclick="return brankCheck();">
							<label class="image_button_label input-group-btn">
                            <span class="image_button btn btn-success">
                                画像を添付する
                                <input type="file" name="image" accept="image/*" class="image_submit_button" id="image_submit" style="display:none">
                            </span>
                        </label>
				</form>

				<!--  <form action="DeleteMessage" method="get">
					<div class="delete_button_box">
							<input type="submit" id="delete_button"value="全件削除" class="send_delete_button">
					</div>
				</form>-->

			</div>
		<div class="chat_screen">
			<%
			for(int i = 0; i<dtoList.size(); i++){
				String userName = dtoList.get(i).getUserName();
				String messageId = dtoList.get(i).getMessageId();
				String message = dtoList.get(i).getMessage();
				// 「&」を変換して無害化
				message = message.replace("&", "&amp;");
				// 「<」を変換して無害化
				message = message.replace("<", "&lt;");
				// 「>」を変換して無害化
				message = message.replace(">", "&gt;");
				// 「"」を変換して無害化
				message = message.replace("\"", "&quot;");
				// 「'」を変換して無害化
				message = message.replace("'", "&#039;");
				message = message.replace("indention","<br>");
				Timestamp time = dtoList.get(i).getTime();
				String image = null;
				if(dtoList.get(i).getByte() != null){
					byte[] encoded = Base64.getEncoder().encode(dtoList.get(i).getByte());
					image = new String(encoded);
				}

			 	if(userName.equals(userInfoOnSession.getUserName())){%>
				<div class="my_chat_box">
						<p class="username"> <%=userName%></p>
					<%if(image != null){ %>
						<img src="data:image/*;base64,<%=image%> ">
					<%} %>
						<p class="my_message" id="message_text"> <%=message%></p>
						<p class="created_at"> <%=time%></p>
					<div class="buttons">
						<form action="EditMessage" method="post">
								<input type="hidden" name="message_id" value="<%=messageId %>">
								<input type="hidden" name="edit_message_text" id="edit_message_text" value="" >
								<input type="submit" class="edit_message_button btn btn-outline-secondary" value="メッセージを編集する" onclick="return editConfirm();">
						</form>
						<form action="DeleteMessage" method="post">
								<input type="hidden" name="message_id" value="<%=messageId %>">
								<input type="submit" class="delete_message_button btn btn-outline-secondary" value="メッセージを削除する" onclick="return confirmCheck();">
						</form>
						</div>
						</div>
						<%} else{%>
						<div class="other_chat_box">
						<p class="other_username"> <%=userName%></p>
						<%if(image != null){ %>
							<img src="data:image/*;base64,<%=image%> ">
						<%} %>
							<p class="other_message" id="message_text"> <%=message%></p>
								<p class="created_at"><%=time%> </p>
						</div>
					<%} %>

		<% } %>
	<%}else{
			//未ログインの場合ログイン画面へ遷移
			response.sendRedirect(request.getContextPath()+"/view/Login.jsp");
			}
		%>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Messagebrank.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/deleteConfirm.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/editConfirm.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/xssEscape.js" > </script>
</body>
</html>
