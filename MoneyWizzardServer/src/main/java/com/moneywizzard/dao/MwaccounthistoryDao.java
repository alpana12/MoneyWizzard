package com.moneywizzard.dao;

import java.util.List;

import com.moneywizzard.model.Mwaccounthistory;
 
/**
 * DAO interface for table: Mwaccounthistory.
 * @author autogenerated
 */
public interface MwaccounthistoryDao extends GenericDAO<Mwaccounthistory,  String>  {

	List<Mwaccounthistory> getByCriteria(Mwaccounthistory mwaccounthistory);
	// constructor only
}

