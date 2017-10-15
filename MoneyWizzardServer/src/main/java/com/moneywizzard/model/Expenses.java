package com.moneywizzard.model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

import com.moneywizzard.model.iface.IExpenses;


/** 
 * Object mapping for hibernate-handled table: expenses.
 * @author autogenerated
 */

@Entity
@Table(name = "expenses", catalog = "MoneyWizzard")
public class Expenses implements Cloneable, Serializable, IExpenses {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558953638L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, String> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, String>());
	
	/** hashCode temporary storage. */
	private volatile String hashCode;
	
	/** Field mapping. */
	private Date date;
	/** Field mapping. */
	private Double amount;
	/** Field mapping. */
	private Expensecategory expensecategoryid;
	/** Field mapping. */
	private String id;
	/** Field mapping. */
	private Byte[] image;
	/** Field mapping. */
	private String location;
	/** Field mapping. */
	private Person personid;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Expenses() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Expenses(String id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param amount Double object;
	 * @param id String object;
	 * @param location String object;
	 */
	public Expenses(Double amount, String id, String location) {

		this.amount = amount;
		this.id = id;
		this.location = location;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Expenses.class;
	}
 

    /**
     * Return the value associated with the column: amount.
	 * @return A Double object (this.amount)
	 */
	@Column( nullable = false  )
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
     * Return the value associated with the column: expensecategoryid.
	 * @return A Expensecategory object (this.expensecategoryid)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "ExpenseCategoryId", nullable = true )
	public Expensecategory getExpensecategoryid() {
		return this.expensecategoryid;
		
	}
	

  
    /**  
     * Set the value related to the column: expensecategoryid.
	 * @param expensecategoryid the expensecategoryid value you wish to set
	 */
	public void setExpensecategoryid(final Expensecategory expensecategoryid) {
		this.expensecategoryid = expensecategoryid;
	}

    /**
     * Return the value associated with the column: id.
	 * @return A String object (this.id)
	 */
    @Id 
	@Column( name = "expenseid", nullable = false, length = 36  )
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
     * Return the value associated with the column: image.
	 * @return A Byte[] object (this.image)
	 */
	public Byte[] getImage() {
		return this.image;
		
	}
	

  
    /**  
     * Set the value related to the column: image.
	 * @param image the image value you wish to set
	 */
	public void setImage(final Byte[] image) {
		this.image = image;
	}

    /**
     * Return the value associated with the column: location.
	 * @return A String object (this.location)
	 */
	@Column( nullable = false, length = 21845  )
	public String getLocation() {
		return this.location;
		
	}
	

  
    /**  
     * Set the value related to the column: location.
	 * @param location the location value you wish to set
	 */
	public void setLocation(final String location) {
		this.location = location;
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
     * Return the value associated with the column: date.
	 * @return A Date object (this.date)
	 */
	public Date getDate() {
		return this.date;
		
	}
	

  
    /**  
     * Set the value related to the column: date.
	 * @param date the date value you wish to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Expenses clone() throws CloneNotSupportedException {
		
        final Expenses copy = (Expenses)super.clone();

		copy.setAmount(this.getAmount());
		copy.setExpensecategoryid(this.getExpensecategoryid());
		copy.setId(this.getId());
		copy.setImage(this.getImage());
		copy.setLocation(this.getLocation());
		copy.setPersonid(this.getPersonid());
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
		sb.append("image: " + (this.image == null ? null : Arrays.toString(this.getImage())) + ", ");
		sb.append("location: " + this.getLocation() + ", ");
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
		
		final Expenses that; 
		try {
			that = (Expenses) proxyThat;
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
		result = result && (((getExpensecategoryid() == null) && (that.getExpensecategoryid() == null)) || (getExpensecategoryid() != null && getExpensecategoryid().getId().equals(that.getExpensecategoryid().getId())));	
		result = result && (((getImage() == null) && (that.getImage() == null)) || (getImage() != null && getImage().equals(that.getImage())));
		result = result && (((getLocation() == null) && (that.getLocation() == null)) || (getLocation() != null && getLocation().equals(that.getLocation())));
		result = result && (((getPersonid() == null) && (that.getPersonid() == null)) || (getPersonid() != null && getPersonid().getId().equals(that.getPersonid().getId())));	
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
