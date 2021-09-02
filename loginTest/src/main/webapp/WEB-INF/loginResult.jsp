<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="loginTest.User" %>
    <% 
    User loginUser = (User) session.getAttribute("loginUser");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン結果</title>
</head>
<body>
<h1>ログイン結果</h1>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getUserId() %>さん</p>
	<a href="/loginTest/Logout">ログアウトしてTOPへ</a>
	<% } else { %>
	<p>ログインに失敗しました</p>
	<a href="/loginTest/index.jsp">TOPへ</a>
	<% } %>
</body>
</html>