package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.java.bean.static_bean;
import com.java.bean.w_bas_status;
import com.java.bean.w_bas_team;
import com.java.dao.Dao;
import com.java.dao.DaoImpl;

public class User_Mast {

	/**
	 * @param args
	 */
	private JLabel la1, ID;
	private JTextField aTextField;
	private JTextField bTextField;
	private JTextField cTextField;
	private JTextField dTextField;
	private static JComboBox combobox1;
	private static JComboBox combobox2;
	int t = 0;
	private Vector<String> v = null;
	ResultSet rs = null;
	private String str1, str2;
	public static HashMap<Integer, Object> ms;
	public static HashMap<Integer, Object> masm;
	private w_bas_team team = new w_bas_team();
	private w_bas_status status = new w_bas_status();

	@SuppressWarnings("rawtypes")
	public static HashMap status_Bind1;
	@SuppressWarnings("rawtypes")
	public static HashMap team_Bind1;
	final Dao dao = new DaoImpl();
	// final DaoUtil daoutil = new DaoUtil();
	static {

	}

	public User_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("user_mast");
		jf.setBounds((int) (static_bean.width*0.3), 100, 670, 580);
		final JTable jt = new JTable();
		String header[] = { "ID", "用户名称", "用户密码","realName","realIpone", "用户状态", "部门名称" };
		final DefaultTableModel md = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setModel(md);

		jt.setRowSorter(new TableRowSorter<DefaultTableModel>(md));// 设置表格的排序器
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = jt.getSelectedRow();

				Object oa = md.getValueAt(selectedRow, 0);
				Object ob = md.getValueAt(selectedRow, 1);
				Object oc = md.getValueAt(selectedRow, 2);
				Object od = md.getValueAt(selectedRow, 3);
				Object oe = md.getValueAt(selectedRow, 4);
				Object of = md.getValueAt(selectedRow, 5);
				Object og = md.getValueAt(selectedRow, 6);

				ID.setText(oa.toString());
				aTextField.setText(ob.toString());// 将值赋值给文本框
				bTextField.setText(oc.toString());// 将值赋值给文本框
				cTextField.setText(od.toString());// 将值赋值给文本框
				dTextField.setText(oe.toString());// 将值赋值给文本框
				
