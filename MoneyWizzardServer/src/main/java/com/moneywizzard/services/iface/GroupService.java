package com.moneywizzard.services.iface;

import java.util.List;

import com.moneywizzard.object.GroupObj;
import com.moneywizzard.object.PersonObj;


public interface GroupService {

	GroupObj getGroupDetails(String id)throws Exception;

	String createGroup(GroupObj groupObj) throws Exception;


}
