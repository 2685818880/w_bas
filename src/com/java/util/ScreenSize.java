package com.java.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenSize {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        int w=(int) screensize.getWidth();
        int h=(int) screensize.getHeight();
        int dpi=Toolkit.getDefaultToolkit().getScreenResolution();
        System.out.println(w+"=--"+h+"--"+dpi);
	}

}
