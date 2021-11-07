package servlet;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.entity.EndTime;

@WebServlet("/DicisionEndTime")
public class DicisionEndTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LocalTime now = LocalTime.now();
		int nowHour = now.getHour();
		int nowMinut = now.getMinute();
		
		if(nowMinut>=0 && nowMinut<5) {
			nowMinut = 0;
		} if(nowMinut>=5 && nowMinut<10) {
			nowMinut = 5;
		} if(nowMinut>=10 && nowMinut<15) {
			nowMinut = 10;
		} if(nowMinut>=15 && nowMinut<20) {
			nowMinut = 15;
		} if(nowMinut>=20 && nowMinut<25) {
			nowMinut = 20;
		} if(nowMinut>=25 && nowMinut<30) {
			nowMinut = 25;
		} if(nowMinut>=30 && nowMinut<35) {
			nowMinut = 30;
		} if(nowMinut>=35 && nowMinut<40) {
			nowMinut = 35;
		} if(nowMinut>=40 && nowMinut<45) {
			nowMinut = 40;
		} if(nowMinut>=45 && nowMinut<50) {
			nowMinut = 45;
		} if(nowMinut>=50 && nowMinut<55) {
			nowMinut = 50;
		} if(nowMinut>=55 && nowMinut<60) {
			nowMinut = 55;
		}
		
		EndTime endTime = new EndTime();
		endTime.setEndHour(nowHour);
		endTime.setEndMinut(nowMinut);
		session.setAttribute("endTime", endTime);
		RequestDispatcher dispacher = request.getRequestDispatcher("RegisterEndTime.jsp");
		dispacher.forward(request, response);
	}

}
