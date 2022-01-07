package CreditCardApp;

import java.time.LocalDate;
import java.util.ArrayList;

public class CreditCards {
	private ArrayList<CreditCard> cards;

	public ArrayList <CreditCard> activeCards() {
		ArrayList<CreditCard> active = new ArrayList<>();
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getStatus() == CreditCardStatus.ACTIVE) {// null safe
				active.add(cards.get(i));
			}
		}
		return active;

	}

	public double totalBalance() {
		double balance = cards.get(0).getCurrentBalance();
		for (int i = 1; i < cards.size(); i++) {
			balance += cards.get(i).getCurrentBalance();
			
		}
		return balance;

	}

	public double totalAvailCredit() {
		double availCredit = cards.get(0).getAvailCredit();
		for (int i = 1; i < cards.size(); i++) {
			availCredit += cards.get(i).getAvailCredit();
		}
		return availCredit;


	}

	public void addCard(LocalDate issueDate, CreditCardType creditCardType, CreditCardStatus status
			, double creditCardLimit) {
		CreditCard newCard = new CreditCard(issueDate, creditCardType, status, creditCardLimit);
		cards.add(newCard);
		
	}

	public void removeCard(int id) {

		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.remove(i);
				return;
			}
		}
	}

	public CreditCard findCard(int id) {//?? RETURNS THE CARD - SWTICHED IT FROM VOID
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

	public void addFee(int id, double f) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.get(i).addFee(f);;
			}
		}

	}

	public void addPayment(int id, double pay) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getID() == id) {
				cards.get(i).addPayment(pay);
			}
		}

	}

}
