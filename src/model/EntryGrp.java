package model;

import java.io.Serializable;

public class EntryGrp implements Serializable{
	
	String entry_id,user_id,group_id;
	boolean isEntry;
	
	public boolean isEntry() {
		return isEntry;
	}

	public void setEntry(boolean isEntry) {
		this.isEntry = isEntry;
	}

	public EntryGrp() {}

	public String getEntry_id() {
		return entry_id;
	}

	public void setEntry_id(String entry_id) {
		this.entry_id = entry_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	

}
