package com.ims.base.hashutils;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 * 
 * Explains how to override the default methods
 * equals and hashcode of object class.
 * 
 */
public class Employee {
	
	/** The employee id. */
	protected long employeeId;
	
	/** The first name. */
	protected String firstName;
	
	/** The last name. */
	protected String lastName;

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Employee))
			return false;

		Employee other = (Employee) o;
		if (this.employeeId != other.employeeId)
			return false;
		if (!this.firstName.equals(other.firstName))
			return false;
		if (!this.lastName.equals(other.lastName))
			return false;

		return true;
	}

	/**
	 * Hash code.
	 *
	 * @param o the o
	 * @return the int
	 */
	public int hashCode(Object o) {
		return (int) this.employeeId * firstName.hashCode()
				* lastName.hashCode();
	}
}
