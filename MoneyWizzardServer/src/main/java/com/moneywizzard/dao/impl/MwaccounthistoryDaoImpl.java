package com.moneywizzard.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.moneywizzard.dao.MwaccounthistoryDao;
import com.moneywizzard.model.Groupexpense;
import com.moneywizzard.model.Mwaccounthistory;
 
 
/**
 * DAO for table: Mwaccounthistory.
 * @author autogenerated
 */
@Repository
public class MwaccounthistoryDaoImpl extends GenericHibernateDaoImpl<Mwaccounthistory, String> implements MwaccounthistoryDao {
	
	/** Constructor method. */
		public MwaccounthistoryDaoImpl() {
			super(Mwaccounthistory.class);
		}

	@Override
	public List<Mwaccounthistory> getByCriteria(Mwaccounthistory mwaccounthistory) {

		try {
			Criteria crit = getSession().createCriteria(getPersistentClass());

			if(mwaccounthistory.getSenderpersonid()!=null){
				crit.add(Restrictions.or(Restrictions.eq("recieverpersonid", mwaccounthistory.getSenderpersonid()),Restrictions.eq("senderpersonid",mwaccounthistory.getSenderpersonid())));
			}
			
			crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return crit.list();
			

		} catch (HibernateException e) {
			throw e;
		}	
	}
	}

