package com.java.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.java.util.Calculator;
import com.java.util.Help;
import com.java.util.WriteRead;

/***
 * 监听
 * **/
class ItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuitem = (JMenuItem) e.getSource();
		System.out.println("你点击的菜单项：" + menuitem.getText());
		if ("部门基本信息".equals(menuitem.getText())) {
			try {
				new Project_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("WriteRead".equals(menuitem.getText())) {
			try {
				new WriteRead();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("修改密码".equals(menuitem.getText())) {
			try {
				new UserModify();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}  else if ("计算器".equals(menuitem.getText())) {
			try {

				new Calculator();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("关于...".equals(menuitem.getText())) {
			try {

				new Help();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		else if ("退出".equals(menuitem.getText())) {
			int n = JOptionPane.showConfirmDialog(null, "确认退出吗?", "确认退出框",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				System.exit(1);
			} else if (n == JOptionPane.NO_OPTION) {
				
			}
		}

	}

}