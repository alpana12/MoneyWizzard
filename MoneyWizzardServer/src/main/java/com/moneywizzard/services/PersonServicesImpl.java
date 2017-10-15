package com.moneywizzard.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneywizzard.dao.BudgetDao;
import com.moneywizzard.dao.ExpensesDao;
import com.moneywizzard.dao.FriendsDao;
import com.moneywizzard.dao.FriendsPKDao;
import com.moneywizzard.dao.GroupexpenseDao;
import com.moneywizzard.dao.GroupexpensemappingDao;
import com.moneywizzard.dao.GroupmappingDao;
import com.moneywizzard.dao.MoneywizardaccountDao;
import com.moneywizzard.dao.PersonDao;
import com.moneywizzard.model.Budget;
import com.moneywizzard.model.Expenses;
import com.moneywizzard.model.Friends;
import com.moneywizzard.model.FriendsPK;
import com.moneywizzard.model.Groupexpense;
import com.moneywizzard.model.Groupexpensemapping;
import com.moneywizzard.model.Groupmapping;
import com.moneywizzard.model.GroupmappingPK;
import com.moneywizzard.model.Moneywizardaccount;
import com.moneywizzard.model.Person;
import com.moneywizzard.object.CategoryExpenseObj;
import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.object.ReportsObj;
import com.moneywizzard.services.iface.PersonServices;
import com.moneywizzard.utils.MWUtils;

@Service
public class PersonServicesImpl implements PersonServices {

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
	private FriendsPKDao friendsPKDao;
	
	@Autowired 
	private FriendsDao friendsDao;
	
	@Autowired 
	private MoneywizardaccountDao moneywizardaccountDao;


	@Override
	@Transactional
	public PersonObj createAccount(PersonObj personObj) throws Exception {
		if(personObj!=null){
			if(!MWUtils.isNullOrBlank(personObj.getId())){
				Person person = personDao.getById(personObj.getId());
				if(person!=null){
					person.setName(personObj.getPersonName());
					person.setEmailid(personObj.getEmailId());
					//person.setPhonenumber(Integer.valueOf(personObj.getPhoneNumber()+""));
					if(!MWUtils.isNullOrBlank(personObj.getPassword())){
						person.setPassword(personObj.getPassword());
					}
					personDao.update(person);
				}
			}else{
				Person person = new Person();
				person.setId(MWUtils.generateGUID());
				person.setName(personObj.getPersonName());
				person.setEmailid(personObj.getEmailId());
				person.setPassword(personObj.getPassword());
				//person.setPhonenumber(Integer.valueOf(personObj.getPhoneNumber()+""));
				personDao.save(person);
				Moneywizardaccount moneywizardaccount = new Moneywizardaccount();
				moneywizardaccount.setId(MWUtils.generateGUID());
				moneywizardaccount.setPersonid(person);
				moneywizardaccount.setAccountbalance(0.0);
				moneywizardaccountDao.save(moneywizardaccount);

				personObj.setId(person.getId());
			}

		}
		return personObj;
	}

