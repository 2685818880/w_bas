package com.java.dao;

import java.sql.ResultSet;
import java.util.Date;

public abstract class Dao extends DaoUtil {
	/**** 根据 表名,参数,条件语句,查询数据库中是否存在 
	 * @throws Exception **/
	public abstract boolean Exists(String table, String column, String where) throws Exception;

	/**** 查询 **/
	public abstract ResultSet findId(String table, String column, String where);

	/**** 添加 **/
	public abstract int insert(String table, String column, String where);

	/**** 删除 **/
	public abstract int delete(String table, String where);

	/**** 修改 **/
	public abstract int modify(String table, String column, String where);

	/** 将date类型时间 以字符串的形式显示* **/
	public abstract String DateFormat(Date date);


	

}
