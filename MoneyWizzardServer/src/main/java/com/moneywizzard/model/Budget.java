package com.moneywizzard.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.proxy.HibernateProxy;

import com.moneywizzard.model.iface.IBudget;


/** 
 * Object mapping for hibernate-handled table: budget.
 * @author autogenerated
 */

@Entity
@Table(name = "budget", catalog = "MoneyWizzard")
public class Budget implements Cloneable, Serializable, IBudget {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558953640L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, String> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, String>());
	
	/** hashCode temporary storage. */
	private volatile String hashCode;
	

	/** Field mapping. */
	private Double amount;
	/** Field mapping. */
	private String id;
	/** Field mapping. */
	private Integer month;
	/** Field mapping. */
	private Person personid;
	/** Field mapping. */
	private Integer year;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Budget() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Budget(String id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Budget.class;
	}
 

    /**
     * Return the value associated with the column: amount.
	 * @return A Double object (this.amount)
	 */
	public Double getAmount() {
		return this.amount;
		
	}
	

  
    /**  
     * Set the value related to the column: amount.
	 * @param amount the amount value you wish to set
	 */
	public void setAmount(final Double amount) {
		this.amount = amount;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A String object (this.id)
	 */
    @Id 
	@Column( name = "budgetid", nullable = false, length = 36  )
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
     * Return the value associated with the column: month.
	 * @return A Integer object (this.month)
	 */
	public Integer getMonth() {
		return this.month;
		
	}
	

  
    /**  
     * Set the value related to the column: month.
	 * @param month the month value you wish to set
	 */
	public void setMonth(final Integer month) {
		this.month = month;
	}

    /**
     * Return the value associated with the column: personid.
	 * @return A Person object (this.personid)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "personId", nullable = true )
	public Person getPersonid() {
		return this.personid;
		
	}
	

  
    /**  
     * Set the value related to the column: personid.
	 * @param personid the personid value you wish to set
	 */
	public void setPersonid(final Person personid) {
		this.personid = personid;
	}

    /**
     * Return the value associated with the column: year.
	 * @return A Integer object (this.year)
	 */
	public Integer getYear() {
		return this.year;
		
	}
	

  
    /**  
     * Set the value related to the column: year.
	 * @param year the year value you wish to set
	 */
	public void setYear(final Integer year) {
		this.year = year;
	}


   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Budget clone() throws CloneNotSupportedException {
		
        final Budget copy = (Budget)super.clone();

		copy.setAmount(this.getAmount());
		copy.setId(this.getId());
		copy.setMonth(this.getMonth());
		copy.setPersonid(this.getPersonid());
		copy.setYear(this.getYear());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("amount: " + this.getAmount() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("month: " + this.getMonth() + ", ");
		sb.append("year: " + this.getYear());
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
		
		final Budget that; 
		try {
			that = (Budget) proxyThat;
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
		result = result && (((getAmount() == null) && (that.getAmount() == null)) || (getAmount() != null && getAmount().equals(that.getAmount())));
		result = result && (((getMonth() == null) && (that.getMonth() == null)) || (getMonth() != null && getMonth().equals(that.getMonth())));
		result = result && (((getPersonid() == null) && (that.getPersonid() == null)) || (getPersonid() != null && getPersonid().getId().equals(that.getPersonid().getId())));	
		result = result && (((getYear() == null) && (that.getYear() == null)) || (getYear() != null && getYear().equals(that.getYear())));
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