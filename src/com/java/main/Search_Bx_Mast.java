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

public class Search_Bx_Mast {

	/**
	 * @param args
	 */
	private JLabel la1;
	private JTextField bTextField;
	private JTextField cTextField;
	private Vector<Object> v = null;
	ResultSet rs = null;
	final Dao dao = new DaoImpl();
	Search_Bx_Mast() throws Exception {
		super();
		JFrame jf = new JFrame("������ѯ");
		Container cont = jf.getContentPane();

		
		jf.setBounds((int) (static_bean.width*0.3), 100, 570, 470);
		final JTable jt = new JTable();
		String header[] = { "����", "������Ա", "��Ŀ���", "���ű��", "��������", "��������", "����",
				"����ʱ��", "����", "�ͷ�", "�޷�", "����", "�ܼ�","���״̬" };
		
		final DefaultTableModel md = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(jt);
		jt.setModel(md);
		jt.setSize(780, 550);

		final JPanel panel = new JPanel();
		cont.add(panel, BorderLayout.NORTH);
	
		rs = dao.findId("w_user users, w_bas_bx bx ,w_bas_project project ,w_bas_team team ,w_status statu", "bxNo,users.userName,project.proName,team.teamName,bxStart,bxBack,bxDay,bxTime,carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy,(case statu.statusNo When 1 Then '�����' else 'δ���' end) as ���״̬","bx.userNo=users.userNo and bx.project_id=project.project_id and bx.team_id=team.team_id and statu.statusNo=bx.statusNo");
				
		
		while (rs.next()) {
			v = new Vector<Object>();
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
			v.addElement(rs.getString("���״̬"));
			md.addRow(v);
		}

		jt.setRowSorter(new TableRowSorter<DefaultTableModel>(md));// ���ñ���������
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = jt.getSelectedRow();
				Object oa = md.getValueAt(selectedRow, 0);
				Object ob = md.getValueAt(selectedRow, 1);

				bTextField.setText(oa.toString());// ��ֵ��ֵ���ı���
				cTextField.setText(ob.toString());// ��ֵ��ֵ���ı���

			}
		});
		scrollPane.setViewportView(jt);

		panel.add(new JLabel("����:"));
		bTextField = new JTextField("", 10);
		panel.add(bTextField);

		panel.add(new JLabel("������Ա"));
		cTextField = new JTextField("", 10);
		panel.add(cTextField);

		cont.add(scrollPane, BorderLayout.CENTER);

		final JButton selButton = new JButton("��ѯ");
		selButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/************************* ����ϴβ�ѯ����Ϣ ************************************************************/
				DefaultTableModel model = (DefaultTableModel) jt.getModel();
				while (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}
				rs = dao.findId("w_user users, w_bas_bx bx ,w_bas_project project ,w_bas_team team ,w_status statu", "bxNo,users.userName,project.proName,team.teamName,bxStart,bxBack,bxDay,bxTime,carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy,(case statu.statusNo When 1 Then '�����' else 'δ���' end) as ���״̬","bx.userNo=users.userNo and bx.project_id=project.project_id and bx.team_id=team.team_id and bx.statusNo=statu.statusNo and  bx.bxNO like '%"
						+ bTextField.getText()
						+ "%' and users.userName like '%"
						+ cTextField.getText() + "%'");
				try {
					while (rs.next()) {// �������ݿ��е�����(

						v = new Vector<Object>();
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
						v.addElement(rs.getString("���״̬"));
						md.addRow(v);

					}
				} catch (SQLException e1) {
					System.out.println(v.size());
					la1.setText("��ʾȫ�����ݣ�");
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
		new Search_Bx_Mast();

	}
}
