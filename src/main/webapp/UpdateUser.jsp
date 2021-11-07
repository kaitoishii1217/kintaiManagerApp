<%@ page import="model.entity.User,model.entity.WorkTime,java.time.LocalDateTime,java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
    User loginUser = (User) session.getAttribute("loginUser");
    String loginUserName = loginUser.getName();
    request.setCharacterEncoding("UTF-8");
    String userID = request.getParameter("userID");
    String name = request.getParameter("name");
    String adminFlag = request.getParameter("adminFlag");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者情報更新</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<div class="updateUser">
<body>
<h1>利用者情報更新</h1>
<p style="text-align:right">
ログインユーザー：<%= loginUserName %>
</p>
<form action="userInfo">
ID
<input type="text" name="userID" value=<%= userID %>>
<br><br>
ユーザー名
<input type="text" name="name" value=<%= name %>>
<br><br>
権限
<select name="adminFlag">
<option value="0"<%= adminFlag.equals("0") ? "selected":"" %>>一般ユーザー</option>
<option value="1"<%= adminFlag.equals("1") ? "selected":"" %>>システム管理者</option>
</select>
</form>
<br>
<a href="SelectAllUser">利用者一覧に戻る</a>
</body>
</div>
</html>