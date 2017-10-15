package com.moneywizzard.object;

import java.util.Date;

import com.moneywizzard.model.Person;

public class MoneyWizzardAccountHistoryObj {

	private String id;

	private Double amount;

	private Date date;

	private String recieverPersonId;

	private String senderPersonId;

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRecieverPersonId() {
		return recieverPersonId;
	}

	public void setRecieverPersonId(String recieverPersonId) {
		this.recieverPersonId = recieverPersonId;
	}

	public String getSenderPersonId() {
		return senderPersonId;
	}

	public void setSenderPersonId(String senderPersonId) {
		this.senderPersonId = senderPersonId;
	}

}