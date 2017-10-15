package com.moneywizzard.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.moneywizzard.dao.FriendsDao;
import com.moneywizzard.model.Friends;
import com.moneywizzard.model.FriendsPK;
import com.moneywizzard.model.Person;
import com.moneywizzard.utils.MWUtils;
 
/**
 * DAO for table: Friends.
 * @author autogenerated
 */
@Repository
public class FriendsDaoImpl extends GenericHibernateDaoImpl<Friends, FriendsPK> implements FriendsDao {
	
	/** Constructor method. */
		public FriendsDaoImpl() {
			super(Friends.class);
		}
		
		@Override
		@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
		public List<Friends> getByCriteria(Friends friend) throws Exception{

			try {
				Criteria crit = getSession().createCriteria(getPersistentClass());

				if(friend.getId()!=null && friend.getId().getPersonid()!=null){
					crit.add(Restrictions.eq("id.personid", friend.getId().getPersonid()));
				}
				if(friend.getId()!=null && friend.getId().getFriendid()!=null){
					crit.add(Restrictions.eq("id.friendid", friend.getId().getFriendid()));
				}

				return crit.list();


			} catch (HibernateException e) {
				throw e;
			}	

		}
	}

