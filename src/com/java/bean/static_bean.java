package com.java.bean;

import java.awt.Toolkit;

public class static_bean {
	/**
	 * ��¼�û�������
	 * */
	public static String username = "";
	/**
	 * ��¼�û�������
	 * */
	public static String password = "";
	/**
	 * ��¼�û���Ȩ��
	 * */
	public static int statusNo = 0;
   /**
    * ��ʾ����Ļ�ķֱ���
    * **/
	public static int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth();
	public static int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getHeight();

}
