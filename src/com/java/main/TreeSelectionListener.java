package com.java.main;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.java.main.Project_Mast;

class TreeSelectionListener implements javax.swing.event.TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) Manager.treeRoot
				.getLastSelectedPathComponent();
		System.out.println(node);
		if (node == null)
			return;
		if (node.isRoot()) {

			System.out.println("ROOT");

		}//jdk版本过低，switch 用不了
		if ("报销单汇总录入".equals(node.toString())) {
			try {
				new W_bas_Bxhzsr_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("报销查询".equals(node.toString())) {
			try {
				new Search_Bx_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("报销审核".equals(node.toString())) {
			try {
				new Bx_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if ("部门档案".equals(node.toString())) {
			try {
				new Team_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("部门查询".equals(node.toString())) {
			try {
				new Search_Team_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if ("项目档案".equals(node.toString())) {
			try {
				new Project_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("项目查询".equals(node.toString())) {
			try {
				new Search_Project_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		else if ("系统用户".equals(node.toString())) {
			try {
				new User_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("用户查询".equals(node.toString())) {
			try {
				new Search_User_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		
		/***
		 * 输出节点标签
		 * out 
		 * */
		  String s=null;
		  Manager.treeRoot.getSelectionModel().setSelectionMode(TreeSelectionModel
		  .SINGLE_TREE_SELECTION); if(!Manager.treeRoot.isSelectionEmpty()){
		  TreePath[]selection=Manager.treeRoot.getSelectionPaths(); for(int
		  i=0;i<selection.length;i++){ TreePath treepath=selection[i];
		  Object[]path=treepath.getPath(); DefaultMutableTreeNode nodes;
		  nodes=(DefaultMutableTreeNode) path[i];
		  s=nodes.getUserObject()+(i==(path.length-1)?"":"-->"); //s=(String)
		  nodes.getUserObject(); System.out.println(s); }
		  System.out.println(); } 
		  System.out.println();
		 
	}

}