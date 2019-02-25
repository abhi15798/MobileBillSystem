package com.cg.billing.client;
import java.util.Scanner;

import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.BillingServices;
import com.cg.billing.services.BillingServicesImpl;
public class MainClass {
	public static void main(String[] args) {
		BillingServices services=new BillingServicesImpl();
		Scanner scan=new Scanner(System.in);
		int customerID,planID;
		long mobileNo;
		char ch;
		String billMonth;
		
		do{
			System.out.println("1)Register Customer \n2)Open Postpaid Account \n3)Generate Monthly Mobile Bill"
					+ "\n4)Customer Info \n5)All Customer Info \n6)Account Details \n7)Customer All Account Details"
					+ "\n8)Customer Bill (Monthly) \n9)Customer All Bill \n10)Change Plan \n11)Close Customer Account"
					+ "\n12)Remove Customer \n)13Customer Account Plan Details \n14)All Plan");
			System.out.println("Enter your Choice");
			int choice=scan.nextInt();
			switch(choice) {
		case 1: System.out.println("Enter First Name :");
		  		String firstName=scan.next();
		  		System.out.println("Enter Last Name :");
		  		String lastName=scan.next();
		  		System.out.println("Enter EmailID :");
		  		String emailID=scan.next();
		  		System.out.println("Enter DOB :");
		  		String dateOfBirth=scan.next();
		  		System.out.println("Enter Billing Address City");
		  		String billingAddressCity=scan.next();
		  		System.out.println("Enter Billing Address State");
		  		String billingAddressState=scan.next();
		  		System.out.println("Enter Billing Address PinCode");
		  		int billingAddressPinCode=scan.nextInt();
		  		System.out.println("Enter Home Address City");
		  		String homeAddressCity=scan.next();
		  		System.out.println("Enter Home Address State");
		  		String homeAddressState=scan.next();
		  		System.out.println("Enter Home Address PinCode");
		  		int homeAddressPinCode=scan.nextInt();
		  		customerID=services.acceptCustomerDetails(firstName, lastName, emailID, dateOfBirth, billingAddressCity, billingAddressState, billingAddressPinCode, homeAddressCity, homeAddressState, homeAddressPinCode);
		  		System.out.println("CustomerId  :"+customerID);
		  		break; 
		case 2: System.out.println("Enter Customer ID:");
				customerID = scan.nextInt();
				System.out.println("Select Plan:");
				System.out.println(services.getAllPlanDetails());
				planID=scan.nextInt();
				try {
						System.out.println("Your Mobile Number :"+services.openPostpaidMobileAccount(customerID, planID));
				} catch (PlanDetailsNotFoundException | CustomerDetailsNotFoundException e3) {
					e3.printStackTrace();
				}
				break;	
		case 3: System.out.println("Enter Customer ID :");
				customerID = scan.nextInt();
				System.out.println("Enter MobileNo :");
				mobileNo=scan.nextInt();
				System.out.println("Enter Month :");
				billMonth= scan.next();
				System.out.println("Enter noOfLocalSMS :");
				int noOfLocalSMS=scan.nextInt();
				System.out.println("Enter noOfStdSMS :");
				int noOfStdSMS=scan.nextInt();
				System.out.println("Enter noOfLocalCalls :");
				int noOfLocalCalls=scan.nextInt();
				System.out.println("Enter noOfStdCalls:");
				int noOfStdCalls=scan.nextInt();
				System.out.println("Enter internetDataUsageUnits :");
				int internetDataUsageUnits=scan.nextInt();
				try {
						System.out.println("Total Monthly Bill for Month :"+billMonth+",Customer ID:"+services.generateMonthlyMobileBill(customerID, mobileNo, billMonth, noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits));
				} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
					| PlanDetailsNotFoundException e) {
				  System.out.println(e.getMessage());
				}
				break;		
		case 4: System.out.println("Enter Customer ID:");
		    	customerID = scan.nextInt();
		    	try {
		    			System.out.println(services.getCustomerDetails(customerID));
		    	} catch (CustomerDetailsNotFoundException e1) {
		    		System.out.println(e1.getMessage());
		    	}
		    	break;
		case 5: System.out.println(services.getAllCustomerDetails());
		        break;
		case 6: System.out.println("Enter Customer ID:");
				customerID = scan.nextInt();
				System.out.println("Enter MobileNo:");
				mobileNo=scan.nextInt();
				try {
					System.out.println(services.getPostPaidAccountDetails(customerID, mobileNo));
				} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException e2) {
				System.out.println(e2.getMessage());
				}	
				break;
		case 7: System.out.println("Enter customerId");	
				customerID=scan.nextInt();
			    try {
			    	services.getCustomerAllPostpaidAccountsDetails(customerID);
			    } catch (CustomerDetailsNotFoundException e) {
			    	System.out.println(e.getMessage());
			    }
			    break;
		case 8: System.out.println("Enter Customer ID:");
				customerID = scan.nextInt();
				System.out.println("Enter MobileNo:");
				mobileNo=scan.nextInt();
				System.out.println("Enter Month :");
				billMonth= scan.next();
				try {
					System.out.println(services.getMobileBillDetails(customerID, mobileNo, billMonth));
				} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException | InvalidBillMonthException
					| BillDetailsNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;	   
		case 9: System.out.println("Enter Customer ID:");
		  		customerID = scan.nextInt();
		  		System.out.println("Enter MobileNo:");
		  		mobileNo=scan.nextInt();
		  		try {
		  			System.out.println(services.getCustomerPostPaidAccountAllBillDetails(customerID, mobileNo));
		  		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException e) {
		  			System.out.println(e.getMessage());
		  		}
		  		break;
		case 10:System.out.println("Enter Customer ID:");
		 		customerID = scan.nextInt();
		 		System.out.println("Enter MobileNo:");
		 		mobileNo=scan.nextInt();
		 		System.out.println(services.getAllPlanDetails());
		 		System.out.println("Enter planID:");
		 		planID=scan.nextInt();
		 		try {
		 			services.changePlan(customerID, mobileNo, planID);
		 			System.out.println("Changes Saved Succesfully for CustomerID :"+customerID+"and MobileNo :"+mobileNo+"New PlanId:"+planID);
		 		} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException e) {
		 			System.out.println(e.getMessage());
		 		} catch (PlanDetailsNotFoundException e) {
					System.out.println(e.getMessage());
				}
		 		break;
		case 11:System.out.println("Enter Customer ID:");
				customerID = scan.nextInt();
				System.out.println("Enter MobileNo:");
				mobileNo=scan.nextInt();
				try {
					services.closeCustomerPostPaidAccount(customerID, mobileNo);
					System.out.println("Postpaid Account Succesfully Deleted!!");
				} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
		case 12:System.out.println("Enter Customer ID:");
				customerID = scan.nextInt();
				try {
					services.removeCustomerDetails(customerID);
					System.out.println("Customer Details Succesfully Removed!");
				} catch (CustomerDetailsNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;	
		case 13:System.out.println("Enter CustomerId");
		 		customerID=scan.nextInt();
		 		System.out.println("Enter MobileNo");
		 		mobileNo=scan.nextLong();
			 try {
				System.out.println(services.getCustomerPostPaidAccountPlanDetails(customerID,  mobileNo));
			} catch (CustomerDetailsNotFoundException | PostpaidAccountNotFoundException
					| PlanDetailsNotFoundException e) {
				System.out.println(e.getMessage());
			}
			break;			
		default: System.out.println("Wrong input");
		 		 break;
		}
		System.out.println("Want to continue");
		ch=scan.next().charAt(0);
		}while(ch=='y'||ch=='Y');
	}   
}
