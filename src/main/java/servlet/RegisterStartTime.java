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


@WebServlet("/RegisterStartTime")
public class RegisterStartTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		Integer startHour = Integer.parseInt(request.getParameter("start_hour"));
		Integer startMinut = Integer.parseInt(request.getParameter("start_minut"));
		LocalTime startTime = LocalTime.of(startHour,startMinut);
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		String userID = loginUser.getUserID();
		boolean registerSTFlag = false;
		
		WorkTimeDAO wtDao = WorkTimeDAO.getinstance();
		
		try {
			wtDao.getConnection();
			wtDao.createSt();
			registerSTFlag = wtDao.registerStartTime(userID, startTime);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(registerSTFlag == false) {
			request.setAttribute("registerSTFlag", registerSTFlag);
			RequestDispatcher dispacher = request.getRequestDispatcher("RegisterStartTime.jsp");
			dispacher.forward(request, response);
		} else {
			response.sendRedirect("/KintaiManager/GoMain");
		}
		
		

		
		
		
	}

}
