package com.shdp.websockets.webservice.usecases.employeews;

import java.math.BigDecimal;



public class Employee {
	
	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private BigDecimal recentYearIncome ;
	private String recentYearIncomeEncoded ;
	public Employee() {
		
	}
	public Employee(long id, String firstName, String lastName, String gender,int age, EvalRates evalRate,BigDecimal recentYearIncomei ) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.recentYearIncome = recentYearIncomei ;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getRecentYearIncome() {
		return recentYearIncome;
	}
	public void setRecentYearIncome(BigDecimal recentYearIncome) {
		this.recentYearIncome = recentYearIncome;
	}
	
	public String getRecentYearIncomeEncoded() {
		return recentYearIncomeEncoded;
	}
	public void setRecentYearIncomeEncoded(String recentYearIncomeEncoded) {
		this.recentYearIncomeEncoded = recentYearIncomeEncoded;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", age="
				+ age + ", recentYearIncome=" + recentYearIncome
				+ ", recentYearIncomeEncoded=" + recentYearIncomeEncoded + "]";
	}
	
	
}
