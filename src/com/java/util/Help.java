package com.java.util;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Help extends JFrame {
	public Help() throws Exception {
		super("����ϵͳ");// ����
		setBounds(500, 200, 500, 500);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		JLabel jl=new JLabel("����Ʒʹ��Ȩ��˽����������");
		jl.setVisible(true);
		container.add(jl);
		this.getContentPane().add(jl, BorderLayout.CENTER);
		this.setSize(200, 150);
		this.setVisible(true);
	}

}
