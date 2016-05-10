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

		}//jdk�汾���ͣ�switch �ò���
		if ("����������¼��".equals(node.toString())) {
			try {
				new W_bas_Bxhzsr_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if ("������ѯ".equals(node.toString())) {
			try {
				new Search_Bx_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("�������".equals(node.toString())) {
			try {
				new Bx_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if ("���ŵ���".equals(node.toString())) {
			try {
				new Team_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("���Ų�ѯ".equals(node.toString())) {
			try {
				new Search_Team_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if ("��Ŀ����".equals(node.toString())) {
			try {
				new Project_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("��Ŀ��ѯ".equals(node.toString())) {
			try {
				new Search_Project_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		else if ("ϵͳ�û�".equals(node.toString())) {
			try {
				new User_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if ("�û���ѯ".equals(node.toString())) {
			try {
				new Search_User_Mast();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		
		/***
		 * ����ڵ��ǩ
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