	@Override
	public PersonObj authenticateUser(PersonObj personObj) throws Exception {

		Person person = new Person();
		person.setEmailid(personObj.getEmailId());
		person.setPassword(personObj.getPassword());
		person = personDao.authenticateUser(person);
		if(person == null){
			throw new Exception("User not found");
		}else{
			personObj.setId(person.getId());
			personObj.setPersonName(person.getName());
			personObj.setPhoneNumber(person.getPhonenumber()!=null?person.getPhonenumber():0);
			personObj.setEmailId(person.getEmailid());
			personObj.setPassword(null);

			/*Expenses expense = new Expenses();
			expense.setPersonid(person);
			List<Expenses> expenses = expenseDao.getByCriteria(expense);
			double totalExpense = 0.0;
			if(expenses!=null && expenses.size()>0){
				HashMap<String, Double> categoryExperience = new HashMap<>();
				for(Expenses exp:expenses){
					totalExpense += exp.getAmount();
					Double val = categoryExperience.get(categoryExperience.get(exp.getExpensecategoryid().getCategoryname()));
					if(val == null){
						categoryExperience.put(exp.getExpensecategoryid().getCategoryname(),exp.getAmount());
					}else{
						categoryExperience.put(exp.getExpensecategoryid().getCategoryname(),val+exp.getAmount());
					}
				}
				List<CategoryExpenseObj> objs = new ArrayList<>();
				if(categoryExperience.size() > 0){				
					Iterator it = categoryExperience.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry)it.next();
						CategoryExpenseObj obj=new CategoryExpenseObj();
						obj.setAmount((double)pair.getValue());
						obj.setCategory((String)pair.getKey());
						objs.add(obj);
					}
				}
				personObj.setCategoryExpenses(objs);
				personObj.setExpenses(totalExpense);
			}
			Budget budget = new Budget();
			budget.setPersonid(person);
			budget.setMonth(Calendar.getInstance().get(Calendar.MONTH));
			budget.setYear(Calendar.getInstance().get(Calendar.YEAR));
			List<Budget> budgets = budgetDao.getByCriteria(budget);
			if(budgets!=null && budgets.size()>0){
				budget = budgets.get(0);
				personObj.setTotalRemainingBudget(budget.getAmount()-totalExpense);
			}

			Groupexpense groupexpense = new Groupexpense();
			groupexpense.setPaidbypersonid(person);
			List<Groupexpense> groupexpenses = groupExpenseDao.getByCriteria(groupexpense);
			if(groupexpenses!=null && groupexpenses.size()>0){
				double totalOweYou = 0.0;
				for(Groupexpense grpExp:groupexpenses){
					Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
					groupexpensemapping.setGroupexpenseid(groupexpense);
					List<Groupexpensemapping> groupexpensemappings = groupexpensemappingDao.getByCriteria(groupexpensemapping);
					if(groupexpensemappings!=null && groupexpensemappings.size()>0){

						for(Groupexpensemapping grpExpMap: groupexpensemappings){
							if(!grpExpMap.getPersonid().getId().equalsIgnoreCase(person.getId())){
								totalOweYou += grpExpMap.getAmount();
							}
						}
					}
					personObj.setYouAreOwed(totalOweYou);
				}
			}
*/			return personObj;

		}
	}

	@Override
	public List<GroupObj> getPersonGroups(String personId) throws Exception {
		List<GroupObj> groupObjs = new ArrayList<>();
		if(!MWUtils.isNullOrBlank(personId)){
			Person person = personDao.getById(personId);
			if(person!=null){
				Groupmapping groupmapping = new Groupmapping();
				GroupmappingPK groupmappingPK = new GroupmappingPK();
				groupmappingPK.setPersonid(person);
				groupmapping.setId(groupmappingPK);
				List<Groupmapping> groupmappings = groupmappingDao.getByCriteria(groupmapping);
				if(groupmappings!=null && groupmappings.size()>0){
					for(Groupmapping grpMap:groupmappings){
						GroupObj groupObj = new GroupObj();
						groupObj.setId(grpMap.getId().getGroupid().getId());
						groupObj.setName(grpMap.getId().getGroupid().getName());
						List<PersonObj> personObjs = new ArrayList<>();
						
						groupmapping = new Groupmapping();
						groupmappingPK = new GroupmappingPK();
						groupmappingPK.setGroupid(grpMap.getId().getGroupid());
						groupmapping.setId(groupmappingPK);
						List<Groupmapping> groupmappings2 = groupmappingDao.getByCriteria(groupmapping);

						if(groupmappings!=null && groupmappings.size()>0){
							for(Groupmapping grpMap2:groupmappings2){
								PersonObj personObj = new PersonObj();
								personObj.setId(grpMap2.getId().getPersonid().getId());
								personObj.setPersonName(grpMap2.getId().getPersonid().getName());
								personObjs.add(personObj);
							}
						}

						Groupexpense groupexpense = new Groupexpense();
						groupexpense.setGroupid(grpMap.getId().getGroupid());
						List<Groupexpense> groupexpenses = groupExpenseDao.getByCriteria(groupexpense);
						List<ExpenseObj> expenseObjs =new ArrayList<>();
						if(groupexpenses!=null && groupexpenses.size()>0){
							for(Groupexpense grpExp:groupexpenses){
								ExpenseObj expenseObj = new ExpenseObj();
								expenseObj.setAmount(grpExp.getAmount());
								expenseObj.setPersonId(grpExp.getPaidbypersonid().getId());
								expenseObj.setPersonName(grpExp.getPaidbypersonid().getName());
								expenseObj.setComment(grpExp.getComment());
								expenseObj.setDate(grpExp.getDate());
								
								Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
								groupexpensemapping.setGroupexpenseid(grpExp);
								List<Groupexpensemapping> groupexpensemappings = groupexpensemappingDao.getByCriteria(groupexpensemapping);
								List<PersonObj> personObjs2 = new ArrayList<>();
								if(groupexpensemappings!=null && groupexpensemappings.size()>0){
									for(Groupexpensemapping grpExpMap:groupexpensemappings){
										PersonObj personObj = new PersonObj();
										personObj.setId(grpExpMap.getPersonid().getId());
										personObj.setPersonName(grpExpMap.getPersonid().getName());
										personObj.setYouOwe(grpExpMap.getAmount());
										personObjs2.add(personObj);
										
									}	
								}
								expenseObj.setPerson(personObjs2);
								expenseObjs.add(expenseObj);
							}
						}
						groupObj.setExpenseObjs(expenseObjs);
						groupObj.setPersonObjs(personObjs);
						groupObjs.add(groupObj);
					}
				}
			}
		}
		return groupObjs;
	}