				combobox1.setSelectedItem(of.toString());
				combobox2.setSelectedItem(og.toString());
			}
		});
		scrollPane.setViewportView(jt);
		rs = dao.findId("w_user users,w_bas_team team,w_status status",
				"userNo,userName,password,realName,realIpone,(case status.statusNo When 1 Then '管理人员' else '普通用户' end) as thorization,team.teamName",
				"users.team_id=team.team_id and users.statusNo=status.statusNo");
		try {
			while (rs.next()) {// 接收数据库中的内容
				v = new Vector<String>();
				v.addElement(rs.getString("userNo"));
				v.addElement(rs.getString("username"));
				v.addElement(rs.getString("password"));
				v.addElement(rs.getString("realName"));
				v.addElement(rs.getString("realIpone"));
				v.addElement(rs.getString("thorization"));
				v.addElement(rs.getString("teamName"));
				md.addRow(v);

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		Container cont = jf.getContentPane();
		cont.setLayout(new FlowLayout());
		cont.add(scrollPane, BorderLayout.CENTER);

		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.SOUTH);

		panel.add(new JLabel("用户名称:"));
		aTextField = new JTextField("", 7);
		panel.add(aTextField);

		panel.add(new JLabel("用户密码:"));
		bTextField = new JTextField("", 7);
		panel.add(bTextField);
		
		panel.add(new JLabel("realName:"));
		cTextField = new JTextField("", 7);
		panel.add(cTextField);

		final JPanel pane2 = new JPanel();
		cont.add(pane2, BorderLayout.SOUTH);
		
		pane2.add(new JLabel("realIpone:"));
		dTextField = new JTextField("", 7);
		pane2.add(dTextField);

		pane2.add(new JLabel("用户状态:"));
		combobox1 = new JComboBox();
		pane2.add(combobox1);

		pane2.add(new JLabel("所属部门:"));
		combobox2 = new JComboBox();
		pane2.add(combobox2);

		// getSelectedItem 当前所选项目
		combobox1.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					// 这个判断是选择只会得到一个结果，如果没有判断，会得到两个相同的值，从而获取不到所要选中的值。。
					str1 = combobox1.getSelectedItem().toString();
				}
			}

		});
		combobox2.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					str2 = combobox2.getSelectedItem().toString();
				}
			}

		});
		status_Bind1 = dao.Bind("w_status statu", "statusNo,(case statu.statusNo When 1 Then '管理人员' else '普通用户' end) as style", "", masm, combobox1, status);
		team_Bind1 = dao.Bind("w_bas_team", "*", "", ms, combobox2, team);
		final JPanel pane3 = new JPanel();
		ID = new JLabel("ID:", 10);
		ID.setVisible(false);
		pane3.add(ID);

		la1 = new JLabel("提示信息:", 10);
		pane3.add(la1);

		final JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/************ 创建表格行数组**********向表格模型中添加一行******(单行插入) **************/
				/*
				 * String[] rowValues = { aTextField.getText(),
				 * bTextField.getText(), str1, str2 };
				 */
				if ("".equals(aTextField.getText())
						|| "".equals(bTextField.getText())) {
					JOptionPane.showMessageDialog(null, "not null");
				} else {
					boolean exists = false;
					try {
						exists = dao.Exists("w_user", "*",
								"userName='" + aTextField.getText() + "'");
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					if(exists){
						JOptionPane.showMessageDialog(null, "用户名已经存在");
					}else{
					int temp = dao.insert(
							"w_user",
							"(userName,password,realName,realIpone,statusNo,team_id)",
							"('" + aTextField.getText() + "','"
									+ bTextField.getText() + "','"
									+ cTextField.getText() + "','"
									+ dTextField.getText() + "','"
									+ dao.status_search(str1) + "','"
									+ dao.team_search(str2) + "')");

					if (temp > 0) {
						// md.addRow(rowValues);// 向表格模型中添加一行

						/*********************** 添加成功后，重新插入所有的数据 *********************************/
						DefaultTableModel model = (DefaultTableModel) jt
								.getModel();
						while (model.getRowCount() > 0) {
							model.removeRow(model.getRowCount() - 1);
						}
						/******************** 刷新 *********************/
						rs = dao.findId("w_user users,w_bas_team team,w_status status",
								"userNo,userName,password,realName,realIpone,(case status.statusNo When 1 Then '管理人员' else '普通用户' end) as thorization,team.teamName",
								"users.team_id=team.team_id and users.statusNo=status.statusNo");

						try {
							while (rs.next()) {// 接收数据库中的内容
								v = new Vector<String>();
								v.addElement(rs.getString("userNo"));
								v.addElement(rs.getString("username"));
								v.addElement(rs.getString("password"));
								v.addElement(rs.getString("realName"));
								v.addElement(rs.getString("realIpone"));
								v.addElement(rs.getString("thorization"));
								v.addElement(rs.getString("teamName"));
								md.addRow(v);

							}
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

						JOptionPane.showMessageDialog(null, "插入成功");
						la1.setText("数据插入成功！");

					} else {
						JOptionPane.showMessageDialog(null, "插入失败");
						la1.setText("数据插入失败！");
					}
				}
				}
			}
		});
		pane2.add(addButton);
		final JButton updButton = new JButton("update");
		updButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();// 获取被选中行的索引
			
				int t = 0;
				try {

					t = dao.modify("w_user",
							"userName='" + aTextField.getText()
									+ "',passWord='" + bTextField.getText()
									+ "',realName='" + cTextField.getText()
									+ "',realIpone='" + dTextField.getText()
									+ "',statusNo=" + dao.status_search(str1)
									+ ",team_id=" + dao.team_search(str2) + "",
							"userNo=" + Integer.parseInt(ID.getText()) + "");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (t > 0) {
					if (selectedRow != -1) {
						md.setValueAt(ID.getText(), selectedRow, 0);
						md.setValueAt(aTextField.getText(), selectedRow, 1);
						md.setValueAt(bTextField.getText(), selectedRow, 2);
						md.setValueAt(cTextField.getText(), selectedRow, 3);
						md.setValueAt(dTextField.getText(), selectedRow, 4);
						md.setValueAt(str1, selectedRow, 5);
						md.setValueAt(str2, selectedRow, 6);
					}
					JOptionPane.showMessageDialog(null, "更新成功");
					la1.setText("数据更新成功！");
				} else {

					JOptionPane.showMessageDialog(null, "更新失败");
					la1.setText("数据更新失败！");
				}

			}

		});
		pane2.add(updButton);
		final JButton delButton = new JButton("delete");
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();

				boolean exists = false;
				try {
					exists = dao.Exists("w_bas_bx", "*",
							"userNo='" + Integer.parseInt(ID.getText()) + "'");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (exists) {
					int n = JOptionPane.showConfirmDialog(null,
							"该用户已经有报销记录,删除将丢失报销记录,确认要删除吗?", "确认",
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {

						dao.delete("w_bas_bx",
								"userNo=" + Integer.parseInt(ID.getText()) + "");

						t = dao.delete("w_user",
								"userNo=" + Integer.parseInt(ID.getText()) + "");
					} else if (n == JOptionPane.NO_OPTION) {

					}
				} else {
					t = dao.delete("w_user",
							"userNo=" + Integer.parseInt(ID.getText()) + "");

				}
				if (t > 0) {
					if (selectedRow != -1) {
						md.removeRow(selectedRow);
					}
					JOptionPane.showMessageDialog(null, "数据删除成功");
					la1.setText("数据删除成功！");
				} else {
					JOptionPane.showMessageDialog(null, "数据删除失败");
					la1.setText("数据删除失败！");
				}

			}

		});
		pane2.add(delButton);
		cont.setLayout(new FlowLayout(FlowLayout.LEADING));
		cont.add(pane3, BorderLayout.SOUTH);
		cont.add(pane2, BorderLayout.SOUTH);
		jf.setSize(670, 580);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new User_Mast();

	}
}
