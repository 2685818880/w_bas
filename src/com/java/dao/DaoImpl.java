package com.java.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.java.getconn.GetConnection;


public class DaoImpl extends Dao {
	private GetConnection con = new GetConnection();
	private ResultSet rs = null;
	private int num = 0;

	/**
	 * 根据 表名,参数,条件语句,查询数据库中是否存在
	 * @param table  column where 
	 * @return Boolean
	 * @throws Exception 
	 * **/
	public boolean Exists(String table, String column, String where) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT ").append(column);
		sql.append(" FROM ").append(table).append(" WHERE ").append(where);
		System.out.println(sql);	
		PreparedStatement pstat = con.pstat(String.valueOf(sql));
		ResultSet rs = pstat.executeQuery();
		return rs.next();

	}

	/**
	 * 查询
	 * @param table  column where 
	 * @return ResultSet
	 * **/
	public ResultSet findId(String table, String column, String where){
		StringBuffer sql = new StringBuffer("SELECT ").append(column);
		sql.append(" FROM ").append(table);
		if (!where.equals("")) {
			sql.append(" WHERE ").append(where);
		}
		System.out.println(sql);
		rs = con.find(String.valueOf(sql));
		return rs;
	}

	/**
	 * 添加
	 * @param table  column where 
	 * @return int
	 * **/
	public int insert(String table, String column, String where) {

		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(table).append(column).append(" values ").append(where);
		System.out.println(sql);
		num = con.modify(String.valueOf(sql));
		return num;

	}

	/**
	 * 修改
	 * @param table  column where 
	 * @return int
	 * **/
	public int modify(String table, String column, String where) {

		StringBuffer sql = new StringBuffer("UPDATE ");
		sql.append(table).append(" SET ").append(column).append(" WHERE ")
				.append(where);
		System.out.println(sql);
		num = con.modify(String.valueOf(sql));
		return num;

	}

	/**
	 * 删除
	 * @param table  where 
	 * @return int
	 * **/
	public int delete(String table, String where) {
		StringBuffer sql = new StringBuffer("DELETE ");
		sql.append(" FROM ").append(table).append(" WHERE ").append(where);
		System.out.println(sql);
		num = con.modify(String.valueOf(sql));
		return num;

	}

	/**
	 * 将date类型时间 以字符串的形式显示
	 * @param Date 
	 * @return String
	 * **/
	public String DateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(date);
	}


	/**
	 * 模糊查询
	 * 
	 * @param name
	 * @return 
	 */
	
	/* StringBuffer strbu = new StringBuffer("");
	  String ss ="select bxNo,users.userName,project.proName,team.teamName,bxStart,bxBack,bxTime,carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy from w_user users, w_bas_bx bx ,w_bas_project project ,w_bas_team team"; String str1 = bTextField.getText().toString().trim();
	  String str2 = cTextField.getText().toString().trim(); 
	  if (("").equals(str1)&&("").equals(str2)) 
	  { 
	    strbu.append(ss);
	  }else{ 
	    strbu.append(ss).append("where"); 
	  } 
	  boolean lock =false; 
	  char c = '"'; 
	  if (str1!= null||!("").equals(str1)) {
	  strbu.append( " bx.bxNO like '%" + str1 + "%' "); 
	  lock = true; } 
	  if (str2!= null||!("").equals(str2)) { 
	  if (lock == true) {
	   strbu.append(" and users.userName like '%" + str2 + "%' "); 
	   } else { 
	   strbu.append( " users.userName like '%" + str2 + "%'");
	    lock = true;
        } 
        } String sql=c+ss+c;
	 */
}
