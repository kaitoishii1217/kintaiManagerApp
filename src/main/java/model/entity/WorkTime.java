package model.entity;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTime {
	
	private String workDate;
	
	private String startTime;
	
	private String endTime;
	
	private DateFormat dateft =  new SimpleDateFormat("yyyy/MM/dd(E)");
	
	private DateFormat timeft = new SimpleDateFormat("HH:mm");

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date date) {
		this.workDate = dateft.format(date);
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(Time time) {
		this.startTime = timeft.format(time);
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(Time time) {
		this.endTime = timeft.format(time);
	}
	
	

}
