package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.UserDAO;
import model.entity.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		if (userID == null) {
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("main_menu.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                                                                   throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userID = request.getParameter("userID");
		String pass = request.getParameter("pass");
		
		boolean loginError = false;
		
		HttpSession session = request.getSession();
		
		User loginUser = null;
		UserDAO userDao = UserDAO.getinstance();
		
		try {
			userDao.getConnection();
			userDao.createSt();
			loginUser = userDao.loginCheck(userID, pass);
		} catch (NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
		
		if(loginUser.getUserID() != null) {
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect("/KintaiManager/GoMain");
		} else {
			loginError = true;
			request.setAttribute("loginError",loginError);
			RequestDispatcher dispacher = request.getRequestDispatcher("index.jsp");
			dispacher.forward(request, response);
		}
	}

}
