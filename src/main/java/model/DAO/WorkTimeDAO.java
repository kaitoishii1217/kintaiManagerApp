package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.entity.WorkTime;

public class WorkTimeDAO {
	
	private static WorkTimeDAO instance = new WorkTimeDAO();
	
	private Connection con;
	
	private Statement st;
	
	private WorkTimeDAO() {
	}
	
	public static WorkTimeDAO getinstance() {
		return instance;
	}
	
	public void getConnection() throws SQLException {
		ConnectionManager cm = ConnectionManager.getinstance();
		con = cm.connect();
	}
	
	public void createSt() throws SQLException {
		st = con.createStatement();
	}
	
	public void discon() {
		try {
			if (con != null) 
				con.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean registerStartTime(String userID,LocalTime startTime) {
		LocalDate today = LocalDate.now();
		String sql = "insert into work_time (work_date,userID,start_time) values ('" + today + "','" + userID + "','" + startTime +"');";
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean registerEndTime(String userID,LocalTime endTime) {
		LocalDate today = LocalDate.now();
		String sql = "update work_time set end_time = '" + endTime + "' "
				                               + "where userID = '" + userID + "' and work_date = '" + today + "';";
		try {
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean existStartTime(String userID) {
		boolean existStartTime = false;
		LocalDate today = LocalDate.now();
		String sql ="select start_time from work_time where work_date ='" + today + "' and userID ='" + userID + "';";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				existStartTime = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existStartTime;
	}
	
	public boolean existEndTime(String userID) {
		boolean existEndTime = false;
		LocalDate today = LocalDate.now();
		String sql ="select end_time from work_time where work_date ='" + today + "' and userID ='" + userID + "';";
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getDate("end_time")!=null) {
					existEndTime = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existEndTime;
	}
	
	public List<WorkTime> selectLastWeekWorkTimes(String userID) {
		
		List<WorkTime> lastWeekWorkTimes = new ArrayList<WorkTime>();
		ResultSet rs;
		Calendar cl = Calendar.getInstance();
		int today = cl.get(Calendar.DAY_OF_WEEK);
		cl.add(Calendar.DATE, Calendar.SUNDAY - today);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0 ; i<7 ; i++) {
			String searchDate = df.format(cl.getTime());
			String sql = "select * from work_time where "
					+ "work_date = '" + searchDate + "' and userID = '" + userID + "';";
			try {
				WorkTime wt = null;
				rs = st.executeQuery(sql);
				if(!rs.next()) {
					wt = new WorkTime();
					Date nullDate = cl.getTime();
					wt.setWorkDate(nullDate);
					lastWeekWorkTimes.add(wt);
				} else if(rs.getDate(1) != null) {
					wt = new WorkTime();
					wt.setWorkDate(rs.getDate(1));
					if(rs.getDate(3) != null) {
						wt.setStartTime(rs.getTime(3));
					}
					if(rs.getDate(4) != null) {
					wt.setEndTime(rs.getTime(4));
					}
					lastWeekWorkTimes.add(wt);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cl.add(Calendar.DATE, 1);
		}
		return lastWeekWorkTimes;
	}
	
	public List<WorkTime> selectThisMonthWorkTimes(String userID,Calendar thisMonth) {
		
		List<WorkTime> thisMonthWorkTimes = new ArrayList<WorkTime>();
		for(int i=1 ;i<=thisMonth.getActualMaximum(Calendar.DAY_OF_MONTH) ;i++) {
			WorkTime wt = new WorkTime();
			thisMonth.set(Calendar.DATE, i);
			wt.setWorkDate(thisMonth.getTime());
			thisMonthWorkTimes.add(wt);
		}
		
		ResultSet rs;
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd(E)");
		String thisMonthSt = df.format(thisMonth.getTime());
		String sql = "select * from work_time where "
					+ "work_date like '" + thisMonthSt + "%' and userID = '" + userID + "' "
							+ "order by work_date;";
		try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				for(WorkTime wt : thisMonthWorkTimes) {
					if(df2.format(rs.getDate(1)).equals(wt.getWorkDate())){
						if(rs.getDate(3) != null) {
							wt.setStartTime(rs.getTime(3));
						}
						if(rs.getDate(4) != null) {
							wt.setEndTime(rs.getTime(4));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return thisMonthWorkTimes;
	}

}
