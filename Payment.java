package CreditCardApp;

import java.time.LocalDate;

public class Payment extends Transaction{

	private PaymentType paymentType;
	private BankAccount account;
	
	public Payment(LocalDate tDate, TransactionType tType, PaymentType paymentType, BankAccount account, double paymentAmount) {
		super(tDate, tType, paymentAmount);
		this.paymentType = paymentType;
		this.account = account;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	

}
