package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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



@WebServlet("/SelectThisMonthWorkTimes")
public class SelectThisMonthWorkTimes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SelectThisMonthWorkTimes() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		String userID = loginUser.getUserID();
		
		Integer searchYear = Integer.parseInt(request.getParameter("select_year"));
		Integer searchMonth = Integer.parseInt(request.getParameter("select_month"));
		Calendar cl = Calendar.getInstance();
		cl.set(searchYear, searchMonth-1, 1);
		
		WorkTimeDAO wkDao = WorkTimeDAO.getinstance();
		List<WorkTime> thisMonthWorkTimeList = new ArrayList<WorkTime>();
		try {
			wkDao.getConnection();
			wkDao.createSt();
			thisMonthWorkTimeList = wkDao.selectThisMonthWorkTimes(userID,cl);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("searchYear", searchYear);
		request.setAttribute("searchMonth", searchMonth);
		request.setAttribute("thisMonthWorkTimeList", thisMonthWorkTimeList);
		RequestDispatcher dispacher = request.getRequestDispatcher("SelectThisMonthWorkTimes.jsp");
		dispacher.forward(request, response);
	}

}
