package com.example.demo;

public class Member {
	private String id;
	private String password;
	private String name;
	private String age;
	
	public Member(String id, String password, String name, String age) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.age=age;
	}
	
	public String getId() { return id; }
	public String getPassword() { return password; }
	public String getName() { return name; }
	public String getAge() {return age;}
	
	public void setId(String id) { this.id = id; }
	public void setPassword(String password) { this.password = password; }
	public void setName(String name) { this.name = name; }
	public void setAge(String age) {this.age=age;}
	
}