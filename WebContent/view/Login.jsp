<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.*" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
   <link rel="stylesheet" href="<%=request.getContextPath() %>/css/login.css">
</head>
<body>
	<div class="login_screen">
		<div class="login_title">Chat</br>Room</div>


			<div class="login_container">
			<form action="<%=request.getContextPath()%>/DoLogin" method="post">
				<div class="login_word_box">
					<p class="login_word">LOGIN</p>
				</div>
				<div class="input_username_box">
					<img src="<%=request.getContextPath() %>/picture/humanlogo.png" class="humanlogo">
					<input type="text" id="userId" name="userId" class="login_userid_text form-control" placeholder="USER ID">
				</div>
				<div class="input_password_box">
					<img src="<%=request.getContextPath() %>/picture/locklogo.png" class="locklogo">
					<input type="password" id="password" name="password" class="login_password_text form-control" placeholder="PASSWORD">
				</div>
				<div class="login_button_box">
					<input type="submit" value="LOGIN" id="login_button" class="login_button  btn btn-info" onclick="return logincheck();">
				</div>
			</form>
				<form action="<%=request.getContextPath()%>/NewAccount" method="post">
						<input type="hidden" name="new_userId"  id="new_userId" value="">
						<input type="hidden" name="new_password" id="new_password" value="">
						<input type="hidden" name="new_username" id="new_username" value="">
						<input type="submit" value="New Account" id="account_button" class="account_button  btn btn-outline-info" onclick="return newAccount();">
				</form>
			</div>
	</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/brankcheck.js" > </script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/newAccount.js" > </script>
</body>


</html>
