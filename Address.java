package com.cg.billing.beans;

import javax.persistence.Embeddable;
@Embeddable
public class Address {

	private String homeAddressCity, homeAddressState,billingAddressCity, billingAddressState;
	private int homeAddresspinCode,billingAddresspinCode;
	public Address() {}
	public Address(String homeAddressCity, String homeAddressState, String billingAddressCity,
			String billingAddressState, int homeAddresspinCode, int billingAddresspinCode) {
		super();
		this.homeAddressCity = homeAddressCity;
		this.homeAddressState = homeAddressState;
		this.billingAddressCity = billingAddressCity;
		this.billingAddressState = billingAddressState;
		this.homeAddresspinCode = homeAddresspinCode;
		this.billingAddresspinCode = billingAddresspinCode;
	}
	public String getHomeAddressCity() {
		return homeAddressCity;
	}
	public void setHomeAddressCity(String homeAddressCity) {
		this.homeAddressCity = homeAddressCity;
	}
	public String getHomeAddressState() {
		return homeAddressState;
	}
	public void setHomeAddressState(String homeAddressState) {
		this.homeAddressState = homeAddressState;
	}
	public String getBillingAddressCity() {
		return billingAddressCity;
	}
	public void setBillingAddressCity(String billingAddressCity) {
		this.billingAddressCity = billingAddressCity;
	}
	public String getBillingAddressState() {
		return billingAddressState;
	}
	public void setBillingAddressState(String billingAddressState) {
		this.billingAddressState = billingAddressState;
	}
	public int getHomeAddresspinCode() {
		return homeAddresspinCode;
	}
	public void setHomeAddresspinCode(int homeAddresspinCode) {
		this.homeAddresspinCode = homeAddresspinCode;
	}
	public int getBillingAddresspinCode() {
		return billingAddresspinCode;
	}
	public void setBillingAddresspinCode(int billingAddresspinCode) {
		this.billingAddresspinCode = billingAddresspinCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddressCity == null) ? 0 : billingAddressCity.hashCode());
		result = prime * result + ((billingAddressState == null) ? 0 : billingAddressState.hashCode());
		result = prime * result + billingAddresspinCode;
		result = prime * result + ((homeAddressCity == null) ? 0 : homeAddressCity.hashCode());
		result = prime * result + ((homeAddressState == null) ? 0 : homeAddressState.hashCode());
		result = prime * result + homeAddresspinCode;
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
		Address other = (Address) obj;
		if (billingAddressCity == null) {
			if (other.billingAddressCity != null)
				return false;
		} else if (!billingAddressCity.equals(other.billingAddressCity))
			return false;
		if (billingAddressState == null) {
			if (other.billingAddressState != null)
				return false;
		} else if (!billingAddressState.equals(other.billingAddressState))
			return false;
		if (billingAddresspinCode != other.billingAddresspinCode)
			return false;
		if (homeAddressCity == null) {
			if (other.homeAddressCity != null)
				return false;
		} else if (!homeAddressCity.equals(other.homeAddressCity))
			return false;
		if (homeAddressState == null) {
			if (other.homeAddressState != null)
				return false;
		} else if (!homeAddressState.equals(other.homeAddressState))
			return false;
		if (homeAddresspinCode != other.homeAddresspinCode)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Address [homeAddressCity=" + homeAddressCity + ", homeAddressState=" + homeAddressState
				+ ", billingAddressCity=" + billingAddressCity + ", billingAddressState=" + billingAddressState
				+ ", homeAddresspinCode=" + homeAddresspinCode + ", billingAddresspinCode=" + billingAddresspinCode
				+ "]";
	}
	
}