	@Override
	public ReportsObj getReportsForPerson(String id) throws Exception {
		ReportsObj reportsObj = new ReportsObj();

		Person person = personDao.getById(id);
		if(person!=null){

			Expenses expense = new Expenses();
			expense.setPersonid(person);
			List<Expenses> expenses = expenseDao.getByCriteria(expense);
			double totalExpense = 0.0;
			if(expenses!=null && expenses.size()>0){
				HashMap<String, Double> categoryExperience = new HashMap<>();
				for(Expenses exp:expenses){
					totalExpense += exp.getAmount();
					Double val = categoryExperience.get(exp.getExpensecategoryid().getCategoryname());
					if(val == null){
						categoryExperience.put(exp.getExpensecategoryid().getCategoryname(),exp.getAmount());
					}else{
						categoryExperience.put(exp.getExpensecategoryid().getCategoryname(),val+exp.getAmount());
					}
				}
				List<CategoryExpenseObj> objs = new ArrayList<>();
				if(categoryExperience.size() > 0){				
					Iterator it = categoryExperience.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry)it.next();
						CategoryExpenseObj obj=new CategoryExpenseObj();
						obj.setAmount((double)pair.getValue());
						obj.setCategory((String)pair.getKey());
						objs.add(obj);
					}
				}
				reportsObj.setCategoryExpenses(objs);
				reportsObj.setExpenses(totalExpense);
			}
			Budget budget = new Budget();
			budget.setPersonid(person);
			budget.setMonth(Calendar.getInstance().get(Calendar.MONTH));
			budget.setYear(Calendar.getInstance().get(Calendar.YEAR));
			List<Budget> budgets = budgetDao.getByCriteria(budget);
			if(budgets!=null && budgets.size()>0){
				budget = budgets.get(0);
				reportsObj.setTotalRemainingBudget(budget.getAmount()-totalExpense);
			}

