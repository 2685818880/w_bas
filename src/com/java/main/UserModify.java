package com.java.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import com.java.bean.static_bean;
import com.java.dao.Dao;
import com.java.dao.DaoImpl;

@SuppressWarnings("serial")
public class UserModify extends JFrame {
	static UserModify l = null;
	private JTextField textfield1, textfield2, textfield3;
	private JLabel label, label2, label3, label4;
	private JButton button;
	String sql = "";
	public static String s = "";
	Dao dao = new DaoImpl();

	public UserModify() throws Exception {
		super();
		setTitle("�޸�����");
		setBounds(600, 100, 570, 470);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		label = new JLabel("�����룺");
		textfield1 = new JTextField(10);
		label2 = new JLabel("�����룺");
		textfield2 = new JTextField(10);
		label3 = new JLabel("ȷ�����룺");
		textfield3 = new JTextField(10);
		new JLabel("��ʾ��Ϣ");
		button = new JButton("ȷ��");
		label4 = new JLabel();
		label4.setText("�û�����" + static_bean.username);

		final ResultSet rs = dao.findId("w_user", "*", "username='"
				+ static_bean.username + "'");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(textfield1.getText())
						|| "".equals(textfield2.getText())
						|| "".equals(textfield2.getText())) {
					JOptionPane.showMessageDialog(null, "�ı���Ϣ����ΪNull");

				} else {
					if (!static_bean.password.toString().equals(
							textfield1.getText())) {
						JOptionPane.showMessageDialog(null, "����������");
					} else {
						if (!textfield2.getText().equals(textfield3.getText())) {
							JOptionPane.showMessageDialog(null, "�����������벻һ��");
						} else {
							try {
								rs.next();
								rs.getInt("statusNo");
								rs.getInt("team_id");
								rs.getInt("userNo");
							} catch (SQLException e2) {
								e2.printStackTrace();
							}

							int t = 0;
							try {
								t = dao.modify("w_user", "password='"
										+ textfield2.getText() + "',username='"
										+ static_bean.username + "',statusNo="
										+ rs.getInt("statusNo") + ",team_id="
										+ rs.getInt("team_id") + "", "userNo='"
										+ rs.getInt("userNo") + "'");

							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (t > 0) {

								label4.setText("modify success");
								JOptionPane.showMessageDialog(null,
										"modify success");
								System.out.println("modify success");
								System.exit(0);
							}
						}
					}
				}
			}

		});
		container.add(label);
		container.add(textfield1);

		container.add(label2);
		container.add(textfield2);

		container.add(label3);
		container.add(textfield3);

		container.add(label4);

		container.add(button);
		container.setLayout(new FlowLayout(FlowLayout.LEADING));

		setSize(220, 220);
		setVisible(true);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) throws Exception {
		new UserModify();
	}

}
