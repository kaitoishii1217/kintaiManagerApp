<%@ page import="model.entity.User,model.entity.WorkTime,java.time.LocalDateTime,java.time.format.DateTimeFormatter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% 
    User loginUser = (User) session.getAttribute("loginUser");
    String loginUserName = loginUser.getName();
    int adminFlag = loginUser.getAdminFlag();
    DateTimeFormatter datefmt = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    DateTimeFormatter timefmt = DateTimeFormatter.ofPattern("HH:mm:ss");
    String nowDate = LocalDateTime.now().format(datefmt);
    String nowTime = LocalDateTime.now().format(timefmt);
    boolean existSTFlag = false;
    if(request.getAttribute("existSTFlag")!=null){
    	existSTFlag = (boolean)request.getAttribute("existSTFlag");
    }
    boolean existETFlag = false;
    if(request.getAttribute("existETFlag")!=null){
    	existETFlag = (boolean)request.getAttribute("existETFlag");
    }
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインメニュー</title>
<link rel="stylesheet" href="common/css/stylesheet.css">
</head>
<body>
<div class=main_menu>
<h1>メインメニュー</h1>
<p style="text-align:right">
ログインユーザー：<%= loginUserName %>
</p>
<br>
<p class=now_time>
<%= nowDate%>
<br>
<%= nowTime%></p>
<table>
<tr>
<td><form action="DicisionStartTime" method="get">
<input class="kintai-btn" type="submit"<%= existSTFlag ? "disabled":"" %> value="出勤">　　　
</form></td>
<td><form action="DicisionEndTime" method="get">
　　　<input class="kintai-btn" type="submit"<%= existETFlag ? "disabled":"" %> value="退勤">
</form></td>
</tr>
</table>
<br><br>
<table class="tables" border="1">
    <thead>
	    <tr>
	         <th>出勤日</th>
	         <th>開始時刻</th>
	         <th>終了時刻</th>
	    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="WorkTime" items="${workTimeList}" >
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
<br><br>
<section>
<a href="SelectThisMonthWorkTimes.jsp" class="btn_10"><span>勤怠一覧</span></a>
<br>
<% if(adminFlag == 1) { %>
<a href="SelectAllUser" class="btn_10"><span>利用者一覧</span></a>
<% } %>
<br>
<a href="Logout" class="btn_10"><span>ログアウト</span></a>
</section>
</div>
</body>
</html>