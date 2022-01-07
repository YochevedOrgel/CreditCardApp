package CreditCardApp;

import java.time.LocalDate;

public class Transaction {
	private long transactionID; 
	private long lastTransactionID;//starts only from 1, 0 is like null over here
	private LocalDate transactionDate;
	private TransactionType transactionType;
	private double transactionAmount;
	private static int counter = 0;//global cntr - not sure if its correct
	
	public Transaction(LocalDate tDate, TransactionType tType, double transactionAmount){
		lastTransactionID = counter;
		counter++;
		transactionID = counter;
		transactionDate = tDate;
		transactionType = tType;
		this.transactionAmount = transactionAmount;
	}
	
	//getters
	public double getTransactionAmount() {
		return transactionAmount;
	}

	public long getTransactionID() {
		return transactionID;
	}
	public long getLastTransactionID() {
		return lastTransactionID;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}


}
