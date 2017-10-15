package com.moneywizzard.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneywizzard.dao.BudgetDao;
import com.moneywizzard.dao.ExpensecategoryDao;
import com.moneywizzard.dao.ExpensesDao;
import com.moneywizzard.dao.GroupDao;
import com.moneywizzard.dao.GroupexpenseDao;
import com.moneywizzard.dao.GroupexpensemappingDao;
import com.moneywizzard.dao.GroupmappingDao;
import com.moneywizzard.dao.PersonDao;
import com.moneywizzard.model.Budget;
import com.moneywizzard.model.Expensecategory;
import com.moneywizzard.model.Expenses;
import com.moneywizzard.model.Group;
import com.moneywizzard.model.Groupexpense;
import com.moneywizzard.model.Groupexpensemapping;
import com.moneywizzard.model.Groupmapping;
import com.moneywizzard.model.GroupmappingPK;
import com.moneywizzard.model.Person;
import com.moneywizzard.object.BudgetObj;
import com.moneywizzard.object.CategoryExpenseObj;
import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.services.iface.BudgetService;
import com.moneywizzard.services.iface.ExpenseService;
import com.moneywizzard.services.iface.GroupService;
import com.moneywizzard.services.iface.PersonServices;
import com.moneywizzard.utils.MWUtils;

@Service
public class BudgetServicesImpl implements BudgetService {

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private ExpensesDao expenseDao;

	@Autowired
	private BudgetDao budgetDao;
	
	@Autowired
	private GroupexpenseDao groupExpenseDao;
	
	@Autowired
	private GroupexpensemappingDao groupexpensemappingDao;
	
	@Autowired
	private GroupmappingDao groupmappingDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ExpensecategoryDao expensecategoryDao;

	@Autowired
	private ExpensesDao expensesDao;
	
	@Autowired
	private GroupexpenseDao groupexpenseDao;

	@Override
	public String addBudget(BudgetObj budgetObj) throws Exception {
		if(budgetObj!=null){
			Person person = personDao.getById(budgetObj.getPersonId());
			Budget budget = new Budget();
			budget.setId(MWUtils.generateGUID());
			budget.setAmount(budgetObj.getAmount());
			budget.setMonth(budgetObj.getMonth());
			budget.setYear(budgetObj.getYear());
			budget.setPersonid(person);
			budgetDao.save(budget);
			return budget.getId();
		}
		return null;
	}

	@Override
	public List<BudgetObj> getBudgets(String id) throws Exception {
		List<BudgetObj> budgetObjs = new ArrayList<>();
		Person person = personDao.getById(id);
		
		Budget budget = new Budget();
		budget.setPersonid(person);
		List<Budget> budgets = budgetDao.getByCriteria(budget);
		for(Budget budg:budgets){
			BudgetObj budgetObj = new BudgetObj();
			budgetObj.setAmount(budg.getAmount());
			budgetObj.setMonth(budg.getMonth());
			budgetObj.setYear(budg.getYear());
			budgetObjs.add(budgetObj);
		}
		return budgetObjs;
	} 

	

}
