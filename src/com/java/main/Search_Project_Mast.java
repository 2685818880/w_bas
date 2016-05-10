package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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

public class Search_Project_Mast {

	/**
	 * @param args
	 */
	private JLabel la1;
	private JTextField bTextField;
	private JTextField cTextField;
	private Vector<String> v = null;
	ResultSet rs = null;
	final Dao dao = new DaoImpl();
	Search_Project_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("项目基础数据w_bas_project_mast");
		jf.setBounds((int) (static_bean.width*0.3), 100, 660, 520);
		final JTable jt = new JTable();
		String header[] = { "项目编号", "项目名称" };
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
				bTextField.setText(oa.toString());// 将值赋值给文本框
				cTextField.setText(ob.toString());// 将值赋值给文本框

			}
		});
		scrollPane.setViewportView(jt);

		Container cont = jf.getContentPane();

		// JPane1
		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.SOUTH);

		panel.add(new JLabel("项目编号:"));
		bTextField = new JTextField("", 10);
		panel.add(bTextField);

		panel.add(new JLabel("项目名称:"));
		cTextField = new JTextField("", 10);
		panel.add(cTextField);

		cont.add(scrollPane, BorderLayout.CENTER);

		final JButton selButton = new JButton("查询");
		selButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 有数据 刷新 把上一次数据 删除 ，从数据库中再次查询
				// select a.* from sysobjects a, syscomments b where a.id = b.id
				// and b.text like '%表名%'
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				while (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}
				rs = dao.findId("w_bas_project project", "*", ""
						+ "project.proNo like '%" + bTextField.getText()
						+ "%' and project.proName like '%"
						+ cTextField.getText() + "%'");
				try {

					while (rs.next()) {// 接收数据库中的内容(
						v = new Vector<String>();
						v.addElement(rs.getString("proNo"));
						v.addElement(rs.getString("proName"));
						md.addRow(v);

					}
				} catch (SQLException e1) {
					System.out.println(v.size());
					la1.setText("显示全部数据！");
					e1.printStackTrace();
				}
			}
		});
		panel.add(selButton);

		cont.setLayout(new FlowLayout(FlowLayout.LEADING));
		jf.setSize(660, 520);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Search_Project_Mast();

	}
}
