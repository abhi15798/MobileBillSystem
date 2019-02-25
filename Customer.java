package com.cg.billing.beans;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerID;
	private String firstName, lastName, emailID, dateOfBirth;
	@Embedded
	private Address address;
	@OneToMany(mappedBy="customer",cascade=CascadeType.ALL)
	@MapKey
	private Map<Long, PostpaidAccount> postpaidAccounts;
	public Customer() {}
	public Customer(int customerID, String firstName, String lastName, String emailID, String dateOfBirth,
		Address address, Map<Long, PostpaidAccount> postpaidAccounts) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.postpaidAccounts = postpaidAccounts;
	}
	public Customer(String firstName, String lastName, String emailID, String dateOfBirth, Address address,
			Map<Long, PostpaidAccount> postpaidAccounts) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.postpaidAccounts = postpaidAccounts;
	}
	public Customer(String firstName, String lastName, String emailID, String dateOfBirth, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Map<Long, PostpaidAccount> getPostpaidAccounts() {
		return postpaidAccounts;
	}
	public void setPostpaidAccounts(Map<Long, PostpaidAccount> postpaidAccounts) {
		this.postpaidAccounts = postpaidAccounts;
	}
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailID=" + emailID + ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", postpaidAccounts=" + postpaidAccounts + "]";
	}
	
}