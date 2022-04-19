package CreditCardApp;

import java.time.LocalDate;

public class Purchase extends Transaction {

	private PurchaseType purchaseType;
	private Vendor vendor;

	public Purchase(LocalDate tDate, TransactionType tType, PurchaseType purchaseType, Vendor vendor
			, double transactionAmount) {
		super(tDate, tType, transactionAmount);
		this.purchaseType = purchaseType;
		this.vendor = vendor;

	}

	public PurchaseType getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(PurchaseType purchaseType) {
		this.purchaseType = purchaseType;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

}
