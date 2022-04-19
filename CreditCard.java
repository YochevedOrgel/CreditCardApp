package CreditCardApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreditCard {
	static int creditCardIDs = 1;//static global counter to generate it
	private int thisCreditCardID;
	private LocalDate issueDate;
	private CreditCardType creditCardType;
	private CreditCardStatus status;
	private double creditCardLimit;
	private double currentBalance;
	private double availCredit;
	private ArrayList <Transaction> transactions;
	
	public CreditCard(LocalDate issueDate, CreditCardType creditCardType, CreditCardStatus status
			, double creditCardLimit) {//constructor
		thisCreditCardID = creditCardIDs++;
		this.issueDate = issueDate;
		this.creditCardType = creditCardType;
		this.status = status;
		this.creditCardLimit = creditCardLimit;
		currentBalance = 0; //starts off at 0
		availCredit = this.creditCardLimit;
		transactions = new ArrayList <>();
			
	}
	
	public LocalDate mostRecentPayment() {
		String str = "01/01/0001"; //earliest date possible
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(str, dt);
		LocalDate recent = date;
		for(int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Payment && transactions.get(i).getTransactionDate().compareTo(recent) > 0) {
				recent = transactions.get(i).getTransactionDate();
			}
		}
		
		return recent;
	}
	
	public double getCreditCardLimit() {
		return creditCardLimit;
	}

	public void addPurchase(Purchase p) {
		transactions.add(p);
		availCredit -= p.getTransactionAmount();
		currentBalance += p.getTransactionAmount();
		
	}
	public void addPayment(Payment pay) {
		transactions.add(pay);
		currentBalance -= pay.getTransactionAmount();
		availCredit += pay.getTransactionAmount();
		
	}
	public void addFee(Fee f) {
		transactions.add(f);
		currentBalance += f.getTransactionAmount() ;
		availCredit -=f.getTransactionAmount();
		
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
		
		String str = "01/01/0001";
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(str, dt); 
		LocalDate mostRecent = date;
		int mostRecentIndex = -1;
		
		//looping through the transactions to find the most recent
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Purchase && mostRecent.compareTo(transactions.get(i).getTransactionDate()) < 0) {
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
		
		String str = "01/01/0001";
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(str, dt); 
		LocalDate mostRecent = date;
		int mostRecentIndex = -1;
		
		
		//looping through the payments to find the most recent
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Payment && mostRecent.compareTo(transactions.get(i).getTransactionDate())<0) {
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
		return this.thisCreditCardID;
	}
	
	public double getTotalAmountSpentOnCatagory(PurchaseType type) {
		double amount = 0.0;
		for (int i = 0; i < transactions.size(); i++) {
			if(transactions.get(i) instanceof Purchase) {
				Purchase p = (Purchase) transactions.get(i); //typecasting
				if(p.getPurchaseType().equals(type)) {
					amount += p.getTransactionAmount();
				}
			}
		}//end of for loop
		
		return amount;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Crdeit Card ID: " + this.thisCreditCardID
				+ "\nType of Card: " + this.creditCardType
				+ "\nDate issued: " + this.issueDate
				+ "\nCredit card Status: " + this.status
				+ "\nCredit Card Limit: $" +  this.creditCardLimit
				+ "\nCurrent Balance: $" + this.currentBalance
				+ "\nCredit Available: $" + this.availCredit + "\n");//didn't add transactions
		
		return str.toString();
	}
}
