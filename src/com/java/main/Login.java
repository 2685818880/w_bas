package com.java.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

import com.java.bean.static_bean;
import com.java.dao.Dao;
import com.java.dao.DaoImpl;

@SuppressWarnings("serial")
public class Login extends JFrame {
	static Login l = null;
	private JTextField textfield1, textfield2;
	private JLabel label, label2;
	private JButton button;
	String sql = "";
	
	Dao dao = new DaoImpl();

	public Login() throws Exception {
		super();
		setTitle("user login");
		
		setBounds(600, 100, 570, 470);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		label = new JLabel("username：");
		textfield1 = new JTextField(10);
		label2 = new JLabel("userpass：");
		textfield2 = new JTextField(10);
		new JLabel("提示信息");
		button = new JButton("enter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean exists = dao.Exists("w_user","*","username='"+textfield1.getText()+"' and password='"+textfield2.getText()+"'");
					System.out.println("login:"+exists);
					if (exists) {
						ResultSet rs = dao.findId("w_user","statusNo","username='"+textfield1.getText()+"' and password='"+textfield2.getText()+"'");
						rs.next();
						static_bean.statusNo= rs.getInt("statusNo");
						static_bean.username=textfield1.getText();
						static_bean.password=textfield2.getText();
						Manager t = new Manager();
						t.setVisible(true);
						System.out.println("having");
						// System.exit(0);
					} else {
						System.out.println("not having");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		container.add(label);
		container.add(textfield1);
		container.add(label2);
		container.add(textfield2);
		container.add(button);
		setSize(250, 150);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public static void main(String[] args) throws Exception {
		new Login();
	}

}
