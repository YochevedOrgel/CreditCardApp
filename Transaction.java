package CreditCardApp;

import java.time.LocalDate;

public class Transaction {
	private long transactionID; 
	private long lastTransactionID;//starts only from 1
	private LocalDate transactionDate;
	private TransactionType transactionType;
	private double transactionAmount;
	static int counter = 1;
	
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

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
			str.append("\nTransaction Type: " + transactionType
					+ "\nTransaction amount: " + transactionAmount);
		return str.toString();
	}

}
