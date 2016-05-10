package com.java.bean;

public class w_bas_team {
	private int team_id;
	private String teamNo;
	private String teamName;
	public w_bas_team() {
		super();
		// TODO Auto-generated constructor stub
	}
	public w_bas_team( int team_id, String teamName) {
		super();
		this.team_id = team_id;
		this.teamName = teamName;
	}
	public w_bas_team(int team_id, String teamNo, String teamName) {
		super();
		this.team_id = team_id;
		this.teamNo = teamNo;
		this.teamName = teamName;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public String getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Override
	public String toString() {
		return "w_bas_team [team_id=" + team_id + ", teamNo=" + teamNo
				+ ", teamName=" + teamName + "]";
	}

	
	
}
