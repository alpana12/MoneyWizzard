package com.moneywizzard.services.iface;

import java.util.List;

import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;
import com.moneywizzard.object.ReportsObj;


public interface PersonServices {

	public PersonObj createAccount(PersonObj personObj) throws Exception;

	public PersonObj authenticateUser(PersonObj personObj) throws Exception;

	public List<GroupObj> getPersonGroups(String personId)throws Exception;

	public ReportsObj getReportsForPerson(String id)throws Exception;

	public List<PersonObj> findPersonByEmail(String email)throws Exception;

	public String addFriend(String personId, String friendId)throws Exception;

	public List<PersonObj> getFriends(String personId)throws Exception;
}
