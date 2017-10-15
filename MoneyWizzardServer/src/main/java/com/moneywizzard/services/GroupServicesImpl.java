package com.moneywizzard.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneywizzard.dao.BudgetDao;
import com.moneywizzard.dao.ExpensesDao;
import com.moneywizzard.dao.GroupDao;
import com.moneywizzard.dao.GroupexpenseDao;
import com.moneywizzard.dao.GroupexpensemappingDao;
import com.moneywizzard.dao.GroupmappingDao;
import com.moneywizzard.dao.PersonDao;
import com.moneywizzard.model.Budget;
import com.moneywizzard.model.Expenses;
import com.moneywizzard.model.Group;
import com.moneywizzard.model.Groupexpense;
import com.moneywizzard.model.Groupexpensemapping;
import com.moneywizzard.model.Groupmapping;
import com.moneywizzard.model.GroupmappingPK;
import com.moneywizzard.model.Person;
import com.moneywizzard.object.CategoryExpenseObj;
import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.services.iface.GroupService;
import com.moneywizzard.services.iface.PersonServices;
import com.moneywizzard.utils.MWUtils;

@Service
public class GroupServicesImpl implements GroupService {

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

	@Override
	public GroupObj getGroupDetails(String id) throws Exception {
		GroupObj groupObj = new GroupObj();
		if(!MWUtils.isNullOrBlank(id)){
				Group group = groupDao.getById(id);
				groupObj.setName(group.getName());
				groupObj.setId(group.getId());
				if(group!=null){
					Groupmapping groupmapping = new Groupmapping();
					GroupmappingPK groupmappingPK =new GroupmappingPK();
					groupmappingPK.setGroupid(group);
					groupmapping.setId(groupmappingPK);
					
					List<Groupmapping> groupmappings = groupmappingDao.getByCriteria(groupmapping);
					if(groupmappings!=null && groupmappings.size()>0){
						List<PersonObj> personObjs = new ArrayList<>();
						for(Groupmapping grpMap:groupmappings){
							Person person = grpMap.getId().getPersonid();
							PersonObj personObj = new PersonObj();
							personObj.setId(person.getId());
							personObj.setPersonName(person.getName());
							personObj.setEmailId(person.getEmailid());
							personObjs.add(personObj);
						}
						groupObj.setPersonObjs(personObjs);
					}
				}
		}
		return groupObj;
	}
	
	@Override
    @Transactional
    public String createGroup(GroupObj groupObj) throws Exception {
        // TODO Auto-generated method stub
        if (groupObj != null) {

                Group group = new Group();
                group.setId(MWUtils.generateGUID());
                group.setName(groupObj.getName());
                groupDao.save(group);
                

                for (PersonObj personObj : groupObj.getPersonObjs()) {

                    Person person = personDao.getById(personObj.getId());

                    Groupmapping groupmapping = new Groupmapping();
                    GroupmappingPK groupmappingPKid = new GroupmappingPK();

                    groupmappingPKid.setGroupid(group);
                    groupmappingPKid.setPersonid(person);

                    groupmapping.setId(groupmappingPKid);

                    groupmappingDao.save(groupmapping);

                }
                return group.getId();
        }
        return null;
    }


}
