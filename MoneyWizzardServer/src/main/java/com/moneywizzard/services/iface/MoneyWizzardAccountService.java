package com.moneywizzard.services.iface;

import java.util.List;

import com.moneywizzard.model.Person;
import com.moneywizzard.object.MoneyWizzardAccountHistoryObj;
import com.moneywizzard.object.MoneyWizzardAccountObj;

public interface MoneyWizzardAccountService {
	public String addMoney(MoneyWizzardAccountObj moneyWizzardAccountObj) throws Exception;

	public Double getCurrentBalance(String personId) throws Exception;

	public List<MoneyWizzardAccountObj> getAccountHistory(String id)throws Exception;

	String transferMoney(MoneyWizzardAccountHistoryObj moneyWizzardAccountHistoryObj) throws Exception;
}