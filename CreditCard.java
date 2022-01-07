package CreditCardApp;

import java.time.LocalDate;
import java.util.ArrayList;

//NEED TO RETURN DEEP COPIES
public class CreditCard {
	private static int creditCardID = 0;//static global counter to generate it
	private LocalDate issueDate;
	//private issueCompany;//need the dataType
	private CreditCardType creditCardType;
	private CreditCardStatus status;
	private double creditCardLimit;
	private double currentBalance;
	private double availCredit;
	private ArrayList <Transaction> transactions;
	
	public CreditCard(LocalDate issueDate, CreditCardType creditCardType, CreditCardStatus status
			, double creditCardLimit) {//constructor
		creditCardID = creditCardID++;
		this.issueDate = issueDate;
		this.creditCardType = creditCardType;
		this.status = status;
		this.creditCardLimit = creditCardLimit;
		currentBalance = 0; //starts off at 0
		availCredit = this.creditCardLimit;
		transactions = new ArrayList <>();
			
	}
	
	public void addPurchase(Purchase p) {
		transactions.add(p);
		availCredit -= p.getTransactionAmount();
		currentBalance += p.getTransactionAmount();
		
	}
	public void addPayment(double pay) {
		currentBalance -= pay;
		availCredit += pay;
		
	}
	public void addFee(double f) {
		currentBalance += f ;
		availCredit -=f;
		
	}
	public double getCurrentBalance() {
		return this.currentBalance;
		
	}
	public double getAvailCredit() {
		return this.availCredit;
		
	}
	public Purchase getLargestPurchase() {
		double largest = -1;
		int largestIndex = -1;
		//looping through the transactions to find the largest
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Purchase && largest < transactions.get(i).getTransactionAmount()) {
				largest = transactions.get(i).getTransactionAmount();
				largestIndex = i;
			}
		}//end of for loop
		Purchase returningPurchase = null;
		if (largestIndex != -1)//if the arrayList is empty
			returningPurchase = (Purchase) transactions.get(largestIndex);
		
		return returningPurchase;
	
	}
	public double getTotalFees() {
		double total = 0;
		
		//looping through the transactions to find the largest
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Fee )//needs to be a Fee dataType
				total += transactions.get(i).getTransactionAmount();
		}
		return total;
	}
	public Purchase getMostRecentPurchase() {
		
		LocalDate mostRecent = null; //might not compare itself to null, we might need to set this
		int mostRecentIndex = -1;
		
		//looping through the transactions to find the most recent
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Purchase && mostRecent.isAfter(transactions.get(i).getTransactionDate())) {
				mostRecent = transactions.get(i).getTransactionDate();
				mostRecentIndex = i;
			}
		}//end of for loop
		Purchase returningPurchase = null;
		if (mostRecentIndex != -1)//if the arrayList is empty
			returningPurchase = (Purchase) transactions.get(mostRecentIndex);
		
		return returningPurchase;
		
		
	}
	public Payment getMostRecentPayment() {
		
		LocalDate mostRecent = null; //might not compare itself to null, we might need to set this
		int mostRecentIndex = -1;
		
		//looping through the payments to find the most recent
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Payment && mostRecent.isAfter(transactions.get(i).getTransactionDate())) {
				mostRecent = transactions.get(i).getTransactionDate();
				mostRecentIndex = i;
			}
		}//end of for loop
		Payment returningPayment = null;
		if (mostRecentIndex != -1)//if the arrayList is empty
			returningPayment = (Payment) transactions.get(mostRecentIndex);
		
		return returningPayment;
		
		
	}
	public CreditCardStatus getStatus() {
		return this.status;
	}
	public int getID() {
		return CreditCard.creditCardID;
	}
}
