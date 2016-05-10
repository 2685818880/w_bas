package com.java.bean;

public class w_bas_bx {
	private String bxNo ;
	private String bxStart ;
	private String bxBack ;
	private String bxTime ;
	private float carSubsidy ;
	private float mealSubsidy ;
	private float sleepSubsidy ;
	private float otherSubsidy ;
	private float sumSubsidy ;
	public w_bas_bx() {
		super();
		// TODO Auto-generated constructor stub
	}
	public w_bas_bx(String bxNo, String bxStart, String bxBack, String bxTime,
			float carSubsidy, float mealSubsidy, float sleepSubsidy,
			float otherSubsidy, float sumSubsidy) {
		super();
		this.bxNo = bxNo;
		this.bxStart = bxStart;
		this.bxBack = bxBack;
		this.bxTime = bxTime;
		this.carSubsidy = carSubsidy;
		this.mealSubsidy = mealSubsidy;
		this.sleepSubsidy = sleepSubsidy;
		this.otherSubsidy = otherSubsidy;
		this.sumSubsidy = sumSubsidy;
	}
	public String getBxNo() {
		return bxNo;
	}
	public void setBxNo(String bxNo) {
		this.bxNo = bxNo;
	}
	public String getBxStart() {
		return bxStart;
	}
	public void setBxStart(String bxStart) {
		this.bxStart = bxStart;
	}
	public String getBxBack() {
		return bxBack;
	}
	public void setBxBack(String bxBack) {
		this.bxBack = bxBack;
	}
	public String getBxTime() {
		return bxTime;
	}
	public void setBxTime(String bxTime) {
		this.bxTime = bxTime;
	}
	public float getCarSubsidy() {
		return carSubsidy;
	}
	public void setCarSubsidy(float carSubsidy) {
		this.carSubsidy = carSubsidy;
	}
	public float getMealSubsidy() {
		return mealSubsidy;
	}
	public void setMealSubsidy(float mealSubsidy) {
		this.mealSubsidy = mealSubsidy;
	}
	public float getSleepSubsidy() {
		return sleepSubsidy;
	}
	public void setSleepSubsidy(float sleepSubsidy) {
		this.sleepSubsidy = sleepSubsidy;
	}
	public float getOtherSubsidy() {
		return otherSubsidy;
	}
	public void setOtherSubsidy(float otherSubsidy) {
		this.otherSubsidy = otherSubsidy;
	}
	public float getSumSubsidy() {
		return sumSubsidy;
	}
	public void setSumSubsidy(float sumSubsidy) {
		this.sumSubsidy = sumSubsidy;
	}
	@Override
	public String toString() {
		return "w_bas_bx [bxNo=" + bxNo + ", bxStart=" + bxStart + ", bxBack="
				+ bxBack + ", bxTime=" + bxTime + ", carSubsidy=" + carSubsidy
				+ ", mealSubsidy=" + mealSubsidy + ", sleepSubsidy="
				+ sleepSubsidy + ", otherSubsidy=" + otherSubsidy
				+ ", sumSubsidy=" + sumSubsidy + "]";
	}
	
	
	
	
	
	
	
	
}
