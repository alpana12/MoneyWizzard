package com.moneywizzard.services.iface;

import java.util.List;

import com.moneywizzard.object.BudgetObj;
import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;


public interface BudgetService {

	String addBudget(BudgetObj budgetObj)throws Exception;

	List<BudgetObj> getBudgets(String id)throws Exception;

}
