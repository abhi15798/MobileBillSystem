package com.cg.billing.services;
import java.util.ArrayList;
import java.util.List;
import com.cg.billing.beans.Address;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDao;
import com.cg.billing.daoservices.BillDaoImpl;
import com.cg.billing.daoservices.CustomerDao;
import com.cg.billing.daoservices.CustomerdaoImpl;
import com.cg.billing.daoservices.PlanDao;
import com.cg.billing.daoservices.PlanDaoImpl;
import com.cg.billing.daoservices.PostpaidAccountDao;
import com.cg.billing.daoservices.PostpaidAccountDaoImpl;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

public class BillingServicesImpl implements BillingServices {
  CustomerDao customerDao=new CustomerdaoImpl();
  PlanDao planDao=new PlanDaoImpl();
  BillDao billDao=new BillDaoImpl();
  PostpaidAccountDao postpaidAccountDao=new PostpaidAccountDaoImpl();
	@Override
	public Plan getPlanAllDetails(int planId)throws PlanDetailsNotFoundException {
		if(planDao.findOne(planId)==null)
			throw new PlanDetailsNotFoundException("Plan not found for id="+planId);
		return planDao.findOne(planId);
	}

	@Override
	public int acceptCustomerDetails(String firstName, String lastName, String emailID, String dateOfBirth,
			String billingAddressCity, String billingAddressState, int billingAddressPinCode, String homeAddressCity,
			String homeAddressState, int homeAddressPinCode) {
		Customer customer=new Customer(firstName, lastName, emailID, dateOfBirth, new Address(homeAddressCity, homeAddressState, billingAddressCity, billingAddressState, homeAddressPinCode, billingAddressPinCode));
		customer=customerDao.save(customer);
		return customer.getCustomerID();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
			PostpaidAccount postpaidAccount=new PostpaidAccount(planDao.findOne(planID), customerDao.findOne(customerID));
			postpaidAccountDao.save(postpaidAccount);
		return postpaidAccount.getMobileNo();
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, PlanDetailsNotFoundException {
			Plan plan=getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			PostpaidAccount postpaidAccount= getPostPaidAccountDetails(customerID, mobileNo);
			 float totalBillAmount=0;
			 float localSMSAmount=0; 
			 float stdSMSAmount=0;
			 float localCallAmount=0;
			 float stdCallAmount=0;
			 float  internetDataUsageAmount=0; 
			 float stateGST=0 ;
			 float centralGST=0;
			if(noOfLocalCalls>plan.getFreeLocalCalls()) {
				localCallAmount=(noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate();
			}
			if(noOfStdCalls>plan.getFreeStdCalls()) {
				stdCallAmount=(noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate();
			}
			if(noOfLocalSMS>plan.getFreeLocalSMS()) {
				localSMSAmount=(noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate();
			}
			if(noOfStdSMS>plan.getFreeStdSMS()) {
				stdSMSAmount=(noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate();
			}
			if(internetDataUsageUnits>plan.getFreeInternetDataUsageUnits()) {
				internetDataUsageAmount= (internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate();
			}
			float sumOfBill=localCallAmount+stdCallAmount+localSMSAmount+stdSMSAmount+internetDataUsageAmount;
			 stateGST=(float) 0.3*sumOfBill;
			 centralGST=(float) 0.1*sumOfBill;
			 totalBillAmount=sumOfBill+stateGST+centralGST;
			Bill bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth,totalBillAmount,localSMSAmount,stdSMSAmount,localCallAmount,stdCallAmount,internetDataUsageAmount, stateGST, centralGST,postpaidAccount);
			billDao.save(bill);
			return bill.getTotalBillAmount();
	}

	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		if(customerDao.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("Customer id not found="+customerID);
		return customerDao.findOne(customerID);
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDao.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		if(customerDao.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("Customer details not found="+customerID);
		PostpaidAccount postpaidAccount=postpaidAccountDao.findOne(mobileNo);
		if(postpaidAccount==null)
			throw new PostpaidAccountNotFoundException("Account number not valid="+mobileNo);
		return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		if(customerDao.findOne(customerID)==null)
			throw new CustomerDetailsNotFoundException("Customer details not found="+customerID);
		return postpaidAccountDao.findAll(customerID);
	}
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
	      Bill bill=null;
		 ArrayList<Integer>billSetKey=new ArrayList<Integer>(getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().keySet());
		for(int i=0;i<billSetKey.size();i++) {
			if(getPostPaidAccountDetails(customerID, mobileNo).getBills().get(billSetKey.get(i)).getBillMonth().equalsIgnoreCase(billMonth))
				bill=billDao.findOne(billSetKey.get(i));
		}
		if(bill==null)
			throw new InvalidBillMonthException("Enter valid Bill Month");
		else return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		return new ArrayList<Bill>(getPostPaidAccountDetails(customerID, mobileNo).getBills().values());
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=new PostpaidAccount(mobileNo, getPlanAllDetails(planID), getCustomerDetails(customerID));
		postpaidAccountDao.update(postpaidAccount);	
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)      
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		return postpaidAccountDao.remove(getPostPaidAccountDetails(customerID, mobileNo),mobileNo);
	}

	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		return customerDao.remove(getCustomerDetails(customerID));
	}
	
	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		getCustomerDetails(customerID);
		PostpaidAccount postpaidAccount=postpaidAccountDao.findOne(mobileNo);
		return postpaidAccount.getPlan();
	}

	@Override
	public List<Plan> getAllPlanDetails() {
		return planDao.findAll();
	}
}