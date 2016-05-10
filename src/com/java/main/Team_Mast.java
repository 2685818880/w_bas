package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
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
import javax.swing.JOptionPane;
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

public class Team_Mast {

	/**
	 * @param args
	 */
	private JLabel la1, ID;
	private JTextField aTextField;
	private JTextField bTextField;
	private Vector<String> v = null;
	final Dao dao = new DaoImpl();
	ResultSet rs = null;

	Team_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("部门基础数据w_bas_team_mast");
		jf.setBounds((int) (static_bean.width*0.3), 100, 660, 550);
		final JTable jt = new JTable();
		String header[] = { "ID", "部门编号", "部门名称" };
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
				ID.setText(oa.toString());
				aTextField.setText(ob.toString());// 将值赋值给文本框
				bTextField.setText(oc.toString());// 将值赋值给文本框

			}
		});
		scrollPane.setViewportView(jt);

		rs = dao.findId("w_bas_team", "*", "");

		try {
			while (rs.next()) {// 接收数据库中的内容
				v = new Vector<String>();
				v.addElement(rs.getString("team_id"));
				v.addElement(rs.getString("teamNo"));
				v.addElement(rs.getString("teamName"));
				md.addRow(v);

			}
		} catch (SQLException e1) {
			System.out.println(v.size());
			la1.setText("显示全部数据！");
			e1.printStackTrace();
		}

		Container cont = jf.getContentPane();
		cont.add(scrollPane, BorderLayout.CENTER);

		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.SOUTH);

		final JPanel pane2 = new JPanel();
		cont.add(pane2, BorderLayout.SOUTH);

		la1 = new JLabel("提示信息", 10);
		pane2.add(la1);

		ID = new JLabel("ID", 10);
		pane2.add(ID);

		panel.add(new JLabel("项目编号:"));
		aTextField = new JTextField("", 10);
		panel.add(aTextField);

		panel.add(new JLabel("项目名称:"));
		bTextField = new JTextField("", 10);
		panel.add(bTextField);

		// add update delete
		final Dao dao = new DaoImpl();
		final JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String[] rowValues = {
				// aTextField.getText(),bTextField.getText() };// 创建表格行数组

				if ("".equals(aTextField.getText())
						|| "".equals(bTextField.getText())) {
					JOptionPane.showMessageDialog(null, "not null");
				} else {

					try {
						if (dao.Exists("w_bas_team", "*", "teamNo='"
								+ aTextField.getText() + "'")
								|| dao.Exists("w_bas_team", "*", "teamName='"
										+ bTextField.getText() + "'")) {
							JOptionPane.showMessageDialog(null, "teamNo或teamName已经存在");
						}
						else {

							int temp = dao.insert("w_bas_team",
									"(teamNo,teamName)",
									"('" + aTextField.getText() + "','"
											+ bTextField.getText() + "')");
							if (temp > 0) {
								// md.addRow(rowValues);// 向表格模型中添加一行

								/*********************** 添加成功后，重新插入所有的数据 *********************************/
								DefaultTableModel model = (DefaultTableModel) jt
										.getModel();
								while (model.getRowCount() > 0) {
									model.removeRow(model.getRowCount() - 1);
								}
								/******************** 刷新 *********************/
								rs = dao.findId("w_bas_team", "*", "");

								try {
									while (rs.next()) {// 接收数据库中的内容
										v = new Vector<String>();
										v.addElement(rs.getString("team_id"));
										v.addElement(rs.getString("teamNo"));
										v.addElement(rs.getString("teamName"));
										md.addRow(v);

									}
								} catch (SQLException e1) {
									System.out.println(v.size());
									la1.setText("显示全部数据！");
									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null, "插入成功");
								la1.setText("数据插入成功！");

							} else {
								JOptionPane.showMessageDialog(null, "插入失败");
								la1.setText("数据插入失败！");
							}
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		pane2.add(addButton);
		final JButton updButton = new JButton("modify");
		updButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();// 获取被选中行的索引
				// 预编译 ，提高效率
				int t = 0;
				t = dao.modify("w_bas_team", "teamNo='" + aTextField.getText()
						+ "',teamName='" + bTextField.getText() + "'",
						"team_id='" + ID.getText() + "'");
				if (t > 0) {
					if (selectedRow != 1) {
						md.setValueAt(ID.getText(), selectedRow, 0);
						md.setValueAt(aTextField.getText(), selectedRow, 1);
						md.setValueAt(bTextField.getText(), selectedRow, 2);
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

				int t = 0;

				boolean exists = true;
				try {
					exists = dao.Exists("w_user", "*",
							"team_id=" + Integer.parseInt(ID.getText() + ""));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (exists) {
					int n = JOptionPane.showConfirmDialog(null,
							"该部门已经存在人员信息，删除后组员信息将丢失,确认要删除吗?", "确认",
							JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {

						dao.delete(
								"w_bas_bx",
								"team_id="
										+ Integer.parseInt(ID.getText() + ""));

						dao.delete(
								"w_user",
								"team_id="
										+ Integer.parseInt(ID.getText() + ""));

						t = dao.delete(
								"w_bas_team",
								"team_id="
										+ Integer.parseInt(ID.getText() + ""));

					} else if (n == JOptionPane.NO_OPTION) {

					}
				} else {
					t = dao.delete("w_bas_team", "team_id='" + ID.getText()
							+ "'");
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
		jf.setSize(660, 550);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Team_Mast();

	}
}
