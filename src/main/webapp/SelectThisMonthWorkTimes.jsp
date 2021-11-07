<%@ page import="model.entity.User,model.entity.WorkTime,java.time.LocalDateTime,java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
    User loginUser = (User) session.getAttribute("loginUser");
    String loginUserName = loginUser.getName();
    int year = LocalDateTime.now().getYear();
    int month = LocalDateTime.now().getMonthValue();
    int searchYear =0;
    int searchMonth =0;
    if(request.getAttribute("searchYear")!=null){
    	searchYear = (int)request.getAttribute("searchYear");
    } else {
    	searchYear = LocalDateTime.now().getYear();
    }
    if(request.getAttribute("searchMonth")!=null){
    	searchMonth = (int)request.getAttribute("searchMonth");
    } else {
    	searchMonth = LocalDateTime.now().getMonthValue();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤怠一覧</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<body vlink="#FFF">
<h1>勤怠一覧</h1>
<p style="text-align:right">
ログインユーザー：<%= loginUserName %>
</p>
<form method="POST" action="SelectThisMonthWorkTimes">
<select name="select_year">
<% for(int i = 2020; i<2100 ; i++) { %>
<option value="<%= i %>" <%= searchYear == i ? "selected":"" %>><%= i %></option>
<% } %>
</select>
年
<select name="select_month">
<option value="1" <%= searchMonth == 1 ? "selected":"" %>>01</option>
<option value="2" <%= searchMonth == 2 ? "selected":"" %>>02</option>
<option value="3" <%= searchMonth == 3 ? "selected":"" %>>03</option>
<option value="4" <%= searchMonth == 4 ? "selected":"" %>>04</option>
<option value="5" <%= searchMonth == 5 ? "selected":"" %>>05</option>
<option value="6" <%= searchMonth == 6 ? "selected":"" %>>06</option>
<option value="7" <%= searchMonth == 7 ? "selected":"" %>>07</option>
<option value="8" <%= searchMonth == 8 ? "selected":"" %>>08</option>
<option value="9" <%= searchMonth == 9 ? "selected":"" %>>09</option>
<option value="10" <%= searchMonth == 10 ? "selected":"" %>>10</option>
<option value="11" <%= searchMonth == 11 ? "selected":"" %>>11</option>
<option value="12" <%= searchMonth == 12 ? "selected":"" %>>12</option>
</select>
月
<br>
<input type="submit" value="検索">
</form>
<br>
----------------------------------------------------------------------------------------------------------------------------------------
<% if(request.getAttribute("thisMonthWorkTimeList") != null) {%>
<br>
<table class=tables border="1">
    <thead>
	    <tr>
	         <th>出勤日</th>
	         <th>開始時刻</th>
	         <th>終了時刻</th>
	    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="WorkTime" items="${thisMonthWorkTimeList}" >
                <td><c:out value="${WorkTime.workDate}" /></td>
                <td><c:choose>
                <c:when  test="${empty WorkTime.startTime}">
                --:--
                </c:when>
                <c:otherwise>
                <c:out value="${WorkTime.startTime}" />
                </c:otherwise>
                </c:choose></td>
                <td><c:choose>
                <c:when  test="${empty WorkTime.endTime}">
                --:--
                </c:when>
                <c:otherwise>
                <c:out value="${WorkTime.endTime}" />
                </c:otherwise>
                </c:choose></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<% } %>
<br>
<a href="/KintaiManager/GoMain">メインメニューに戻る</a>
</body>
</html>