<%@ page import="model.entity.User,model.entity.WorkTime,java.time.LocalDateTime,java.time.format.DateTimeFormatter,java.net.URLEncoder,java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
    User loginUser = (User) session.getAttribute("loginUser");
    String loginUserName = loginUser.getName();
    List<User> userList = (List<User>)session.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者一覧</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<body vlink="#FFF">
<h1>利用者一覧</h1>
<p style="text-align:right">
ログインユーザー：<%= loginUserName %>
</p>
<% if(session.getAttribute("userList") != null) {%>
<br>
<table class="tables" border="1">
    <thead>
	    <tr>
	         <th>ユーザーID</th>
	         <th>ユーザー名</th>
	         <th>利用者区分</th>
	    </tr>
    </thead>
    <tbody>
        <% for(User user : userList) { 
             String userID = user.getUserID();
             String name = user.getName();
             String encodeName = URLEncoder.encode(name,"UTF-8");
             String adminFlag = Integer.toString(user.getAdminFlag());%>
                <tr>
                    <td><a href = "UpdateUser.jsp?userID=<%= userID %>&name=<%= encodeName %>&adminFlag=<%= adminFlag %>"><%= userID %></a></td>
                    <td><%= name %></td>
                    <td><%= adminFlag %></td>
                </tr>
         <% } %>
    </tbody>
</table>
<% } %>
<br>
<a href="/KintaiManager/GoMain">メインメニューに戻る</a>
</body>
</html>