package com.java.main;

import java.awt.BorderLayout;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.java.bean.static_bean;
import com.java.main.ItemListener;
import com.java.main.TreeSelectionListener;

@SuppressWarnings("serial")
public class Manager extends JFrame {
	private JMenuBar menuBar = null;
	public static JTree treeRoot = null;
	private JMenu menu1 = null;
	private JMenu menu2 = null;
	private JMenu menu3 = null;
	private JMenu menu4 = null;
	private JMenuItem menuitem1 = null;
	private JMenuItem menuitem3 = null;
	private JMenuItem menuitem11 = null;
	private JMenuItem menuitem33 = null;
	private JMenuItem menuitem44 = null;

	public Manager() {
		super();
		setTitle("AccountManager");
		setBounds(0, 0, static_bean.width, static_bean.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JSplitPane hs = new JSplitPane();
		hs.setDividerLocation(200);// ��ߵĿ��Ϊ40
		getContentPane().add(hs, BorderLayout.CENTER);// ��ˮƽ���������һ����ǩ���t

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("��������");
		root.add(node1);
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("����������¼��",
				false);// �������������ӽڵ�Ķ����ڵ�
		node1.add(node2);
		DefaultMutableTreeNode node21 = new DefaultMutableTreeNode("������ѯ",
				false);// �������������ӽڵ�Ķ����ڵ�
		node1.add(node21);

		DefaultMutableTreeNode node22 = new DefaultMutableTreeNode("�������",
				false);// �������������ӽڵ�Ķ����ڵ�
		node1.add(node22);

		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("��������ά��");
		root.add(node3);
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("���ŵ���", false);
		// �������������ӽڵ�Ķ����ڵ�
		node3.add(node4);

		DefaultMutableTreeNode node41 = new DefaultMutableTreeNode("��Ŀ����",
				false);// �������������ӽڵ�Ķ����ڵ�
		node3.add(node41);
		DefaultMutableTreeNode node51 = new DefaultMutableTreeNode("��Ŀ��ѯ",
				false);// �������������ӽڵ�Ķ����ڵ�
		DefaultMutableTreeNode node52 = new DefaultMutableTreeNode("���Ų�ѯ",
				false);// �������������ӽڵ�Ķ����ڵ�
		node3.add(node51);
		node3.add(node52);
		DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("ϵͳ����");
		root.add(node5);
		DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("ϵͳ�û�", false);
		// �������������ӽڵ�Ķ����ڵ�
		DefaultMutableTreeNode node61 = new DefaultMutableTreeNode("�û���ѯ",
				false);

		node5.add(node6);
		node5.add(node61);

		if (static_bean.statusNo == 0) {
			node1.remove(node22);
			node3.remove(node4);
			node3.remove(node41);
			node5.remove(node6);
		}
		// node6.setAllowsChildren(false); ȷ���Ƿ�����˽ڵ�ӵ���ӽڵ㡣

		treeRoot = new JTree(root);
		getContentPane().add(treeRoot, BorderLayout.WEST);
		/********************** �������нڵ�ķ�ʽ *****************************/
		/*
		 * DefaultTreeModel treemodel = new DefaultTreeModel(root); JTree
		 * treedefault =new JTree(treemodel);
		 * getContentPane().add(treedefault,BorderLayout.CENTER);
		 */
		/*
		 * DefaultTreeModel treemodel2=new DefaultTreeModel(root,true); JTree
		 * treepont =new JTree(treemodel2);
		 * getContentPane().add(treepont,BorderLayout.EAST);
		 */

		treeRoot.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeRoot.addTreeSelectionListener(new TreeSelectionListener());

		hs.setLeftComponent(treeRoot);

		final JSplitPane vs = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vs.setDividerLocation(30);
		vs.setDividerSize(8);// �ָ����Ŀ��Ϊ8����
		// vs.setOneTouchExpandable(true);// �ṩUIС����
		// vs.setContinuousLayout(true);
		hs.setRightComponent(vs);// ��ӵ�ˮƽ�����Ҳ�

		final JLabel backLabel = new JLabel();
		URL resource = this.getClass().getResource("/image/1.jpg");
		ImageIcon icon = new ImageIcon(resource);// ��������ͼƬ����
		backLabel.setIcon(icon);
		backLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		vs.setLeftComponent(backLabel);// �ڴ�ֱ����Ϸ����һ����ǩ���

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menu1 = new JMenu("�ļ�");
		menu2 = new JMenu("����");
		menu3 = new JMenu("����");
		menu4 = new JMenu("����");

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		
		//����������
		final JToolBar tool = new JToolBar("gongjulan");
		tool.setFloatable(false);
		getContentPane().add(tool, BorderLayout.NORTH);
		

		final JLabel timeLabel = new JLabel();
		timeLabel.setText("������Ա:"
				+ static_bean.username
				+ "    "
				+ "����ʱ�䣺"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
		tool.add(timeLabel);
		tool.addSeparator();

		menuitem1 = new JMenuItem("�޸�����");
		menuitem1.addActionListener(new ItemListener());

		menuitem3 = new JMenuItem("�˳�");
		menuitem3.addActionListener(new ItemListener());
		menu1.add(menuitem1);

		menu1.add(menuitem3);

		menuitem11 = new JMenuItem("������");
		menuitem11.addActionListener(new ItemListener());

		menuitem44 = new JMenuItem("����...");
		menuitem44.addActionListener(new ItemListener());

		menuitem33 = new JMenuItem("WriteRead");
		menuitem33.addActionListener(new ItemListener());

		menu2.add(menuitem11);

		menu3.add(menuitem33);

		menu4.add(menuitem44);
		menuBar.setVisible(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Manager frame = new Manager();
		frame.setVisible(true);
	}

}
