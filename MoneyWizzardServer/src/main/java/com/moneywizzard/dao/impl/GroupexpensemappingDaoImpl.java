package com.moneywizzard.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moneywizzard.dao.GroupexpensemappingDao;
import com.moneywizzard.model.Budget;
import com.moneywizzard.model.Groupexpensemapping;
 
 
/**
 * DAO for table: Groupexpensemapping.
 * @author autogenerated
 */
@Repository
public class GroupexpensemappingDaoImpl extends GenericHibernateDaoImpl<Groupexpensemapping, String> implements GroupexpensemappingDao {
	
	/** Constructor method. */
		public GroupexpensemappingDaoImpl() {
			super(Groupexpensemapping.class);
		}
		
		@Override
		@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
		public List<Groupexpensemapping> getByCriteria(Groupexpensemapping groupexpensemapping) throws Exception{

			try {
				Criteria crit = getSession().createCriteria(getPersistentClass());

				if(groupexpensemapping.getGroupexpenseid()!=null){
					crit.add(Restrictions.eq("groupexpenseid", groupexpensemapping.getGroupexpenseid()));
				}
				

				return crit.list();

			} catch (HibernateException e) {
				throw e;
			}	

		}
	}

