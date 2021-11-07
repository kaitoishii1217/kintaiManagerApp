<%@ page import="model.entity.User,model.entity.EndTime,java.time.LocalDate,java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    User loginUser = (User) session.getAttribute("loginUser");
    EndTime endTime = (EndTime) session.getAttribute("endTime");
    int nowHour = endTime.getEndHour();
    int nowMinut = endTime.getEndMinut();
    String loginUserName = loginUser.getName();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    String today = LocalDate.now().format(dtf);
    boolean registerETFlag = true;
    if(request.getAttribute("registerETFlag")!=null){
    	registerETFlag = (boolean)request.getAttribute("registerETFlag");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退勤打刻</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<body vlink="#FFF">
<h1>退勤時刻登録</h1>
<p style="text-align:right">
ログインユーザー：<%= loginUserName %>
</p>
<%= today %>
<br>
業務終了時刻：
<form method="POST" action="RegisterEndTime">
<select name="end_hour">
<option value="0"<%= 0 == nowHour ? "selected":"" %>>00</option>
<option value="1"<%= 1 == nowHour ? "selected":"" %>>01</option>
<option value="2"<%= 2 == nowHour ? "selected":"" %>>02</option>
<option value="3"<%= 3 == nowHour ? "selected":"" %>>03</option>
<option value="4"<%= 4 == nowHour ? "selected":"" %>>04</option>
<option value="5"<%= 5 == nowHour ? "selected":"" %>>05</option>
<option value="6"<%= 6 == nowHour ? "selected":"" %>>06</option>
<option value="7"<%= 7 == nowHour ? "selected":"" %>>07</option>
<option value="8"<%= 8 == nowHour ? "selected":"" %>>08</option>
<option value="9"<%= 9 == nowHour ? "selected":"" %>>09</option>
<option value="10"<%= 10 == nowHour ? "selected":"" %>>10</option>
<option value="11"<%= 11 == nowHour ? "selected":"" %>>11</option>
<option value="12"<%= 12 == nowHour ? "selected":"" %>>12</option>
<option value="13"<%= 13 == nowHour ? "selected":"" %>>13</option>
<option value="14"<%= 14 == nowHour ? "selected":"" %>>14</option>
<option value="15"<%= 15 == nowHour ? "selected":"" %>>15</option>
<option value="16"<%= 16 == nowHour ? "selected":"" %>>16</option>
<option value="17"<%= 17 == nowHour ? "selected":"" %>>17</option>
<option value="18"<%= 18 == nowHour ? "selected":"" %>>18</option>
<option value="19"<%= 19 == nowHour ? "selected":"" %>>19</option>
<option value="20"<%= 20 == nowHour ? "selected":"" %>>20</option>
<option value="21"<%= 21 == nowHour ? "selected":"" %>>21</option>
<option value="22"<%= 22 == nowHour ? "selected":"" %>>22</option>
<option value="23"<%= 23 == nowHour ? "selected":"" %>>23</option>
</select>
時
<select name="end_minut">
<option value="0"<%= 0 == nowMinut ? "selected":"" %>>00</option>
<option value="5"<%= 5 == nowMinut ? "selected":"" %>>05</option>
<option value="10"<%= 10 == nowMinut ? "selected":"" %>>10</option>
<option value="15"<%= 15 == nowMinut ? "selected":"" %>>15</option>
<option value="20"<%= 20 == nowMinut ? "selected":"" %>>20</option>
<option value="25"<%= 25 == nowMinut ? "selected":"" %>>25</option>
<option value="30"<%= 30 == nowMinut ? "selected":"" %>>30</option>
<option value="35"<%= 35 == nowMinut ? "selected":"" %>>35</option>
<option value="40"<%= 40 == nowMinut ? "selected":"" %>>40</option>
<option value="45"<%= 45 == nowMinut ? "selected":"" %>>45</option>
<option value="50"<%= 50 == nowMinut ? "selected":"" %>>50</option>
<option value="55"<%= 55 == nowMinut ? "selected":"" %>>55</option>
</select>
分
<br><br>
<input class="register-btn" type="submit" value="登録">
</form>
<%= registerETFlag ?"":"勤務終了時刻を登録できませんでした。" %>
<br><br>
<a href="/KintaiManager/GoMain">メインメニューに戻る</a>
</body>
</html>