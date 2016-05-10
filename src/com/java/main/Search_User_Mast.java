package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.java.bean.static_bean;
import com.java.dao.Dao;
import com.java.dao.DaoImpl;

public class Search_User_Mast {

	private JTextField bTextField;
	private JTextField cTextField;
	private Vector<Object> v = null;
	ResultSet rs = null;
	final Dao dao = new DaoImpl();
	Search_User_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("个人查询");
		Container cont = jf.getContentPane();
		jf.setBounds((int) (static_bean.width*0.3), 100, 800, 520);
		final JTable jt = new JTable();
		String header[] = { "员工编号", "用户名称", "真实姓名", "联系方式", "所属部门", "系统权限" };
		final DefaultTableModel md = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setModel(md);
		jt.setSize(780, 550);

		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.NORTH);

		rs = dao.findId("w_user users,w_bas_team team,w_status status", "userNo,userName,realName,realIpone,team.teamName,(case status.statusNo When 1 Then '管理人员' else '普通用户' end) as thorization", "users.team_id=team.team_id and users.statusNo=status.statusNo");
				
		while (rs.next()) {
			v = new Vector<Object>();
			v.addElement(rs.getInt("userNo"));
			v.addElement(rs.getString("userName"));
			v.addElement(rs.getString("realName"));
			v.addElement(rs.getString("realIpone"));
			v.addElement(rs.getString("teamName"));
			v.addElement(rs.getString("thorization"));
			md.addRow(v);
		}

		jt.setRowSorter(new TableRowSorter<DefaultTableModel>(md));// 设置表格的排序器
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = jt.getSelectedRow();
				Object oa = md.getValueAt(selectedRow, 0);
				Object ob = md.getValueAt(selectedRow, 1);

				bTextField.setText(oa.toString());// 将值赋值给文本框
				cTextField.setText(ob.toString());// 将值赋值给文本框

			}
		});
		scrollPane.setViewportView(jt);

		panel.add(new JLabel("员工编号:"));
		bTextField = new JTextField("", 10);
		panel.add(bTextField);

		panel.add(new JLabel("用户名称"));
		cTextField = new JTextField("", 10);
		panel.add(cTextField);

		cont.add(scrollPane, BorderLayout.CENTER);

		final JButton selButton = new JButton("查询");
		selButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				while (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}			
				rs = dao.findId("w_user users,w_bas_team team,w_status status", "userNo,userName,realName,realIpone,team.teamName,(case status.statusNo When 1 Then '管理人员' else '普通用户' end) as thorization",  "users.team_id=team.team_id and users.statusNo=[status].statusNo and"+" "+ "users.userNo like '%" + bTextField.getText()
						+ "%' and users.userName like '%"
						+ cTextField.getText() + "%'");
				try {
					while (rs.next()) {// 接收数据库中的内容(

						v = new Vector<Object>();
						v.addElement(rs.getInt("userNo"));
						v.addElement(rs.getString("userName"));
						v.addElement(rs.getString("realName"));
						v.addElement(rs.getString("realIpone"));
						v.addElement(rs.getString("teamName"));
						v.addElement(rs.getString("thorization"));
						md.addRow(v);

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(selButton);

		// cont.setLayout(new FlowLayout(FlowLayout.LEADING));
		jf.setSize(800, 520);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Search_User_Mast();

	}
}
