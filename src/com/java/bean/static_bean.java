package com.java.bean;

import java.awt.Toolkit;

public class static_bean {
	/**
	 * 登录用户的名称
	 * */
	public static String username = "";
	/**
	 * 登录用户的密码
	 * */
	public static String password = "";
	/**
	 * 登录用户的权限
	 * */
	public static int statusNo = 0;
   /**
    * 显示器屏幕的分辨率
    * **/
	public static int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth();
	public static int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
			.getHeight();

}
