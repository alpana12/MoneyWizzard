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
import com.moneywizzard.object.CategoryExpenseObj;
import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.services.iface.ExpenseService;
import com.moneywizzard.services.iface.GroupService;
import com.moneywizzard.services.iface.PersonServices;
import com.moneywizzard.utils.MWUtils;

@Service
public class ExpenseServicesImpl implements ExpenseService {

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
	@Transactional
	public ExpenseObj addExpense(ExpenseObj expenseObj) throws Exception {
		if(expenseObj!=null){
			Person person= personDao.getById(expenseObj.getPersonId());
			if(person!=null){
				Expensecategory expensecategory = expensecategoryDao.getById(expenseObj.getExpenseCategoryId());
				if(expensecategory!=null){
					if(expenseObj.getExpenseType().equalsIgnoreCase("Personal")){
						Expenses expenses = new Expenses();
						expenses.setId(MWUtils.generateGUID());
						expenses.setPersonid(person);
						expenses.setLocation(expenseObj.getLocation());
						expenses.setAmount(expenseObj.getAmount());
						expenses.setExpensecategoryid(expensecategory);	
						expenses.setDate(new Date());
						expensesDao.save(expenses);	
					}else{
						Group group = groupDao.getById(expenseObj.getGroupId());
						if(group!=null){
							Groupexpense groupexpense = new Groupexpense();
							groupexpense.setId(MWUtils.generateGUID());
							groupexpense.setPaidbypersonid(person);
							groupexpense.setGroupid(group);
							groupexpense.setAmount(expenseObj.getAmount());
							groupexpense.setComment(expenseObj.getComment());
							groupexpense.setDate(new Date());
							groupexpenseDao.save(groupexpense);
							
							if(expenseObj.getPerson()!=null && expenseObj.getPerson().size()>0){
								for(PersonObj obj:expenseObj.getPerson()){
									Person per= personDao.getById(obj.getId());
									Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
									groupexpensemapping.setId(MWUtils.generateGUID());
									groupexpensemapping.setPersonid(per);
									groupexpensemapping.setAmount(obj.getYouOwe());
									groupexpensemapping.setGroupexpenseid(groupexpense);
									groupexpensemappingDao.save(groupexpensemapping);
								}
							}

						}
					}
					
				}	
			}
			
		}
		return expenseObj;
	}


	@Override
	public List<ExpenseObj> getExpenses(String id) throws Exception {
		List<ExpenseObj> expenseObjs = new ArrayList<>();
		
		Person person = personDao.getById(id);
		if(person!=null){
			Expenses expense = new Expenses();
			expense.setPersonid(person);
			List<Expenses> expenses = expenseDao.getByCriteria(expense);
			for(Expenses exp:expenses){
				ExpenseObj expenseObj = new ExpenseObj();
				expenseObj.setAmount(exp.getAmount());
				expenseObj.setExpenseType("Personal");
				expenseObj.setLocation(exp.getLocation());
				expenseObj.setDate(exp.getDate());
				expenseObjs.add(expenseObj);
			}
			
		}
		return expenseObjs;
	}

	

}
