package model;

import java.io.Serializable;

public class User implements Serializable{
	
	private String user_id,passwordHash,name,address,tel_number,mail,group;
	
	public User(){}
	public User(String user_id,String passwordHash,String name,String address,String tel_number,String mail,String group) {
		this.user_id=user_id;
		this.passwordHash=passwordHash;
		this.name=name;
		this.address=address;
		this.tel_number=tel_number;
		this.mail=mail;
		this.group=group;
	}
	public User(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
}
