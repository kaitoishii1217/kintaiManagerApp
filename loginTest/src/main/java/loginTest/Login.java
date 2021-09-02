package loginTest;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response) 
                                                                   throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String unencrycedPass = request.getParameter("pass");
		
		User user = new User(userId,unencrycedPass);
		
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(user);
		
		if(isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
		}
		
		RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/loginResult.jsp");
		dispacher.forward(request, response);
	}

}
