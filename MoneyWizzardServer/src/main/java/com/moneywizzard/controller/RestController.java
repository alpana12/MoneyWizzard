package com.moneywizzard.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.moneywizzard.object.BudgetObj;
import com.moneywizzard.object.ExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.MoneyWizzardAccountHistoryObj;
import com.moneywizzard.object.MoneyWizzardAccountObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.object.ReportsObj;
import com.moneywizzard.services.iface.BudgetService;
import com.moneywizzard.services.iface.ExpenseService;
import com.moneywizzard.services.iface.GroupService;
import com.moneywizzard.services.iface.MoneyWizzardAccountService;
import com.moneywizzard.services.iface.PersonServices;
import com.moneywizzard.utils.Response;

@Controller
@RequestMapping("/rest")
public class RestController {

	@Autowired
	private PersonServices personServices;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private MoneyWizzardAccountService moneyWizzardAccountService;
	
	
	Gson gson = new Gson();
	
	static final Logger logger = Logger.getLogger(RestController.class);
	
	@RequestMapping(value = "/person/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response createAccount(@RequestBody String personStr) {
		try {
			PersonObj obj = gson.fromJson(personStr, PersonObj.class);
			obj = personServices.createAccount(obj);
			
			Response<PersonObj> resp = new Response<PersonObj>();
			resp.setStatus("200");
			resp.getObjectList().add(obj);
			return resp;
		} catch (Exception e) {
			Response<PersonObj> resp = new Response<PersonObj>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/authenticate", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response authenticateUser(@RequestBody String personStr) {
		try {
			PersonObj personObj = gson.fromJson(personStr, PersonObj.class);
			personObj = personServices.authenticateUser(personObj);
			
			Response<PersonObj> resp = new Response<PersonObj>();
			resp.setStatus("200");
			resp.getObjectList().add(personObj);
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			Response<PersonObj> resp = new Response<PersonObj>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/{id}/group", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getPersonGroups(@PathVariable("id") String id) {
		try {
			List<GroupObj> objs= personServices.getPersonGroups(id);
			
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(objs);
			return resp;
		} catch (Exception e) {
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getGroupDetails(@PathVariable("id") String id) {
		try {
			GroupObj objs= groupService.getGroupDetails(id);	
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("200");
			resp.getObjectList().add(objs);
			return resp;
		} catch (Exception e) {
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/expense", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response addExpense(@RequestBody String expenseStr) {
		try {
			ExpenseObj obj = gson.fromJson(expenseStr, ExpenseObj.class);
			obj= expenseService.addExpense(obj);	
			Response<ExpenseObj> resp = new Response<>();
			resp.setStatus("200");
			resp.getObjectList().add(obj);
			return resp;
		} catch (Exception e) {
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/{id}/reports", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getReportsForPerson(@PathVariable("id") String id) {
		try {
			ReportsObj objs= personServices.getReportsForPerson(id);	
			Response<ReportsObj> resp = new Response<>();
			resp.setStatus("200");
			resp.getObjectList().add(objs);
			return resp;
		} catch (Exception e) {
			Response<ReportsObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response findPersonByEmail(@PathVariable("email") String email) {
		try {
			List<PersonObj> personObjs = personServices.findPersonByEmail(email);	
			Response<PersonObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(personObjs);
			return resp;
		} catch (Exception e) {
			Response<PersonObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	
	@RequestMapping(value = "/person/{personId}/friend/{friendId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response addFriend(@PathVariable("personId") String personId,@PathVariable("friendId") String friendId) {
		try {
			String str = personServices.addFriend(personId,friendId);	
			Response<String> resp = new Response<>();
			resp.setStatus("200");
			resp.getObjectList().add(str);
			return resp;
		} catch (Exception e) {
			Response<String> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/{personId}/friends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getFriends(@PathVariable("personId") String personId) {
		try {
			List<PersonObj> objs = personServices.getFriends(personId);	
			Response<PersonObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(objs);
			return resp;
		} catch (Exception e) {
			Response<PersonObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	
	@RequestMapping(value = "/group", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response createGroup(@RequestBody String groupNameStr) {
        try {
            GroupObj groupObj = gson.fromJson(groupNameStr, GroupObj.class);

            String groupId = groupService.createGroup(groupObj);

            Response<String> resp = new Response<>();
            resp.setStatus("200");
            resp.getObjectList().add(groupId);
            return resp;
        } catch (Exception e) {
            Response<GroupObj> resp = new Response<>();
            resp.setStatus("400");
            resp.setMessage(e.getMessage());
            return resp;
        }

    }
	
	@RequestMapping(value = "/person/{id}/expense", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getExpenses(@PathVariable("id") String id) {
		try {
			List<ExpenseObj> objs= expenseService.getExpenses(id);	
			Response<ExpenseObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(objs);
			return resp;
		} catch (Exception e) {
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/budget", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response addBudget(@RequestBody String groupNameStr) {
		try {
			BudgetObj budgetObj = gson.fromJson(groupNameStr, BudgetObj.class);
			String str= budgetService.addBudget(budgetObj);	
			Response<String> resp = new Response<>();
			resp.setStatus("200");
			resp.getObjectList().add(str);
			return resp;
		} catch (Exception e) {
			Response<GroupObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	
	@RequestMapping(value = "/person/{id}/budgets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getBudgets(@PathVariable("id") String id) {
		try {
			List<BudgetObj> objs= budgetService.getBudgets(id);	
			Response<BudgetObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(objs);
			return resp;
		} catch (Exception e) {
			Response<BudgetObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/wallet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response addMoney(@RequestBody String moneyWizardAccount) {
        try {

            MoneyWizzardAccountObj moneyWizzardAccountObj = gson.fromJson(moneyWizardAccount,MoneyWizzardAccountObj.class);

            String personId = moneyWizzardAccountService.addMoney(moneyWizzardAccountObj);

            Response<String> resp = new Response<>();
            resp.setStatus("200");
            resp.getObjectList().add(personId);
            return resp;
        } catch (Exception e) {
            Response<GroupObj> resp = new Response<>();
            resp.setStatus("400");
            resp.setMessage(e.getMessage());
            return resp;
        }

    }
	
	@RequestMapping(value = "/person/{personId}/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response getCurrentBalance(@PathVariable("personId") String personId) {
        try {
            Double balance = moneyWizzardAccountService.getCurrentBalance(personId);

            Response<Double> resp = new Response<>();
            resp.setStatus("200");
            resp.getObjectList().add(balance);
            return resp;
        } catch (Exception e) {
            Response<PersonObj> resp = new Response<>();
            resp.setStatus("400");
            resp.setMessage(e.getMessage());
            return resp;
        }

    }
	
	@RequestMapping(value = "/person/{id}/accountHistory", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response getAccountHistory(@PathVariable("id") String id) {
		try {
			List<MoneyWizzardAccountObj> objs= moneyWizzardAccountService.getAccountHistory(id);	
			Response<MoneyWizzardAccountObj> resp = new Response<>();
			resp.setStatus("200");
			resp.setObjectList(objs);
			return resp;
		} catch (Exception e) {
			Response<BudgetObj> resp = new Response<>();
			resp.setStatus("400");
			resp.setMessage(e.getMessage());
			return resp;
		}

	}
	
	@RequestMapping(value = "/person/wallet/transferMoney", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Response transferMoney(@RequestBody String moneyWizardAccountHistory) {
        try {

            MoneyWizzardAccountHistoryObj moneyWizzardAccountHistoryObj = gson.fromJson(moneyWizardAccountHistory,
                    MoneyWizzardAccountHistoryObj.class);

            String trasnferStatus = moneyWizzardAccountService.transferMoney(moneyWizzardAccountHistoryObj);

            Response<String> resp = new Response<>();
            resp.setStatus("200");
            resp.getObjectList().add(trasnferStatus);
            return resp;
        } catch (Exception e) {
            Response<GroupObj> resp = new Response<>();
            resp.setStatus("400");
            resp.setMessage(e.getMessage());
            return resp;
        }
    }
}
