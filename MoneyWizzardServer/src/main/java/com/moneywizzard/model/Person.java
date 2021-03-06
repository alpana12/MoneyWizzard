package com.moneywizzard.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.proxy.HibernateProxy;

import com.moneywizzard.model.iface.IPerson;


/** 
 * Object mapping for hibernate-handled table: person.
 * @author autogenerated
 */

@Entity
@Table(name = "person", catalog = "MoneyWizzard")
public class Person implements Cloneable, Serializable, IPerson {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558953626L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, String> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, String>());
	
	/** hashCode temporary storage. */
	private volatile String hashCode;
	

	/** Field mapping. */
	private Set<Budget> budgets = new HashSet<Budget>();

	/** Field mapping. */
	private String emailid;
	/** Field mapping. */
	private Set<Expenses> expenseses = new HashSet<Expenses>();

	/** Field mapping. */
	private Set<Friends> friendss = new HashSet<Friends>();

	/** Field mapping. */
	private Set<Groupexpense> groupexpenses = new HashSet<Groupexpense>();

	/** Field mapping. */
	private Set<Groupexpensemapping> groupexpensemappings = new HashSet<Groupexpensemapping>();

	/** Field mapping. */
	private Set<Groupmapping> groupmappings = new HashSet<Groupmapping>();

	/** Field mapping. */
	private String id;
	/** Field mapping. */
	private Set<Moneywizardaccount> moneywizardaccounts = new HashSet<Moneywizardaccount>();

	/** Field mapping. */
	private Set<Mwaccounthistory> mwaccounthistories = new HashSet<Mwaccounthistory>();

	/** Field mapping. */
	private String name;
	/** Field mapping. */
	private String password;
	/** Field mapping. */
	private Integer phonenumber;
	/** Field mapping. */
	private Byte[] photo;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Person() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Person(String id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param emailid String object;
	 * @param id String object;
	 * @param name String object;
	 * @param password String object;
	 */
	public Person(String emailid, String id, String name, 					
			String password) {

		this.emailid = emailid;
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Person.class;
	}
 

    /**
     * Return the value associated with the column: budget.
	 * @return A Set&lt;Budget&gt; object (this.budget)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "personid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Budget> getBudgets() {
		return this.budgets;
		
	}
	
	/**
	 * Adds a bi-directional link of type Budget to the budgets set.
	 * @param budget item to add
	 */
	public void addBudget(Budget budget) {
		budget.setPersonid(this);
		this.budgets.add(budget);
	}

  
    /**  
     * Set the value related to the column: budget.
	 * @param budget the budget value you wish to set
	 */
	public void setBudgets(final Set<Budget> budget) {
		this.budgets = budget;
	}

    /**
     * Return the value associated with the column: emailid.
	 * @return A String object (this.emailid)
	 */
	@Column( nullable = false, length = 21845  )
	public String getEmailid() {
		return this.emailid;
		
	}
	

  
    /**  
     * Set the value related to the column: emailid.
	 * @param emailid the emailid value you wish to set
	 */
	public void setEmailid(final String emailid) {
		this.emailid = emailid;
	}

    /**
     * Return the value associated with the column: expenses.
	 * @return A Set&lt;Expenses&gt; object (this.expenses)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "personid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Expenses> getExpenseses() {
		return this.expenseses;
		
	}
	
	/**
	 * Adds a bi-directional link of type Expenses to the expenseses set.
	 * @param expenses item to add
	 */
	public void addExpenses(Expenses expenses) {
		expenses.setPersonid(this);
		this.expenseses.add(expenses);
	}

  
    /**  
     * Set the value related to the column: expenses.
	 * @param expenses the expenses value you wish to set
	 */
	public void setExpenseses(final Set<Expenses> expenses) {
		this.expenseses = expenses;
	}

    /**
     * Return the value associated with the column: friends.
	 * @return A Set&lt;Friends&gt; object (this.friends)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "id.friendid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Friends> getFriendss() {
		return this.friendss;
		
	}
	
	/**
	 * Adds a bi-directional link of type Friends to the friendss set.
	 * @param friends item to add
	 */
	public void addFriends(Friends friends) {
		friends.getId().setFriendid(this);
		this.friendss.add(friends);
	}

  
    /**  
     * Set the value related to the column: friends.
	 * @param friends the friends value you wish to set
	 */
	public void setFriendss(final Set<Friends> friends) {
		this.friendss = friends;
	}

    /**
     * Return the value associated with the column: groupexpense.
	 * @return A Set&lt;Groupexpense&gt; object (this.groupexpense)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "paidbypersonid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Groupexpense> getGroupexpenses() {
		return this.groupexpenses;
		
	}
	
	/**
	 * Adds a bi-directional link of type Groupexpense to the groupexpenses set.
	 * @param groupexpense item to add
	 */
	public void addGroupexpense(Groupexpense groupexpense) {
		groupexpense.setPaidbypersonid(this);
		this.groupexpenses.add(groupexpense);
	}

  
    /**  
     * Set the value related to the column: groupexpense.
	 * @param groupexpense the groupexpense value you wish to set
	 */
	public void setGroupexpenses(final Set<Groupexpense> groupexpense) {
		this.groupexpenses = groupexpense;
	}

    /**
     * Return the value associated with the column: groupexpensemapping.
	 * @return A Set&lt;Groupexpensemapping&gt; object (this.groupexpensemapping)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "personid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Groupexpensemapping> getGroupexpensemappings() {
		return this.groupexpensemappings;
		
	}
	
	/**
	 * Adds a bi-directional link of type Groupexpensemapping to the groupexpensemappings set.
	 * @param groupexpensemapping item to add
	 */
	public void addGroupexpensemapping(Groupexpensemapping groupexpensemapping) {
		groupexpensemapping.setPersonid(this);
		this.groupexpensemappings.add(groupexpensemapping);
	}

  
    /**  
     * Set the value related to the column: groupexpensemapping.
	 * @param groupexpensemapping the groupexpensemapping value you wish to set
	 */
	public void setGroupexpensemappings(final Set<Groupexpensemapping> groupexpensemapping) {
		this.groupexpensemappings = groupexpensemapping;
	}

    /**
     * Return the value associated with the column: groupmapping.
	 * @return A Set&lt;Groupmapping&gt; object (this.groupmapping)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "id.personid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Groupmapping> getGroupmappings() {
		return this.groupmappings;
		
	}
	
	/**
	 * Adds a bi-directional link of type Groupmapping to the groupmappings set.
	 * @param groupmapping item to add
	 */
	public void addGroupmapping(Groupmapping groupmapping) {
		groupmapping.getId().setPersonid(this);
		this.groupmappings.add(groupmapping);
	}

  
    /**  
     * Set the value related to the column: groupmapping.
	 * @param groupmapping the groupmapping value you wish to set
	 */
	public void setGroupmappings(final Set<Groupmapping> groupmapping) {
		this.groupmappings = groupmapping;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A String object (this.id)
	 */
    @Id 
	@Column( name = "personid", nullable = false, length = 36  )
	public String getId() {
		return this.id;
		
	}
	

  
    /**  
     * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final String id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null ) &&
				(id != null) &&
				(this.hashCode != null) ) {
			SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}

    /**
     * Return the value associated with the column: moneywizardaccount.
	 * @return A Set&lt;Moneywizardaccount&gt; object (this.moneywizardaccount)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "personid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Moneywizardaccount> getMoneywizardaccounts() {
		return this.moneywizardaccounts;
		
	}
	
	/**
	 * Adds a bi-directional link of type Moneywizardaccount to the moneywizardaccounts set.
	 * @param moneywizardaccount item to add
	 */
	public void addMoneywizardaccount(Moneywizardaccount moneywizardaccount) {
		moneywizardaccount.setPersonid(this);
		this.moneywizardaccounts.add(moneywizardaccount);
	}

  
    /**  
     * Set the value related to the column: moneywizardaccount.
	 * @param moneywizardaccount the moneywizardaccount value you wish to set
	 */
	public void setMoneywizardaccounts(final Set<Moneywizardaccount> moneywizardaccount) {
		this.moneywizardaccounts = moneywizardaccount;
	}

    /**
     * Return the value associated with the column: mwaccounthistory.
	 * @return A Set&lt;Mwaccounthistory&gt; object (this.mwaccounthistory)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "recieverpersonid"  )
 	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Column( nullable = false  )
	public Set<Mwaccounthistory> getMwaccounthistories() {
		return this.mwaccounthistories;
		
	}
	
	/**
	 * Adds a bi-directional link of type Mwaccounthistory to the mwaccounthistories set.
	 * @param mwaccounthistory item to add
	 */
	public void addMwaccounthistory(Mwaccounthistory mwaccounthistory) {
		mwaccounthistory.setRecieverpersonid(this);
		this.mwaccounthistories.add(mwaccounthistory);
	}

  
    /**  
     * Set the value related to the column: mwaccounthistory.
	 * @param mwaccounthistory the mwaccounthistory value you wish to set
	 */
	public void setMwaccounthistories(final Set<Mwaccounthistory> mwaccounthistory) {
		this.mwaccounthistories = mwaccounthistory;
	}

    /**
     * Return the value associated with the column: name.
	 * @return A String object (this.name)
	 */
	@Column( nullable = false, length = 50  )
	public String getName() {
		return this.name;
		
	}
	

  
    /**  
     * Set the value related to the column: name.
	 * @param name the name value you wish to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

    /**
     * Return the value associated with the column: password.
	 * @return A String object (this.password)
	 */
	@Column( nullable = false, length = 20  )
	public String getPassword() {
		return this.password;
		
	}
	

  
    /**  
     * Set the value related to the column: password.
	 * @param password the password value you wish to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

    /**
     * Return the value associated with the column: phonenumber.
	 * @return A Integer object (this.phonenumber)
	 */
	public Integer getPhonenumber() {
		return this.phonenumber;
		
	}
	

  
    /**  
     * Set the value related to the column: phonenumber.
	 * @param phonenumber the phonenumber value you wish to set
	 */
	public void setPhonenumber(final Integer phonenumber) {
		this.phonenumber = phonenumber;
	}

    /**
     * Return the value associated with the column: photo.
	 * @return A Byte[] object (this.photo)
	 */
	public Byte[] getPhoto() {
		return this.photo;
		
	}
	

  
    /**  
     * Set the value related to the column: photo.
	 * @param photo the photo value you wish to set
	 */
	public void setPhoto(final Byte[] photo) {
		this.photo = photo;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Person clone() throws CloneNotSupportedException {
		
        final Person copy = (Person)super.clone();

		if (this.getBudgets() != null) {
			copy.getBudgets().addAll(this.getBudgets());
		}
		copy.setEmailid(this.getEmailid());
		if (this.getExpenseses() != null) {
			copy.getExpenseses().addAll(this.getExpenseses());
		}
		if (this.getFriendss() != null) {
			copy.getFriendss().addAll(this.getFriendss());
		}
		if (this.getGroupexpenses() != null) {
			copy.getGroupexpenses().addAll(this.getGroupexpenses());
		}
		if (this.getGroupexpensemappings() != null) {
			copy.getGroupexpensemappings().addAll(this.getGroupexpensemappings());
		}
		if (this.getGroupmappings() != null) {
			copy.getGroupmappings().addAll(this.getGroupmappings());
		}
		copy.setId(this.getId());
		if (this.getMoneywizardaccounts() != null) {
			copy.getMoneywizardaccounts().addAll(this.getMoneywizardaccounts());
		}
		if (this.getMwaccounthistories() != null) {
			copy.getMwaccounthistories().addAll(this.getMwaccounthistories());
		}
		copy.setName(this.getName());
		copy.setPassword(this.getPassword());
		copy.setPhonenumber(this.getPhonenumber());
		copy.setPhoto(this.getPhoto());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("emailid: " + this.getEmailid() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("name: " + this.getName() + ", ");
		sb.append("password: " + this.getPassword() + ", ");
		sb.append("phonenumber: " + this.getPhonenumber() + ", ");
		sb.append("photo: " + (this.photo == null ? null : Arrays.toString(this.getPhoto())));
		return sb.toString();		
	}


	/** Equals implementation. 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;
		
		if ( this == aThat ) {
			 return true;
		}

		
		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation(); 
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}
		
		final Person that; 
		try {
			that = (Person) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}
		
		
		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getEmailid() == null) && (that.getEmailid() == null)) || (getEmailid() != null && getEmailid().equals(that.getEmailid())));
		result = result && (((getName() == null) && (that.getName() == null)) || (getName() != null && getName().equals(that.getName())));
		result = result && (((getPassword() == null) && (that.getPassword() == null)) || (getPassword() != null && getPassword().equals(that.getPassword())));
		result = result && (((getPhonenumber() == null) && (that.getPhonenumber() == null)) || (getPhonenumber() != null && getPhonenumber().equals(that.getPhonenumber())));
		result = result && (((getPhoto() == null) && (that.getPhoto() == null)) || (getPhoto() != null && getPhoto().equals(that.getPhoto())));
		return result;
	}
	
	/** Calculate the hashcode.
	 * @see java.lang.Object#hashCode()
	 * @return a calculated number
	 */
	@Override
	public int hashCode() {
		if ( this.hashCode == null ) {
			synchronized ( this ) {
				if ( this.hashCode == null ) {
					String newHashCode = null;

					if ( getId() != null ) {
						newHashCode = SAVED_HASHES.get( getId() );
					}
					
					if ( newHashCode == null ) {
						if ( getId() != null ) {
							newHashCode = getId();
						} else {
						newHashCode = String.valueOf(super.hashCode());

						}
					}
					
					this.hashCode = newHashCode;
				}
			}
		}
	return this.hashCode.hashCode();
	}
	

	
}
