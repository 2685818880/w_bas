package com.java.bean;

public class w_bas_status {

	private int statusNo ;
	private String statusName ;
	public w_bas_status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public w_bas_status(int statusNo, String statusName) {
		super();
		this.statusNo = statusNo;
		this.statusName = statusName;
	}
	@Override
	public String toString() {
		return "w_bas_status [statusNo=" + statusNo + ", statusName="
				+ statusName + "]";
	}
	public int getStatusNo() {
		return statusNo;
	}
	public void setStatusNo(int statusNo) {
		this.statusNo = statusNo;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}
