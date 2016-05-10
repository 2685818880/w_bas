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
		hs.setDividerLocation(200);// 左边的宽度为40
		getContentPane().add(hs, BorderLayout.CENTER);// 在水平面板左侧添加一个标签组件t

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ROOT");
		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("报销管理");
		root.add(node1);
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("报销单汇总录入",
				false);// 创建不润许有子节点的二级节点
		node1.add(node2);
		DefaultMutableTreeNode node21 = new DefaultMutableTreeNode("报销查询",
				false);// 创建不润许有子节点的二级节点
		node1.add(node21);

		DefaultMutableTreeNode node22 = new DefaultMutableTreeNode("报销审核",
				false);// 创建不润许有子节点的二级节点
		node1.add(node22);

		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("基础数据维护");
		root.add(node3);
		DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("部门档案", false);
		// 创建不润许有子节点的二级节点
		node3.add(node4);

		DefaultMutableTreeNode node41 = new DefaultMutableTreeNode("项目档案",
				false);// 创建不润许有子节点的二级节点
		node3.add(node41);
		DefaultMutableTreeNode node51 = new DefaultMutableTreeNode("项目查询",
				false);// 创建不润许有子节点的二级节点
		DefaultMutableTreeNode node52 = new DefaultMutableTreeNode("部门查询",
				false);// 创建不润许有子节点的二级节点
		node3.add(node51);
		node3.add(node52);
		DefaultMutableTreeNode node5 = new DefaultMutableTreeNode("系统管理");
		root.add(node5);
		DefaultMutableTreeNode node6 = new DefaultMutableTreeNode("系统用户", false);
		// 创建不润许有子节点的二级节点
		DefaultMutableTreeNode node61 = new DefaultMutableTreeNode("用户查询",
				false);

		node5.add(node6);
		node5.add(node61);

		if (static_bean.statusNo == 0) {
			node1.remove(node22);
			node3.remove(node4);
			node3.remove(node41);
			node5.remove(node6);
		}
		// node6.setAllowsChildren(false); 确定是否允许此节点拥有子节点。

		treeRoot = new JTree(root);
		getContentPane().add(treeRoot, BorderLayout.WEST);
		/********************** 另外两中节点的方式 *****************************/
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
		vs.setDividerSize(8);// 分割条的宽度为8像素
		// vs.setOneTouchExpandable(true);// 提供UI小部件
		// vs.setContinuousLayout(true);
		hs.setRightComponent(vs);// 添加到水平面板的右侧

		final JLabel backLabel = new JLabel();
		URL resource = this.getClass().getResource("/image/1.jpg");
		ImageIcon icon = new ImageIcon(resource);// 创建背景图片对象
		backLabel.setIcon(icon);
		backLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());

		vs.setLeftComponent(backLabel);// 在垂直面板上方添加一个标签组件

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menu1 = new JMenu("文件");
		menu2 = new JMenu("工具");
		menu3 = new JMenu("窗口");
		menu4 = new JMenu("帮助");

		menuBar.add(menu1);
		menuBar.add(menu2);
		menuBar.add(menu3);
		menuBar.add(menu4);
		
		//工具栏创建
		final JToolBar tool = new JToolBar("gongjulan");
		tool.setFloatable(false);
		getContentPane().add(tool, BorderLayout.NORTH);
		

		final JLabel timeLabel = new JLabel();
		timeLabel.setText("操作人员:"
				+ static_bean.username
				+ "    "
				+ "操作时间："
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
		tool.add(timeLabel);
		tool.addSeparator();

		menuitem1 = new JMenuItem("修改密码");
		menuitem1.addActionListener(new ItemListener());

		menuitem3 = new JMenuItem("退出");
		menuitem3.addActionListener(new ItemListener());
		menu1.add(menuitem1);

		menu1.add(menuitem3);

		menuitem11 = new JMenuItem("计算器");
		menuitem11.addActionListener(new ItemListener());

		menuitem44 = new JMenuItem("关于...");
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
