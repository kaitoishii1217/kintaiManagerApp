<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.removeAttribute("loginUser");
	boolean loginError = false;
	if(request.getAttribute("loginError")!=null){
	loginError = (boolean)request.getAttribute("loginError");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<body>
<div class="login">
	<h1>勤怠管理アプリ</h1>
    <form action="Login" method="post">
    	<input type="text" name="userID" placeholder="ユーザーID" required="required" />
        <input type="password" name="pass" placeholder="パスワード" required="required" />
        <span style="color:red"><%= loginError ?"ユーザ名もしくはパスワードが間違っています。":"" %></span><br>
        <button type="submit" class="login-btn">ログイン</button>
    </form>
</div>
</body>
</html>