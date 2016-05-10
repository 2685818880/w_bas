package com.java.getconn;

import java.sql.*;

/**
 * 
 * @serial 获得数据库连接 JDBC的连接
 */

public class GetConnection {
	private static Connection conn = GetConnection.getConnection();
	private PreparedStatement pstat = null;
	private Statement stat = null;
	private ResultSet rs = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			conn = DriverManager
					.getConnection(
							"jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=w_bas",
							"sa", "sa");
			System.out.println("连接数据库成功");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			System.out.println("数据库驱动加载失败！");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return conn;
	}

	/***
	 * 更新语句
	 * **/
	public void Update(String sql) {
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/***
	 * 查询语句 createStatement
	 * **/
	public ResultSet find(String sql) {
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/*if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}*/
		return rs;

	}

	/**
	 * prepareStatement
	 ***/
	public ResultSet select(String sql) {
		try {
			pstat = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs = pstat.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
/*		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return rs;
	}
	/**
	 * prepareStatement 
	 ***/
	public int modify(String sql) {
		int temp = 0;
		try {
			pstat = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			temp =pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
	}
	public PreparedStatement pstat(String sql){
		PreparedStatement preparestat=null;
		try {
			preparestat=getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  preparestat;

		}
}
	