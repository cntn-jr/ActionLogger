package model;

import java.io.Serializable;

public class InformationAction implements Serializable{
	
	private String log_id,dateSbm,out_datetime,in_datetime,place,reason,remarks,user_id;
	
	public InformationAction() {}
	public InformationAction(String log_id,String dateSbm,String out_datetime,String in_datetime,String place,String reason,String remarks,String user_id) {
		this.log_id=log_id;
		this.dateSbm=dateSbm;
		this.out_datetime=out_datetime;
		this.in_datetime=in_datetime;
		this.place=place;
		this.reason=reason;
		this.remarks=remarks;
		this.user_id=user_id;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDateSbm() {
		return dateSbm;
	}
	public void setDateSbm(String dateSbm) {
		this.dateSbm = dateSbm;
	}
	public String getOut_datetime() {
		return out_datetime;
	}
	public void setOut_datetime(String out_datetime) {
		this.out_datetime = out_datetime;
	}
	public String getIn_datetime() {
		return in_datetime;
	}
	public void setIn_datetime(String in_datetime) {
		this.in_datetime = in_datetime;
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
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
