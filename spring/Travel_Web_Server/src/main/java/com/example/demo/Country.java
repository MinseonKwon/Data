package com.example.demo;

public class Country {
	private String id;
	private String c_name;
	private String date;
	private String budget;
	private String food;
	private String shopping;
	private String tour;
	private String traffic;
	private String stay;
	private String etc;
	
	public Country(String id, String c_name,String date, String budget, String food, String shopping, String tour, String traffic, String stay, String etc) {
		this.id=id;
		this.c_name=c_name;
		this.date=date;
		this.budget=budget;
		this.food=food;
		this.shopping=shopping;
		this.tour=tour;
		this.traffic=traffic;
		this.stay=stay;
		this.etc=etc;
	}
	

	public String getId() { return id;}
	public void setId(String id) { this.id=id;}
	
	public String getc_Name() {return c_name;}
	public void setc_Name(String c_name) { this.c_name=c_name;}
	
	public String getDate() { return date;}
	public void setDate(String date) { this.date=date;}
	
	public String getBudget() { return budget;}
	public void setBudget(String budget) { this.budget=budget;}
	
	public String getFood() { return food;}
	public void setFood(String food) { this.food=food;}
	
	public String getShopping() { return shopping;}
	public void setShopping(String shopping) { this.shopping=shopping;}
	
	public String getTour() { return tour;}
	public void setTour(String tour) { this.tour=tour;}
	
	public String getTraffic() { return traffic;}
	public void setTraffic(String traffic) { this.traffic=traffic;}
	
	public String getStay() { return stay;}
	public void setStay(String stay) { this.stay=stay;}
	
	public String getEtc() { return etc;}
	public void setEtc(String etc) { this.etc=etc;}
}
