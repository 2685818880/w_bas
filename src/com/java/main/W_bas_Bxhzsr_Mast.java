package com.java.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.java.bean.static_bean;
import com.java.bean.w_bas_project;
import com.java.bean.w_bas_team;
import com.java.dao.Dao;
import com.java.dao.DaoImpl;
import com.java.dao.DaoUtil;
import com.java.util.DateChooserJButton;
import com.java.util.between_day;

public class W_bas_Bxhzsr_Mast {
	static JButton button,bu;
	private JTextField aTextField, bTextField, cTextField, dTextField,
			eTextField, fTextField, gTextField, hTextField;
	private JComboBox combobox1, combobox2;
	private String str1, str2;

	static HashMap<Integer, Object> ms;
	static HashMap<Integer, Object> mas;

	private w_bas_team team = new w_bas_team();
	private w_bas_project project = new w_bas_project();

	@SuppressWarnings("rawtypes")
	public static HashMap team_Bind;
	@SuppressWarnings("rawtypes")
	public static HashMap project_Bind;
	private int findId;
	final Dao dao = new DaoImpl();
	final DaoUtil daoutil = new DaoUtil();

	@SuppressWarnings("deprecation")
	public W_bas_Bxhzsr_Mast() throws Exception {
		JFrame jf = new JFrame("Test");
		Container cp = jf.getContentPane();
		cp.setLayout(new FlowLayout());
		JPanel jp = new JPanel();
		final JLabel j1 = new JLabel();
		j1.setText("G"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		jp.add(j1);

		JLabel j2 = new JLabel();
		j2.setText("报销单汇总输入");
		jp.add(j2);// bei

		final DateChooserJButton dj1 = new DateChooserJButton();
		final DateChooserJButton dj2 = new DateChooserJButton();
		final DateChooserJButton dj3 = new DateChooserJButton();

		JPanel p = new JPanel();
		p.add(new JLabel("出差起始日:"));
		p.add(new JPanel().add(dj1));
		p.add(new JLabel("出差到达日:"));
		p.add(new JPanel().add(dj2));

		final JLabel jb = new JLabel("天数:");
		p.add(jb);
		button = new JButton("天数统计");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj1.getDate());
				String strDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj2.getDate());
				try {
					int daysBetween = between_day.daysBetween(strDate1,
							strDate2);
					jb.setText(daysBetween + "天");
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj2.getDate()));
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj3.getDate()));

			}

		});
		p.add(button);

		p.add(new JLabel("报销日期:"));
		p.add(new JPanel().add(dj3));

		final JPanel panel = new JPanel();

		panel.add(new JLabel("用户名:"));
		aTextField = new JTextField("", 10);
		panel.add(aTextField);

		panel.add(new JLabel("所属部门:"));
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

		panel.add(new JLabel("项目代编号:"));
		combobox2 = new JComboBox();
		panel.add(combobox2);

		// getSelectedItem 当前所选项目
		combobox2.addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					// 这个判断是选择只会得到一个结果，如果没有判断，会得到两个相同的值，从而获取不到所要选中的值。。
					str2 = combobox2.getSelectedItem().toString();

				}
			}

		});

		team_Bind = daoutil.Bind("w_bas_team", "*", "", ms, combobox1, team);

		project_Bind = daoutil.Bind("w_bas_project", "*", "", mas, combobox2,
				project);
		final JPanel pa = new JPanel();

		pa.add(new JLabel("车费:"));
		bTextField = new JTextField("", 5);
		pa.add(bTextField);

		pa.add(new JLabel("餐费:"));
		cTextField = new JTextField("", 5);
		pa.add(cTextField);

		pa.add(new JLabel("宿费:"));
		dTextField = new JTextField("", 5);
		pa.add(dTextField);

		pa.add(new JLabel("其他:"));
		eTextField = new JTextField("", 5);
		pa.add(eTextField);

		pa.add(new JLabel("总计:"));
		fTextField = new JTextField("", 5);
		pa.add(fTextField);

		bu = new JButton("费用统计");
		bu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("".equals(bTextField.getText())
						|| "".equals(cTextField.getText())
						|| "".equals(dTextField.getText())
						|| "".equals(eTextField.getText())
						) {
					JOptionPane
							.showMessageDialog(null,
									"carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy is don't null");
				}else{
				Float sum= Float
				.parseFloat(bTextField.getText())
				+ Float.parseFloat(cTextField.getText())
				+ Float.parseFloat(dTextField.getText())
				+ Float.parseFloat(eTextField.getText());
				fTextField.setText(sum + "元");	
				JOptionPane.showMessageDialog(null, sum + "元");
				}
			}

		});
		pa.add(bu);
		final JPanel juser = new JPanel();

		juser.add(new JLabel("操作人员:"));
		gTextField = new JTextField(static_bean.username, 20);
		juser.add(gTextField);

		gTextField.enable(false);

		juser.add(new JLabel("操作时间:"));
		hTextField = new JTextField(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
				20);

		juser.add(hTextField);
		hTextField.enable(false);
		final JPanel pane2 = new JPanel();

		final JButton openButton = new JButton("新增");
		openButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new W_bas_Bxhzsr_Mast();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		pane2.add(openButton);
		final JButton addButton = new JButton("保存");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean exists = false;
				try {
					exists = dao.Exists("w_bas_bx", "*",
							"bxNo='" + j1.getText() + "'");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				String strDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj1.getDate());
				String strDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj2.getDate());
				String strDate3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(dj3.getDate());
				int style = 0;
				if (!"".equals(gTextField.getText())) {
					if (exists) {
						JOptionPane.showMessageDialog(null, "编号重复，请另启一个页面重新录入");
					} else {
						if (!"".equals(aTextField.getText())) {
							ResultSet rs = null;
							int temp = 0;
							boolean next = false;
							try {
								rs = dao.findId("w_user", "userNo",
										"userName='"
												+ aTextField.getText().trim()
												+ "'");
								next = rs.next();
								System.out.println(next);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if (!next) {
								JOptionPane.showMessageDialog(null, "用户名输入有误");
							} else {
								try {
									findId = rs.getInt("userNo");
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								System.out.println(findId);
							}
							System.out.println("---------" + findId);
							if ("".equals(bTextField.getText())
									|| "".equals(cTextField.getText())
									|| "".equals(dTextField.getText())
									|| "".equals(eTextField.getText())
									|| "".equals(fTextField.getText())) {
								JOptionPane
										.showMessageDialog(null,
												"carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy is don't null");
							} else {
								if (Float.parseFloat(fTextField.getText()) != Float
										.parseFloat(bTextField.getText())
										+ Float.parseFloat(cTextField.getText())
										+ Float.parseFloat(dTextField.getText())
										+ Float.parseFloat(eTextField.getText())) {
									JOptionPane.showMessageDialog(null,
											"总计有误，请核对！");
								} else {
									try {
										temp = dao
												.insert("w_bas_bx",
														"(bxNo,userNo,project_id,team_id,bxStart,bxBack,bxDay,bxTime,carSubsidy,mealSubsidy,sleepSubsidy,otherSubsidy,sumSubsidy,handler,handlertime,statusNo)",
														"('"
																+ j1.getText()
																+ "',"
																+ findId
																+ ","
																+ daoutil
																		.project_search(str2)
																+ ","
																+ daoutil
																		.team_search1(str1)
																+ ",'"
																+ strDate1
																+ "','"
																+ strDate2
																+ "','"
																+ between_day
																		.daysBetween(
																				strDate1,
																				strDate2)
																+ "','"
																+ strDate3
																+ "',"
																+ Float.parseFloat(bTextField
																		.getText())
																+ ","
																+ Float.parseFloat(cTextField
																		.getText())
																+ ","
																+ Float.parseFloat(dTextField
																		.getText())
																+ ","
																+ Float.parseFloat(eTextField
																		.getText())
																+ ","
																+ Float.parseFloat(fTextField
																		.getText())
																+ ",'"
																+ gTextField
																		.getText()
																+ "','"
																+ hTextField
																		.getText()
																+ "'," + style
																+ ")");
									} catch (ParseException e1) {
										e1.printStackTrace();
									}

									if (temp > 0) {
										JOptionPane.showMessageDialog(null,
												"插入成功");

									} else {
										JOptionPane.showMessageDialog(null,
												"插入失败");
									}

								}
							}

						}

						else {
							JOptionPane.showMessageDialog(null, "用户名不能为空");
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "用户未登录");
				}

			}
		});
		pane2.add(addButton);
		cp.add(jp, BorderLayout.NORTH);
		cp.add(p);
		cp.add(panel, BorderLayout.SOUTH);
		cp.add(pa, BorderLayout.SOUTH);
		cp.add(juser, BorderLayout.SOUTH);
		cp.add(pane2, BorderLayout.SOUTH);
		cp.setLayout(new FlowLayout(FlowLayout.LEADING));
		jf.setBounds((int) (static_bean.width*0.3), 100, 700, 500);
		jf.setSize(700, 550);
		jf.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new W_bas_Bxhzsr_Mast();
	}
}
