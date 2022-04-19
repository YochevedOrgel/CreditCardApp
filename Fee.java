package CreditCardApp;

import java.time.LocalDate;

public class Fee extends Transaction{
	
	private FeeType feeType;
	
	public Fee(LocalDate tDate, TransactionType tType, FeeType feeType, double amount) {
		super(tDate, tType, amount);
		this.feeType = feeType;
	}

	public FeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
}
