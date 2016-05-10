package com.java.bean;

public class w_bas_user {

	private int userNo;
	private String username;
	private String password;
	private String realName;
	private String realIpone;
	private int statusNo;
	private int team_id;
	
	public w_bas_user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public w_bas_user(int userNo, String username, String password,
			 int statusNo, int team_id) {
		super();
		this.userNo = userNo;
		this.username = username;
		this.password = password;
		this.statusNo = statusNo;
		this.team_id = team_id;
	}

	public w_bas_user(int userNo, String username, String password,
			String realName, String realIpone, int statusNo, int team_id) {
		super();
		this.userNo = userNo;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.realIpone = realIpone;
		this.statusNo = statusNo;
		this.team_id = team_id;
	}




	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealIpone() {
		return realIpone;
	}
	public void setRealIpone(String realIpone) {
		this.realIpone = realIpone;
	}
	public int getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	
	
}
