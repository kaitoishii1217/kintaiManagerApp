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
import model.entity.StartTime;

@WebServlet("/DicisionStartTime")
public class DicisionStartTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LocalTime now = LocalTime.now();
		int nowHour = now.getHour();
		int nowMinut = now.getMinute();
		
		if(nowMinut>=1 && nowMinut<=5) {
			nowMinut = 5;
		} if(nowMinut>=6 && nowMinut<=10) {
			nowMinut = 10;
		} if(nowMinut>=11 && nowMinut<=15) {
			nowMinut = 15;
		} if(nowMinut>=16 && nowMinut<=20) {
			nowMinut = 20;
		} if(nowMinut>=21 && nowMinut<=25) {
			nowMinut = 25;
		} if(nowMinut>=26 && nowMinut<=30) {
			nowMinut = 30;
		} if(nowMinut>=31 && nowMinut<=35) {
			nowMinut = 35;
		} if(nowMinut>=36 && nowMinut<=40) {
			nowMinut = 40;
		} if(nowMinut>=41 && nowMinut<=45) {
			nowMinut = 45;
		} if(nowMinut>=46 && nowMinut<=50) {
			nowMinut = 50;
		} if(nowMinut>=51 && nowMinut<=55) {
			nowMinut = 55;
		} if(nowMinut>=56 && nowMinut<=59) {
			nowMinut = 0;
			nowHour++;
		} if(nowMinut == 0) {
			nowMinut = 0;
		}
		
		StartTime startTime = new StartTime();
		startTime.setStartHour(nowHour);
		startTime.setStartMinut(nowMinut);
		session.setAttribute("startTime", startTime);
		RequestDispatcher dispacher = request.getRequestDispatcher("RegisterStartTime.jsp");
		dispacher.forward(request, response);
	}

}
