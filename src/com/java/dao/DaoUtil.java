package com.java.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JComboBox;

import com.java.bean.w_bas_project;
import com.java.bean.w_bas_status;
import com.java.bean.w_bas_team;
import com.java.main.Bx_Mast;
import com.java.main.User_Mast;
import com.java.main.W_bas_Bxhzsr_Mast;

import java.sql.ResultSet;
import com.java.getconn.GetConnection;

public class DaoUtil {
	private GetConnection con = new GetConnection();
	private ResultSet rs = null;

	@SuppressWarnings("rawtypes")
	/**
	 * @param sql  HashMap  JComboBox  w_bas_team
	 * @return 指定类型 ， 获取数据绑定到JComboBox中，Put 到HashMap集合中，返回集合信息 
	 * **/
	public HashMap Bind(String table, String column, String where,
			HashMap<Integer, Object> ms, JComboBox combobox, Object obj) {
		StringBuffer sql = new StringBuffer("SELECT ").append(column);
		sql.append(" FROM ").append(table);
		if (!where.equals("")) {
			sql.append(" WHERE ").append(where);
		}
		rs = con.select(String.valueOf(sql));
		ms = new HashMap<Integer, Object>();
		System.out.println(obj);
		if (obj instanceof w_bas_team) {
			try {
				while (rs.next()) {
					combobox.addItem(rs.getString("teamName"));
					obj = new w_bas_team(rs.getInt("team_id"),
							rs.getString("teamName"));
					ms.put(((w_bas_team) obj).getTeam_id(), obj);
					System.out.println(ms);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (obj instanceof w_bas_project) {
			try {
				while (rs.next()) {
					combobox.addItem(rs.getString("proName"));
					obj = new w_bas_project(rs.getInt("project_id"),
							rs.getString("proName"));
					ms.put(((w_bas_project) obj).getProject_id(), obj);
					System.out.println(ms);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (obj instanceof w_bas_status) {
			try {
				while (rs.next()) {
					combobox.addItem(rs.getString("style"));
					obj = new w_bas_status(rs.getInt("statusNo"),
							rs.getString("style"));
					ms.put(((w_bas_status) obj).getStatusNo(), obj);
					System.out.println(ms);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ms;

	}

	/**
	 * @param
	 * @return Collection ms.values(); 接受Map集合所有的值
	 * ***/
	@SuppressWarnings("unchecked")
	public Collection<Object> team_All() {
		return User_Mast.team_Bind1.values();

	}

	public int team_search(String name) {
		for (Object team : team_All()) {
			if (((w_bas_team) team).getTeamName().equals(name)) {
				return ((w_bas_team) team).getTeam_id();
			}
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	public Collection<w_bas_team> team_All1() {
		return W_bas_Bxhzsr_Mast.team_Bind.values();

	}

	public int team_search1(String name) {
		for (w_bas_team team : team_All1()) {
			if (team.getTeamName().equals(name)) {
				return team.getTeam_id();
			}
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	public Collection<w_bas_project> project_All() {
		return W_bas_Bxhzsr_Mast.project_Bind.values();
	}

	public int project_search(String name) {
		for (w_bas_project project : project_All()) {
			if (project.getProName().equals(name)) {
				return project.getProject_id();
			}
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	public Collection<w_bas_status> status_All() {

		return User_Mast.status_Bind1.values();
	}

	public int status_search(String name) {
		for (w_bas_status status : status_All()) {
			if (status.getStatusName().equals(name)) {
				return status.getStatusNo();
			}
			System.out.println(status.getStatusNo());
		}

		return 0;

	}
	/***bx**/
	@SuppressWarnings("unchecked")
	public Collection<w_bas_status> bx_status_All() {

		return Bx_Mast.Bind.values();
	}

	public int bx_status_search(String name) {
		for (w_bas_status status : bx_status_All()) {
			if (status.getStatusName().equals(name)) {
				return status.getStatusNo();
			}
			System.out.println(status.getStatusNo());
		}

		return 0;

	}
	
	
	
}
