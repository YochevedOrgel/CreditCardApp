package CreditCardApp;

public class BankAccount {

	private String bankName;
	private String accountID;

	public BankAccount(String bankName, String accountID) {
		this.bankName = bankName;
		this.accountID = accountID;
	}
	//no setters because then it would be a different account
	public String getBankName() {
		return bankName;
	}

	public String getAccountID() {
		return accountID;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
			str.append("\nBank Name: " + this.bankName
					+ "\nAccount ID: " + accountID);
		return str.toString();
	}


}
