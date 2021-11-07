package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAO.WorkTimeDAO;
import model.entity.User;
import model.entity.WorkTime;



@WebServlet("/GoMain")
public class GoMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public GoMain() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		String userID = loginUser.getUserID();
		
		WorkTimeDAO wkDao = WorkTimeDAO.getinstance();
		boolean existSTFlag = false;
		boolean existETFlag = false;
		List<WorkTime> workTimeList = new ArrayList<WorkTime>();
		try {
			wkDao.getConnection();
			wkDao.createSt();
			existSTFlag = wkDao.existStartTime(userID);
			existETFlag = wkDao.existEndTime(userID);
			workTimeList = wkDao.selectLastWeekWorkTimes(userID);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("existSTFlag", existSTFlag);
		request.setAttribute("existETFlag", existETFlag);
		request.setAttribute("workTimeList", workTimeList);
		RequestDispatcher dispacher = request.getRequestDispatcher("main_menu.jsp");
		dispacher.forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
