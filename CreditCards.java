package CreditCardApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CreditCards {
	private ArrayList<CreditCard> cards;

	public CreditCards() {
		cards = new ArrayList<>();
	}
	
	public boolean isEmpty() {
		return cards.size() == 0;
	}

	public ArrayList <CreditCard> activeCards() {
		ArrayList<CreditCard> active = new ArrayList<>();
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getStatus() == CreditCardStatus.ACTIVE) {
				active.add(cards.get(i));
			}
		}
		return active;

	}

	public double totalBalance() {	
		double balance = 0;
		for (int i = 0; i < cards.size(); i++) {
			balance += cards.get(i).getCurrentBalance();
			
		}
		return balance;

	}

	public double totalAvailCredit() {
		double availCredit = 0;
		for (int i = 0; i < cards.size(); i++) {
			availCredit += cards.get(i).getAvailCredit();
		}
		return availCredit;


	}

	public void addCard(CreditCard newCard) {
		cards.add(newCard);
		
	}

	public boolean removeCard(int id) {

		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean findCard(int id) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				return true;
			}
		}
		return false;
	}
	
	public CreditCard getCard(int id) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				return cards.get(i);
			}
		}
		return null;
	}

	public void addPurchase(int id, Purchase p) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.get(i).addPurchase(p);
			}
		}

	}

	public void addFee(int id, Fee f) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.get(i).addFee(f);
			}
		}

	}

	public void addPayment(int id, Payment pay) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.get(i).addPayment(pay);
			}
		}
	}
	
	public LocalDate mostRecentPayment() {
		String str = "01/01/0001";
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(str, dt);
		LocalDate recent = date;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).mostRecentPayment().isAfter(recent)) {
				recent = cards.get(i).mostRecentPayment();
			}
	}
		return recent;
	}
	
	public double getLargestPurchase() {
		double largestPurchase = 0;
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getLargestPurchase() != null) {
			if(cards.get(i).getLargestPurchase().getTransactionAmount() > largestPurchase) {
				largestPurchase = cards.get(i).getLargestPurchase().getTransactionAmount();
			}
			}
		}
		return largestPurchase;
	}
	
	public double getTotalAmountSpentOnCatagory(PurchaseType type) {
		double amount = 0;
		for (int i = 0; i < cards.size(); i++) {
			amount += cards.get(i).getTotalAmountSpentOnCatagory(type);

		}
		return amount;
	}


}
