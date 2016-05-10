package com.java.bean;

public class w_bas_project {
private int project_id;
private String proNo;
private String proName;
public w_bas_project() {
	super();
	// TODO Auto-generated constructor stub
}
public w_bas_project(int project_id,  String proName) {
	super();
	this.project_id = project_id;
	this.proName = proName;
}

public w_bas_project(int project_id, String proNo, String proName) {
	super();
	this.project_id = project_id;
	this.proNo = proNo;
	this.proName = proName;
}
public int getProject_id() {
	return project_id;
}
public void setProject_id(int project_id) {
	this.project_id = project_id;
}
public String getProNo() {
	return proNo;
}
public void setProNo(String proNo) {
	this.proNo = proNo;
}
public String getProName() {
	return proName;
}
public void setProName(String proName) {
	this.proName = proName;
}
@Override
public String toString() {
	return "w_bas_project [project_id=" + project_id + ", proNo=" + proNo
			+ ", proName=" + proName + "]";
}



}
