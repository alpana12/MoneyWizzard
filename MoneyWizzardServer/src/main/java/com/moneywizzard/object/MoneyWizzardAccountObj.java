package com.moneywizzard.object;

import java.util.Date;

public class MoneyWizzardAccountObj {

	private String mwaID;
	private String personId;
	private Double accountBalance;
	private boolean receive;
	private String personName;
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public boolean isReceive() {
		return receive;
	}

	public void setReceive(boolean receive) {
		this.receive = receive;
	}

	public String getMwaID() {
		return mwaID;
	}

	public void setMwaID(String mwaID) {
		this.mwaID = mwaID;
	}

		
	public String getPerson() {
		return personId;
	}

	public void setPersonId(String person) {
		this.personId = person;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
}