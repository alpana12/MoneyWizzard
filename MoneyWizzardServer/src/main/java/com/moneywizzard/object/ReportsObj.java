package com.moneywizzard.object;

import java.util.List;

public class ReportsObj {

	private double totalRemainingBudget;
	private double expenses;
	private double youOwe;
	private double youAreOwed;
	
	private List<CategoryExpenseObj> categoryExpenses;

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

	public List<CategoryExpenseObj> getCategoryExpenses() {
		return categoryExpenses;
	}

	public void setCategoryExpenses(List<CategoryExpenseObj> categoryExpenses) {
		this.categoryExpenses = categoryExpenses;
	}
	
	
}
