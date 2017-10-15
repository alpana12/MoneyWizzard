package com.moneywizzard.object;

import java.util.Date;
import java.util.List;

public class ExpenseObj {

	private String personId;
	private String personName;
	private String expenseCategoryId;
	private String location;
	private double amount;
	private String expenseType;
	private String comment;
	private String groupId;
	private List<PersonObj> person; 
	private Date date;
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public List<PersonObj> getPerson() {
		return person;
	}

	public void setPerson(List<PersonObj> person) {
		this.person = person;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getExpenseCategoryId() {
		return expenseCategoryId;
	}

	public void setExpenseCategoryId(String expenseCategoryId) {
		this.expenseCategoryId = expenseCategoryId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	
}
