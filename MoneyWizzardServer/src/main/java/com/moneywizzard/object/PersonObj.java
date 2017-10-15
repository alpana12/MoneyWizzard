package com.moneywizzard.object;

import java.util.List;

public class PersonObj {

	private String id;
	private String personName;
	private String emailId;
	private String password;
	private String personImage;
	private long phoneNumber;
	
	private double totalRemainingBudget;
	private double expenses;
	private double youOwe;
	private double youAreOwed;
	
	private List<CategoryExpenseObj> categoryExpenses;
	
	
	
	public List<CategoryExpenseObj> getCategoryExpenses() {
		return categoryExpenses;
	}
	public void setCategoryExpenses(List<CategoryExpenseObj> categoryExpenses) {
		this.categoryExpenses = categoryExpenses;
	}
	public double getTotalRemainingBudget() {
		return totalRemainingBudget;
	}
	public void setTotalRemainingBudget(double totalRemainingBudget) {
		this.totalRemainingBudget = totalRemainingBudget;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public double getYouOwe() {
		return youOwe;
	}
	public void setYouOwe(double youOwe) {
		this.youOwe = youOwe;
	}
	public double getYouAreOwed() {
		return youAreOwed;
	}
	public void setYouAreOwed(double youAreOwed) {
		this.youAreOwed = youAreOwed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPersonImage() {
		return personImage;
	}
	public void setPersonImage(String personImage) {
		this.personImage = personImage;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
