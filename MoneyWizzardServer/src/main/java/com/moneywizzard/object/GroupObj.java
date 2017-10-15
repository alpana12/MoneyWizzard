package com.moneywizzard.object;

import java.util.List;

public class GroupObj {

	private String id;
	private String name;
	private List<PersonObj> personObjs;
	private List<ExpenseObj> expenseObjs;
	
	
	
	public List<ExpenseObj> getExpenseObjs() {
		return expenseObjs;
	}
	public void setExpenseObjs(List<ExpenseObj> expenseObjs) {
		this.expenseObjs = expenseObjs;
	}
	public List<PersonObj> getPersonObjs() {
		return personObjs;
	}
	public void setPersonObjs(List<PersonObj> personObjs) {
		this.personObjs = personObjs;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
