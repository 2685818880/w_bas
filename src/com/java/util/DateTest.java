package com.java.util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DateTest {
 
 

 public static void main(String[] args) {
  JFrame frame=new JFrame("dee");
  JPanel cp=new JPanel();
  DateChooserJButton button=new DateChooserJButton ();
  cp.add(button);
  frame.setContentPane(cp);
  frame.setSize(200,200);
  frame.setVisible(true);
  
 }
}