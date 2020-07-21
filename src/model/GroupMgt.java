package model;

import java.io.Serializable;

public class GroupMgt implements Serializable{
	
	String group_id,group_name,admin_id;
	
	public GroupMgt() {}
	public GroupMgt(String group_id,String group_name,String admin_id) {
		this.group_id = group_id;
		this.group_name = group_name;
		this.admin_id = admin_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	
}
