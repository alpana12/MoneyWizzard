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

import com.moneywizzard.model.iface.IGroup;


/** 
 * Object mapping for hibernate-handled table: group.
 * @author autogenerated
 */

@Entity
@Table(name = "group", catalog = "MoneyWizzard")
public class Group implements Cloneable, Serializable, IGroup {

	/** Serial Version UID. */
	private static final long serialVersionUID = -558953634L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities 
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, String> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, String>());
	
	/** hashCode temporary storage. */
	private volatile String hashCode;
	

	/** Field mapping. */
	private Set<Groupexpense> groupexpenses = new HashSet<Groupexpense>();

	/** Field mapping. */
	private Set<Groupmapping> groupmappings = new HashSet<Groupmapping>();

	/** Field mapping. */
	private String id;
	/** Field mapping. */
	private Byte[] image;
	/** Field mapping. */
	private String name;
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Group() {
		// Default constructor
	} 

	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Group(String id) {
		this.id = id;
	}
	
 


 
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Group.class;
	}
 

    /**
     * Return the value associated with the column: groupexpense.
	 * @return A Set&lt;Groupexpense&gt; object (this.groupexpense)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "groupid"  )
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
		groupexpense.setGroupid(this);
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
     * Return the value associated with the column: groupmapping.
	 * @return A Set&lt;Groupmapping&gt; object (this.groupmapping)
	 */
 	@OneToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "id.groupid"  )
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
		groupmapping.getId().setGroupid(this);
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
	@Column( name = "groupid", nullable = false, length = 36  )
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
     * Return the value associated with the column: name.
	 * @return A String object (this.name)
	 */
	@Column( length = 60  )
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
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Group clone() throws CloneNotSupportedException {
		
        final Group copy = (Group)super.clone();

		if (this.getGroupexpenses() != null) {
			copy.getGroupexpenses().addAll(this.getGroupexpenses());
		}
		if (this.getGroupmappings() != null) {
			copy.getGroupmappings().addAll(this.getGroupmappings());
		}
		copy.setId(this.getId());
		copy.setImage(this.getImage());
		copy.setName(this.getName());
		return copy;
	}
	


	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("id: " + this.getId() + ", ");
		sb.append("image: " + (this.image == null ? null : Arrays.toString(this.getImage())) + ", ");
		sb.append("name: " + this.getName());
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
		
		final Group that; 
		try {
			that = (Group) proxyThat;
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
		result = result && (((getImage() == null) && (that.getImage() == null)) || (getImage() != null && getImage().equals(that.getImage())));
		result = result && (((getName() == null) && (that.getName() == null)) || (getName() != null && getName().equals(that.getName())));
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
