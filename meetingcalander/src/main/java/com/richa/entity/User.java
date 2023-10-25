package com.richa.entity;

import java.util.List;

public class User {

	private int id;
	private String name;
	private List<Meeting> calender;
	
	public User(int id,String name, List<Meeting> calender) {
		this.id=id;
		this.name=name;
		this.calender=calender;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public List<Meeting> getCalender(){
		return calender;
	}
	public void setCalender(List<Meeting> calender) {
		this.calender=calender;
		
	}
	
}
