
package com.cg.billing.beans;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@Entity 
public class PostpaidAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mobileNoGenerator")
	@SequenceGenerator(name="mobileNoGenerator", sequenceName = "mobile_No_seq",initialValue=981561984)
	private long mobileNo;
	@ManyToOne 
	private Plan plan;
	@ManyToOne
	private Customer customer;
	@OneToMany (mappedBy="postpaidAccount",cascade=CascadeType.ALL)
	@MapKey
	private Map<Integer, Bill> bills;

	public PostpaidAccount() {}

	public PostpaidAccount(long mobileNo, Plan plan, HashMap<Integer, Bill>bills) { 
		super();
		this.mobileNo = mobileNo;
		this.plan = plan; 
		this.bills =bills; 
		}


	public PostpaidAccount(Plan plan) { 
		super(); 
		this.plan = plan; }

	public long getMobileNo() { 
		return mobileNo; }

	public void setMobileNo(long mobileNo) { 
		this.mobileNo = mobileNo; }

	public Plan getPlan() { 
		return plan; }

	public void setPlan(Plan plan) { 
		this.plan = plan; }

	public Map<Integer, Bill> getBills() { 
		return bills; }

	public void setBills(HashMap<Integer, Bill> bills) { 
		this.bills = bills; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bills == null) ? 0 : bills.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + (int) (mobileNo ^ (mobileNo >>> 32));
		result = prime * result + ((plan == null) ? 0 : plan.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostpaidAccount other = (PostpaidAccount) obj;
		if (bills == null) {
			if (other.bills != null)
				return false;
		} else if (!bills.equals(other.bills))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (mobileNo != other.mobileNo)
			return false;
		if (plan == null) {
			if (other.plan != null)
				return false;
		} else if (!plan.equals(other.plan))
			return false;
		return true;
	}

	@Override public String toString() { return "PostpaidAccount [mobileNo=" +
			mobileNo + ", plan=" + plan +  "]"; }

	public PostpaidAccount(Plan plan, Customer customer) {
		super();
		this.plan = plan;
		this.customer = customer;
	}

	public PostpaidAccount(long mobileNo, Plan plan, Customer customer) {
		super();
		this.mobileNo = mobileNo;
		this.plan = plan;
		this.customer = customer;
	}


}
