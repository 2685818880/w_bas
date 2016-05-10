package com.java.dao;

import java.sql.ResultSet;
import java.util.Date;

public abstract class Dao extends DaoUtil {
	/**** ���� ����,����,�������,��ѯ���ݿ����Ƿ���� 
	 * @throws Exception **/
	public abstract boolean Exists(String table, String column, String where) throws Exception;

	/**** ��ѯ **/
	public abstract ResultSet findId(String table, String column, String where);

	/**** ��� **/
	public abstract int insert(String table, String column, String where);

	/**** ɾ�� **/
	public abstract int delete(String table, String where);

	/**** �޸� **/
	public abstract int modify(String table, String column, String where);

	/** ��date����ʱ�� ���ַ�������ʽ��ʾ* **/
	public abstract String DateFormat(Date date);


	

}
