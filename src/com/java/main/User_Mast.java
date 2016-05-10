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
		String header[] = { "ID", "�û�����", "�û�����","realName","realIpone", "�û�״̬", "��������" };
		final DefaultTableModel md = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setModel(md);

		jt.setRowSorter(new TableRowSorter<DefaultTableModel>(md));// ���ñ���������
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
				aTextField.setText(ob.toString());// ��ֵ��ֵ���ı���
				bTextField.setText(oc.toString());// ��ֵ��ֵ���ı���
				cTextField.setText(od.toString());// ��ֵ��ֵ���ı���
				dTextField.setText(oe.toString());// ��ֵ��ֵ���ı���
				
				combobox1.setSelectedItem(of.toString());
				combobox2.setSelectedItem(og.toString());
			}
		});
		scrollPane.setViewportView(jt);
		rs = dao.findId("w_user users,w_bas_team team,w_status status",
				"userNo,userName,password,realName,realIpone,(case status.statusNo When 1 Then '������Ա' else '��ͨ�û�' end) as thorization,team.teamName",
				"users.team_id=team.team_id and users.statusNo=status.statusNo");
		try {
			while (rs.next()) {// �������ݿ��е�����
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

		panel.add(new JLabel("�û�����:"));
		aTextField = new JTextField("", 7);
		panel.add(aTextField);

		panel.add(new JLabel("�û�����:"));
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

		pane2.add(new JLabel("�û�״̬:"));
		combobox1 = new JComboBox();
		pane2.add(combobox1);

		pane2.add(new JLabel("��������:"));
		combobox2 = new JComboBox();
		pane2.add(combobox2);

		// getSelectedItem ��ǰ��ѡ��Ŀ
		combobox1.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					// ����ж���ѡ��ֻ��õ�һ����������û���жϣ���õ�������ͬ��ֵ���Ӷ���ȡ������Ҫѡ�е�ֵ����
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
		status_Bind1 = dao.Bind("w_status statu", "statusNo,(case statu.statusNo When 1 Then '������Ա' else '��ͨ�û�' end) as style", "", masm, combobox1, status);
		team_Bind1 = dao.Bind("w_bas_team", "*", "", ms, combobox2, team);
		final JPanel pane3 = new JPanel();
		ID = new JLabel("ID:", 10);
		ID.setVisible(false);
		pane3.add(ID);

		la1 = new JLabel("��ʾ��Ϣ:", 10);
		pane3.add(la1);

		final JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/************ �������������**********����ģ�������һ��******(���в���) **************/
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
						JOptionPane.showMessageDialog(null, "�û����Ѿ�����");
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
						// md.addRow(rowValues);// ����ģ�������һ��

						/*********************** ��ӳɹ������²������е����� *********************************/
						DefaultTableModel model = (DefaultTableModel) jt
								.getModel();
						while (model.getRowCount() > 0) {
							model.removeRow(model.getRowCount() - 1);
						}
						/******************** ˢ�� *********************/
						rs = dao.findId("w_user users,w_bas_team team,w_status status",
								"userNo,userName,password,realName,realIpone,(case status.statusNo When 1 Then '������Ա' else '��ͨ�û�' end) as thorization,team.teamName",
								"users.team_id=team.team_id and users.statusNo=status.statusNo");

						try {
							while (rs.next()) {// �������ݿ��е�����
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

						JOptionPane.showMessageDialog(null, "����ɹ�");
						la1.setText("���ݲ���ɹ���");

					} else {
						JOptionPane.showMessageDialog(null, "����ʧ��");
						la1.setText("���ݲ���ʧ�ܣ�");
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
				int selectedRow = jt.getSelectedRow();// ��ȡ��ѡ���е�����
			
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
					JOptionPane.showMessageDialog(null, "���³ɹ�");
					la1.setText("���ݸ��³ɹ���");
				} else {

					JOptionPane.showMessageDialog(null, "����ʧ��");
					la1.setText("���ݸ���ʧ�ܣ�");
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
							"���û��Ѿ��б�����¼,ɾ������ʧ������¼,ȷ��Ҫɾ����?", "ȷ��",
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
					JOptionPane.showMessageDialog(null, "����ɾ���ɹ�");
					la1.setText("����ɾ���ɹ���");
				} else {
					JOptionPane.showMessageDialog(null, "����ɾ��ʧ��");
					la1.setText("����ɾ��ʧ�ܣ�");
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
