package com.moneywizzard.services.iface;

import java.util.List;

import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;


public interface ExpenseService {

	ExpenseObj addExpense(ExpenseObj obj) throws Exception;

	List<ExpenseObj> getExpenses(String id) throws Exception;

}
