package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.WorkTimeDAO;
import model.entity.User;


@WebServlet("/RegisterEndTime")
public class RegisterEndTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Integer endHour = Integer.parseInt(request.getParameter("end_hour"));
		Integer endMinut = Integer.parseInt(request.getParameter("end_minut"));
		LocalTime endTime = LocalTime.of(endHour,endMinut);
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		String userID = loginUser.getUserID();
		boolean registerETFlag = false;
		
		WorkTimeDAO wtDao = WorkTimeDAO.getinstance();
		
		try {
			wtDao.getConnection();
			wtDao.createSt();
			if(!wtDao.existEndTime(userID)) {
				registerETFlag = wtDao.registerEndTime(userID, endTime);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(registerETFlag == false) {
			request.setAttribute("registerETFlag",registerETFlag);
			RequestDispatcher dispacher = request.getRequestDispatcher("RegisterEndTime.jsp");
			dispacher.forward(request, response);
		} else {
			response.sendRedirect("/KintaiManager/GoMain");
		}
		
		

		
		
		
	}

}
