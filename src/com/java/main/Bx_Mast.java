package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
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
import com.java.dao.Dao;
import com.java.dao.DaoImpl;

public class Bx_Mast {

	/**
	 * @param args
	 */
	private JLabel ID;
	private JTextField aTextField;
	private static JComboBox combobox1;
	int t = 0;
	private Vector<Object> v = null;
	ResultSet rs = null;
	private String str1;
	public static HashMap<Integer, Object> ms;
	public static HashMap<Integer, Object> masm;
	private w_bas_status status = new w_bas_status();

	@SuppressWarnings("rawtypes")
	public static HashMap Bind;
	final Dao dao = new DaoImpl();
	public Bx_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("user_mast");
		Container cont = jf.getContentPane();
		// cont.setLayout(new FlowLayout());
		jf.setBounds((int) (static_bean.width*0.3), 100, 570, 470);
		final JTable jt = new JTable();
		String header[] = { "ID", "单号", "报销人员", "项目编号", "部门编号", "出发日期", "返回日期",
				"天数", "报销时间", "车费", "餐费", "宿费", "其他", "总计", "审核状态" };
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
				Object on = md.getValueAt(selectedRow, 13);
				ID.setText(oa.toString());
				aTextField.setText(ob.toString());
				combobox1.setSelectedItem(on.toString());
			}
		});
		scrollPane.setViewportView(jt);
		rs = dao.findId(
				"w_user users, w_bas_bx bx ,w_bas_project project ,w_bas_team team ,w_status statu",
				"bx_id,bxNo,users.userName,project.proName,team.teamName,bxStart,bxBack,bxDay,bxTime,carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy,(case statu.statusNo When 1 Then '已审核' else '未审核' end) as 审核状态",
				"bx.userNo=users.userNo and bx.project_id=project.project_id and bx.team_id=team.team_id and statu.statusNo=bx.statusNo");
		try {
			while (rs.next()) {// 接收数据库中的内容
				v = new Vector<Object>();
				v.addElement(rs.getString("bx_id"));
				v.addElement(rs.getString("bxNo"));
				v.addElement(rs.getString("userName"));
				v.addElement(rs.getString("proName"));
				v.addElement(rs.getString("teamName"));
				v.addElement(rs.getString("bxStart"));
				v.addElement(rs.getString("bxBack"));
				v.addElement(rs.getInt("bxDay"));
				v.addElement(rs.getString("bxTime"));
				v.addElement(rs.getString("carSubsidy"));
				v.addElement(rs.getString("mealSubsidy"));
				v.addElement(rs.getString("sleepSubsidy"));
				v.addElement(rs.getString("otherSubsidy"));
				v.addElement(rs.getString("sumSubsidy"));
				v.addElement(rs.getString("审核状态"));
				md.addRow(v);

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		cont.add(scrollPane, BorderLayout.CENTER);
		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.SOUTH);
		panel.add(new JLabel("用户ID:"));
		ID = new JLabel("ID:", 10);
		panel.add(ID);
		panel.add(new JLabel("用户名称:"));
		aTextField = new JTextField("", 10);
		panel.add(aTextField);
		panel.add(new JLabel("审核状态:"));
		combobox1 = new JComboBox();
		panel.add(combobox1);
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

		Bind = dao
				.Bind("w_status statu",
						"statusNo,(case statu.statusNo When 1 Then '已审核' else '未审核' end) as style",
						"", masm, combobox1, status);

		final JButton updButton = new JButton("update");
		updButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();// 获取被选中行的索引
				// 预编译 ，提高效率
				int t = 0;
				try {

					t = dao.modify("w_bas_bx",
							"bxNo='" + aTextField.getText()
									+ "',statusNo=" + dao.bx_status_search(str1)
									+ "","bx_id=" + Integer.parseInt(ID.getText()) + "");
							

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (t > 0) {
					if (selectedRow != -1) {
						md.setValueAt(ID.getText(), selectedRow, 0);
						md.setValueAt(aTextField.getText(), selectedRow, 1);
						md.setValueAt(str1, selectedRow, 14);

					}
					JOptionPane.showMessageDialog(null, "更新成功");
				} else {

					JOptionPane.showMessageDialog(null, "更新失败");
				}

			}

		});
		panel.add(updButton);
		final JButton delButton = new JButton("delete");
		delButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();
				int n = JOptionPane.showConfirmDialog(null, "确认要删除吗?", "确认",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					t=dao.delete("w_bas_bx",
							"bx_id=" + Integer.parseInt(ID.getText()) + "");
					if (t > 0) {
						if (selectedRow != -1) {
							md.removeRow(selectedRow);
						}
						JOptionPane.showMessageDialog(null, "数据删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "数据删除失败");
					}

				} else if (n == JOptionPane.NO_OPTION) {

				}				

			}

		});
		panel.add(delButton);
		// cont.setLayout((LayoutManager) new FlowLayout(FlowLayout.LEADING));
		jf.setSize(800, 580);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Bx_Mast();

	}
}
