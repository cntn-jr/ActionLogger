package model;

import java.io.Serializable;

public class InformationAction implements Serializable{
	
	private String date,time,place,reason,remarks;
	
	public InformationAction() {}
	public InformationAction(String date,String time,String place,String reason,String remarks) {
		this.date=date;
		this.time=time;
		this.place=place;
		this.place=reason;
		this.remarks=remarks;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setBody(String remarks) {
		this.remarks = remarks;
	}
}
