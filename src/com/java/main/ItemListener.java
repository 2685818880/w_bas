package com.java.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.java.util.Calculator;
import com.java.util.Help;
import com.java.util.WriteRead;

/***
 * ����
 * **/
class ItemListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuitem = (JMenuItem) e.getSource();
		System.out.println("�����Ĳ˵��" + menuitem.getText());
		if ("���Ż�����Ϣ".equals(menuitem.getText())) {
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
		} else if ("�޸�����".equals(menuitem.getText())) {
			try {
				new UserModify();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}  else if ("������".equals(menuitem.getText())) {
			try {

				new Calculator();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("����...".equals(menuitem.getText())) {
			try {

				new Help();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		else if ("�˳�".equals(menuitem.getText())) {
			int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���?", "ȷ���˳���",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				System.exit(1);
			} else if (n == JOptionPane.NO_OPTION) {
				
			}
		}

	}

}