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
		JFrame jf = new JFrame("���Ż�������w_bas_team_mast");
		jf.setBounds((int) (static_bean.width*0.3), 100, 660, 550);
		final JTable jt = new JTable();
		String header[] = { "ID", "���ű��", "��������" };
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
				ID.setText(oa.toString());
				aTextField.setText(ob.toString());// ��ֵ��ֵ���ı���
				bTextField.setText(oc.toString());// ��ֵ��ֵ���ı���

			}
		});
		scrollPane.setViewportView(jt);

		rs = dao.findId("w_bas_team", "*", "");

		try {
			while (rs.next()) {// �������ݿ��е�����
				v = new Vector<String>();
				v.addElement(rs.getString("team_id"));
				v.addElement(rs.getString("teamNo"));
				v.addElement(rs.getString("teamName"));
				md.addRow(v);

			}
		} catch (SQLException e1) {
			System.out.println(v.size());
			la1.setText("��ʾȫ�����ݣ�");
			e1.printStackTrace();
		}

		Container cont = jf.getContentPane();
		cont.add(scrollPane, BorderLayout.CENTER);

		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.SOUTH);

		final JPanel pane2 = new JPanel();
		cont.add(pane2, BorderLayout.SOUTH);

		la1 = new JLabel("��ʾ��Ϣ", 10);
		pane2.add(la1);

		ID = new JLabel("ID", 10);
		pane2.add(ID);

		panel.add(new JLabel("��Ŀ���:"));
		aTextField = new JTextField("", 10);
		panel.add(aTextField);

		panel.add(new JLabel("��Ŀ����:"));
		bTextField = new JTextField("", 10);
		panel.add(bTextField);

		// add update delete
		final Dao dao = new DaoImpl();
		final JButton addButton = new JButton("add");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// String[] rowValues = {
				// aTextField.getText(),bTextField.getText() };// �������������

				if ("".equals(aTextField.getText())
						|| "".equals(bTextField.getText())) {
					JOptionPane.showMessageDialog(null, "not null");
				} else {

					try {
						if (dao.Exists("w_bas_team", "*", "teamNo='"
								+ aTextField.getText() + "'")
								|| dao.Exists("w_bas_team", "*", "teamName='"
										+ bTextField.getText() + "'")) {
							JOptionPane.showMessageDialog(null, "teamNo��teamName�Ѿ�����");
						}
						else {

							int temp = dao.insert("w_bas_team",
									"(teamNo,teamName)",
									"('" + aTextField.getText() + "','"
											+ bTextField.getText() + "')");
							if (temp > 0) {
								// md.addRow(rowValues);// ����ģ�������һ��

								/*********************** ��ӳɹ������²������е����� *********************************/
								DefaultTableModel model = (DefaultTableModel) jt
										.getModel();
								while (model.getRowCount() > 0) {
									model.removeRow(model.getRowCount() - 1);
								}
								/******************** ˢ�� *********************/
								rs = dao.findId("w_bas_team", "*", "");

								try {
									while (rs.next()) {// �������ݿ��е�����
										v = new Vector<String>();
										v.addElement(rs.getString("team_id"));
										v.addElement(rs.getString("teamNo"));
										v.addElement(rs.getString("teamName"));
										md.addRow(v);

									}
								} catch (SQLException e1) {
									System.out.println(v.size());
									la1.setText("��ʾȫ�����ݣ�");
									e1.printStackTrace();
								}

								JOptionPane.showMessageDialog(null, "����ɹ�");
								la1.setText("���ݲ���ɹ���");

							} else {
								JOptionPane.showMessageDialog(null, "����ʧ��");
								la1.setText("���ݲ���ʧ�ܣ�");
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
				int selectedRow = jt.getSelectedRow();// ��ȡ��ѡ���е�����
				// Ԥ���� �����Ч��
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
							"�ò����Ѿ�������Ա��Ϣ��ɾ������Ա��Ϣ����ʧ,ȷ��Ҫɾ����?", "ȷ��",
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
		jf.setSize(660, 550);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Team_Mast();

	}
}