			Groupexpense groupexpense = new Groupexpense();
			groupexpense.setPaidbypersonid(person);
			List<Groupexpense> groupexpenses = groupExpenseDao.getByCriteria(groupexpense);
			if(groupexpenses!=null && groupexpenses.size()>0){
				double totalOweYou = 0.0;
				for(Groupexpense grpExp:groupexpenses){
					Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
					groupexpensemapping.setGroupexpenseid(grpExp);
					List<Groupexpensemapping> groupexpensemappings = groupexpensemappingDao.getByCriteria(groupexpensemapping);
					if(groupexpensemappings!=null && groupexpensemappings.size()>0){

						for(Groupexpensemapping grpExpMap: groupexpensemappings){
							if(!grpExpMap.getPersonid().getId().equalsIgnoreCase(person.getId())){
								totalOweYou += grpExpMap.getAmount();
							}
						}
					}
					reportsObj.setYouAreOwed(totalOweYou);
				}
			}
		}

		return reportsObj;
	}

	@Override
	public List<PersonObj> findPersonByEmail(String email) throws Exception {
		List<PersonObj> personObjs = new ArrayList<>();
		
		Person person = new Person();
		person.setEmailid(email);
		List<Person> persons = personDao.getByCriteria(person);
		if(persons!=null){
			for(Person per:persons){
				PersonObj personObj = new PersonObj();
				personObj.setId(per.getId());
				personObj.setPersonName(per.getName());
				personObj.setEmailId(per.getEmailid());
				personObj.setPhoneNumber(person.getPhonenumber()!=null?person.getPhonenumber():0);
//				personObj.setPhoneNumber(per.getPhonenumber());
				personObjs.add(personObj);
			}
			
		}
		
		
		return personObjs;
	}

	@Override
	public String addFriend(String personId, String friendId) throws Exception {
		
		Person person = personDao.getById(personId);
		if(person!=null){
			Person friend = personDao.getById(friendId);
			if(friend!=null){
				Friends friends = new Friends();
				FriendsPK friendsPK = new FriendsPK();
				friendsPK.setPersonid(person);
				friendsPK.setFriendid(friend);	
				//friendsPKDao.save(friendsPK);
				friends.setId(friendsPK);
				friendsDao.save(friends);
				
				friends = new Friends();
				friendsPK = new FriendsPK();
				friendsPK.setPersonid(friend);
				friendsPK.setFriendid(person);	
				//friendsPKDao.save(friendsPK);
				friends.setId(friendsPK);
				friendsDao.save(friends);
			}
		}
		return "true";
	}

	@Override
	public List<PersonObj> getFriends(String personId) throws Exception {
		List<PersonObj> personObjs = new ArrayList<>();
		Person person = personDao.getById(personId);
		if(person!=null){
			Friends friend = new Friends();
			FriendsPK friendsPK = new FriendsPK();
			friendsPK.setPersonid(person);
			friend.setId(friendsPK);
			List<Friends> friends = friendsDao.getByCriteria(friend);
			if(friends!=null && friends.size()>0){
				for(Friends frnd:friends){
					friendsPK = frnd.getId();
					Person friendPerson = friendsPK.getFriendid();
					PersonObj personObj = new PersonObj();
					personObj.setId(friendPerson.getId());
					personObj.setPersonName(friendPerson.getName());
					personObj.setEmailId(friendPerson.getEmailid());
					
					double youAreOwed = 0.0;
					Groupexpense groupexpense = new Groupexpense();
					groupexpense.setPaidbypersonid(person);
					List<Groupexpense> groupexpenses = groupExpenseDao.getByCriteria(groupexpense);
					if(groupexpenses!=null && groupexpenses.size()>0){
						for(Groupexpense grpExp:groupexpenses){
							Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
							groupexpensemapping.setGroupexpenseid(grpExp);
							List<Groupexpensemapping> groupexpensemappings = groupexpensemappingDao.getByCriteria(groupexpensemapping);
							if(groupexpensemappings!=null && groupexpensemappings.size()>0){
								for(Groupexpensemapping grpExpMap:groupexpensemappings){
									if(grpExpMap.getPersonid().getId().equalsIgnoreCase(friendPerson.getId())){
										youAreOwed +=grpExpMap.getAmount();
									}	
								}
							}	
						}		
					}
					personObj.setYouAreOwed(youAreOwed);
					
					double youOwe = 0.0;
					groupexpense = new Groupexpense();
					groupexpense.setPaidbypersonid(friendPerson);
					groupexpenses = groupExpenseDao.getByCriteria(groupexpense);
					if(groupexpenses!=null && groupexpenses.size()>0){
						for(Groupexpense grpExp:groupexpenses){
							Groupexpensemapping groupexpensemapping = new Groupexpensemapping();
							groupexpensemapping.setGroupexpenseid(grpExp);
							List<Groupexpensemapping> groupexpensemappings = groupexpensemappingDao.getByCriteria(groupexpensemapping);
							if(groupexpensemappings!=null && groupexpensemappings.size()>0){
								for(Groupexpensemapping grpExpMap:groupexpensemappings){
									if(grpExpMap.getPersonid().getId().equalsIgnoreCase(person.getId())){
										youOwe +=grpExpMap.getAmount();
									}	
								}
							}	
						}		
					}
					
					personObj.setYouOwe(youOwe);	
					personObjs.add(personObj);
				}
			}
			
		}
		return personObjs;
	}


}
