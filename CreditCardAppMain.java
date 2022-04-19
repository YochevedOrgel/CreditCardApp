package CreditCardApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreditCardAppMain {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		CreditCards cards = new CreditCards();
		try {
			execute(input, cards);
		} catch (InputMismatchException e) {
			System.out.println("You have entered invalid input...Exiting");
			System.exit(1);
		}

	}

	public static void execute(Scanner input, CreditCards cards) {
		int option;

		while (true) {// infinite while loop - to exit you need to press option 0

			displayMenu();
			option = getMenuOption(input);
			// goes to the method the user chose
			switch (option) {
			case 0:
				exitingMethod();
				break;
			case 1:
				addNewCard(input, cards);
				break;
			case 2:
				removeCard(input, cards);
				break;
			case 3:
				totalBalance(cards);
				break;
			case 4:
				totalAvailableCredit(cards);
				break;
			case 5:
				largestPurchase(cards);
				break;
			case 6:
				displayMostRecentPayment(cards);
				break;
			case 7:
				displayTotalSpentOnCertainCategory(input, cards);
				break;
			case 8:
				mostMoneySpent(cards);
				break;
			case 9:
				manageSpecificCard(input, cards);
				break;

			}
		}

	}

	public static void displayMenu() {
		System.out.println("Choose an Option:\n" + "1. Add a new CreditCard\n" + "2. Remove a CreditCard\n"
				+ "3. Display total outstanding balances\n" + "4. Display total available credit\n"
				+ "5. Display largest purchase\n" + "6. Display most recent payment\n"
				+ "7. Display total spent on certain category of expense\n"
				+ "8. Display type of Purchase the most money was spent on\n" + "9. Manage a specific Credit Card\n"
				+ "0. Exit");

	}

	public static int getMenuOption(Scanner input) {
		int option = input.nextInt();
		while (option < 0 || option > 9) {
			System.out.println("Enter a valid choice (0-9)");
			option = input.nextInt();
		}
		input.nextLine();// clearing the buffer
		return option;
	}

	public static void exitingMethod() {
		System.out.println("GoodBye!\nExiting the program...");
		System.exit(0);// Normal Exit

	}

	public static void addNewCard(Scanner input, CreditCards cards) {

		// LocalDate
		try {
			System.out.println("Enter the credit card issue date in dd/MM/yyyy format:");
			String str = input.next();
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(str, dt);
			input.nextLine();// clearing the buffer

			// CreditCardType
			// VISA, MASTERCARD, AMEX
			System.out.println(
					"What type of credit card would you like to add?\n" + "1. Visa\n" + "2. MasterCard\n" + "3. Amex");
			int typeOfCard = input.nextInt();
			while (typeOfCard > 3 || typeOfCard < 1) {
				System.out.println("Enter a valid option!");
				typeOfCard = input.nextInt();
			}
			CreditCardType type;
			if (typeOfCard == 3)
				type = CreditCardType.AMEX;
			else if (typeOfCard == 2)
				type = CreditCardType.MASTERCARD;
			else
				type = CreditCardType.VISA;

			// CreditCardStatus
			// ACTIVE, CANCELLED, LOST, EXPIRED
			System.out.println("What is the status of the credit card would you like to add?\n" + "1. Active\n"
					+ "2. Cancelled\n" + "3. Lost\n" + "4. Expired");
			int statusNum = input.nextInt();
			while (statusNum > 4 || statusNum < 1) {
				System.out.println("Enter a valid option!");
				statusNum = input.nextInt();
			}
			CreditCardStatus status = CreditCardStatus.ACTIVE;
			if (statusNum != 1) {
				switch (statusNum) {
				case 2:
					status = CreditCardStatus.CANCELLED;
					break;
				case 3:
					status = CreditCardStatus.LOST;
					break;
				case 4:
					status = CreditCardStatus.EXPIRED;
					break;

				}
			}

			// double creditCardLimit
			System.out.println("What is/was the credit card limit on the card? ");
			double limit = input.nextDouble();
			while (limit < 0.00 || limit > 5000.00) {
				System.out.println("Invalid Limit!\nEnter 0-5000 dollars");
				limit = input.nextDouble();
			}

			CreditCard newCard = new CreditCard(date, type, status, limit);
			cards.addCard(newCard);
			System.out.println("Credit card added successfully.\n" + newCard);

		} catch (java.time.DateTimeException e) {
			System.out.println("You have entered an invalid date.\n"
					+ "Please try again and make sure it is in dd/MM/yyyy format.");
			addNewCard(input, cards); // gives the user another chance
		}

	}

	public static void removeCard(Scanner input, CreditCards cards) {
		if (cards.isEmpty()) {
			System.out.println("There are no cards to remove");
			return;
		}

		System.out.println("What is your credit card ID? ");
		int iD = input.nextInt();

		if (cards.removeCard(iD)) {
			System.out.println("Credit Card has been removed");
		} else {
			System.out.println("You have entered an invalid Credit Card ID!\nEnter a valid one!!");
			removeCard(input, cards);// calling this method again
		}

	}

	public static void totalBalance(CreditCards cards) {
		System.out.println("Total Balance: $" + cards.totalBalance());
		System.out.println();
	}

	public static void totalAvailableCredit(CreditCards cards) {
		System.out.println("Total Available Credit: $" + cards.totalAvailCredit());
		System.out.println();
	}

	public static void largestPurchase(CreditCards cards) {
		System.out.println("The largest purchase is: $" + cards.getLargestPurchase());
	}

	public static void displayMostRecentPayment(CreditCards cards) {
		String str = "01/01/0001";// earliest date
		DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(str, dt);
		if (cards.mostRecentPayment().equals(date)) {
			System.out.println("There are no payments");
			return;
		}
		System.out.println("The most recent payment was on " + cards.mostRecentPayment());

	}

	public static void displayTotalSpentOnCertainCategory(Scanner input, CreditCards cards) {

		System.out.println("What type of purchase would you like to see the amount spent (1-8)?\n" + "1. Car\n"
				+ "2. Clothing\n" + "3. Food\n" + "4. Groceries\n" + "5. Lodging\n" + "6. Restaurant\n" + "7. Travel\n"
				+ "8. Utilities");
		int pType = input.nextInt();
		while (pType > 8 || pType < 1) {
			System.out.println("Enter a valid option!");
			pType = input.nextInt();
		}

		// getting the category type
		PurchaseType purchaseType = null;
		switch (pType) {
		case 1:
			purchaseType = PurchaseType.CAR;
			break;
		case 2:
			purchaseType = PurchaseType.CLOTHING;
			break;
		case 3:
			purchaseType = PurchaseType.FOOD;
			break;
		case 4:
			purchaseType = PurchaseType.GROCERIES;
			break;
		case 5:
			purchaseType = PurchaseType.LODGING;
			break;
		case 6:
			purchaseType = PurchaseType.RESTAURANT;
			break;
		case 7:
			purchaseType = PurchaseType.TRAVEL;
			break;
		case 8:
			purchaseType = PurchaseType.UTILITIES;
			break;
		}

		System.out.println("Total amount spent: $" + cards.getTotalAmountSpentOnCatagory(purchaseType));

	}

	public static void mostMoneySpent(CreditCards cards) {

		// array of all purchase types
		PurchaseType[] array = { PurchaseType.CAR, PurchaseType.CLOTHING, PurchaseType.FOOD, PurchaseType.GROCERIES,
				PurchaseType.LODGING, PurchaseType.RESTAURANT, PurchaseType.TRAVEL, PurchaseType.UTILITIES };
		double most = 0;
		int mostIndex = -1;
		for (int i = 0; i < array.length; i++) { // looping through array to find the largest
			if (cards.getTotalAmountSpentOnCatagory(array[i]) > most) {
				most = cards.getTotalAmountSpentOnCatagory(array[i]);
				mostIndex = i;
			}
		}
		if (mostIndex == -1) {
			System.out.println("No purchases have been made!");
			return;
		}
		System.out.println("The most money was spent on " + array[mostIndex] + ".\nAmount : $" + most);
	}

	public static void manageSpecificCard(Scanner input, CreditCards cards) {
		if (cards.isEmpty()) {
			System.out.println("There are no cards to manage");
			return;
		}
		int iD = getTheSpecificCreditCard(input, cards); // getting the card ID
		CreditCard currCard = cards.getCard(iD); // getting the card
		while (true) {// infinite while loop but gave a way to exit - so not infinite
			displaySpecificCardOptions();
			int option = getCreditCardOption(input);

			switch (option) {

			case 0:
				execute(input, cards); // back to the main menu
				break;
			case 1: // Display current balance
				System.out.println("Current Balance: $" + currCard.getCurrentBalance());
				System.out.println();
				break;
			case 2: // Display credit card limit
				System.out.println("Credit Card Limit: $" + currCard.getCreditCardLimit());
				System.out.println();
				break;
			case 3: // Add a Purchase
				addPurchase(input, currCard);
				break;
			case 4: // Add a Payment
				addPayment(input, currCard);
				break;
			case 5: // Add a Fee
				addFee(input, currCard);
				break;
			case 6:// Display most recent Purchase
				System.out.println("The most recent purchase for this card is " + currCard.getMostRecentPurchase());
				break;
			case 7:// Display most recent Payment
				displaySpecificCardMostRecentPayment(currCard);
				break;

			}
		}

	}

	public static void displaySpecificCardOptions() {
		System.out.println("Choose an Option:\n" + "1. Display current balance \n" + "2. Display credit limit \n"
				+ "3. Add a Purchase\n" + "4. Add a Payment \n" + "5. Add a Fee\n"
				+ "6. Display most recent Purchase \n" + "7. Display most recent Payment\n" + "0. Exit");
	}

	public static int getCreditCardOption(Scanner input) {
		int option = input.nextInt();
		while (option < 0 || option > 7) {
			System.out.println("Enter a valid choice (0-7)");
			option = input.nextInt();
		}
		input.nextLine();// clearing the buffer
		return option;
	}

	public static int getTheSpecificCreditCard(Scanner input, CreditCards cards) {

		System.out.println("What is the credit card ID of the card you would like to manage? ");
		int iD = input.nextInt();

		while (!(cards.findCard(iD))) {
			System.out.println("You have entered an invalid Credit Card ID!\nEnter a valid one!!");
			iD = input.nextInt();
		}

		return iD;

	}

	public static void addPurchase(Scanner input, CreditCard currCard) {

		if (currCard.getAvailCredit() == 0) {
			System.out.println("Pay your bill!\nYou do not have enough credit to make a purchase!");
			return;
		}
		if (currCard.getStatus() != CreditCardStatus.ACTIVE) {
			System.out.println("Cannot make a purchase on an inactive card!");
			return;
		}

		// LocalDate
		try {
			System.out.println("What is the Purchase date in dd/MM/yyyy format:");
			String str = input.next();
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(str, dt);
			input.nextLine();// clearing the buffer

			// TransactionType
			TransactionType type = TransactionType.PURCHASE;

			// Purchase Type
			// CAR, CLOTHING, FOOD, GROCERIES, LODGING, RESTAURANT, TRAVEL, UTILITIES

			System.out.println("What type of purchase was it for?\n" + "1. Car\n" + "2. Clothing\n" + "3. Food\n"
					+ "4. Groceries\n" + "5. Lodging\n" + "6. Restaurant\n" + "7. Travel\n" + "8. Utilities");
			int pType = input.nextInt();
			while (pType > 8 || pType < 1) {
				System.out.println("Enter a valid option!");
				pType = input.nextInt();
			}
			PurchaseType purchaseType = PurchaseType.CAR; // case 1
			switch (pType) {
			case 2:
				purchaseType = PurchaseType.CLOTHING;
				break;
			case 3:
				purchaseType = PurchaseType.FOOD;
				break;
			case 4:
				purchaseType = PurchaseType.GROCERIES;
				break;
			case 5:
				purchaseType = PurchaseType.LODGING;
				break;
			case 6:
				purchaseType = PurchaseType.RESTAURANT;
				break;
			case 7:
				purchaseType = PurchaseType.TRAVEL;
				break;
			case 8:
				purchaseType = PurchaseType.UTILITIES;
				break;
			}

			// Vendor
			System.out.println("What is the vendors name that the purchase was from? ");
			String name = input.nextLine();
			input.nextLine();// clearing the buffer

			System.out.println("What is the vendors street address that the purchase was from? ");
			String street = input.nextLine();

			System.out.println("Which city is the vendor located in? ");
			String city = input.nextLine();

			System.out.println("What is the vendors zip code? ");
			String zip = input.nextLine();

			Address address = new Address(street, city, zip);

			Vendor vendor = new Vendor(name, address);

			System.out.println("Enter the purchase amount: ");
			double transactionAmount = input.nextDouble();
			while (transactionAmount > currCard.getAvailCredit() || transactionAmount <= 0) {
				if (transactionAmount > currCard.getAvailCredit()) { //making sure they don't go over their limit
					System.out.println(
							"Purchase declined.\nYou do not have enough credit for this!\nEnter another amount!");
				} else {
					System.out.println("Amount must be greater than 0\nEnter another amount!");
				}
				transactionAmount = input.nextDouble();
			}

			Purchase p = new Purchase(date, type, purchaseType, vendor, transactionAmount);
			currCard.addPurchase(p);

		} catch (java.time.DateTimeException e) {
			System.out.println("You have entered an invalid date.\n"
					+ "Please try again and make sure it is in dd/MM/yyyy format.");
			addPurchase(input, currCard);
		}

	}

	public static void addPayment(Scanner input, CreditCard currCard) {
		if (currCard.getCurrentBalance() == 0) {
			System.out.println("There is no balance on this card.");
			return;
		}

		try {
			// LocalDate
			System.out.println("What is the Payment date in dd/MM/yyyy format:");
			String str = input.next();
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(str, dt);
			input.nextLine();// clearing the buffer

			// TransactionType
			TransactionType type = TransactionType.PAYMENT;

			// PaymentType
			// CHECK, ONLINE

			System.out.println("Choose a payment method?\n" + "1. Check\n" + "2. Online");
			int pType = input.nextInt();
			while (pType > 2 || pType < 1) {
				System.out.println("Enter a valid option!");
				pType = input.nextInt();
			}
			PaymentType paymentType;
			if (pType == 1)
				paymentType = PaymentType.CHECK;
			else
				paymentType = PaymentType.ONLINE;

			input.nextLine(); // clearing the buffer
			// BankAccount
			System.out.println("What is the name of the bank?");
			String bankName = input.nextLine();

			System.out.println("What is the account ID?");
			String accountID = input.nextLine();

			BankAccount account = new BankAccount(bankName, accountID);

			// paymentAmount
			System.out.println("How much are you paying?");
			double paymentAmount = input.nextDouble();
			while (paymentAmount <= 0 || paymentAmount > currCard.getCurrentBalance()) {
				System.out.println("Invalid Payment Amount!\nPlease try again");
				paymentAmount = input.nextDouble();
			}

			Payment p = new Payment(date, type, paymentType, account, paymentAmount);
			currCard.addPayment(p);

		} catch (java.time.DateTimeException e) {
			System.out.println("You have entered an invalid date.\n"
					+ "Please try again and make sure it is in dd/MM/yyyy format.");
			addPayment(input, currCard);
		}

	}

	public static void addFee(Scanner input, CreditCard currCard) {
		
		try {
			// LocalDate
			System.out.println("What is the Fee date in dd/MM/yyyy format:");
			String str = input.next();
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(str, dt);
			input.nextLine();// clearing the buffer

			// TransactionType
			TransactionType type = TransactionType.FEE;

			// FeeType
			// LATEPAYMENT, INTEREST
			System.out.println("Choose a fee type:\n" + "1. LatePayment\n" + "2. Interest");
			int feeType = input.nextInt();
			while (feeType > 2 || feeType < 1) {
				System.out.println("Enter a valid option!");
				feeType = input.nextInt();
			}
			FeeType typeOfFee;
			if (feeType == 1)
				typeOfFee = FeeType.LATEPAYMENT;
			else
				typeOfFee = FeeType.INTEREST;

			// fee amount
			System.out.println("How much is the fee?");
			double feeAmount = input.nextDouble();
			while (feeAmount <= 0) {
				System.out.println("Invalid fee Amount!\nPlease try again");
				feeAmount = input.nextDouble();
			}

			Fee f = new Fee(date, type, typeOfFee, feeAmount);
			currCard.addFee(f);

		} catch (java.time.DateTimeException e) {
			System.out.println("You have entered an invalid date.\n"
					+ "Please try again and make sure it is in dd/MM/yyyy format.");
			addFee(input, currCard);
		}

	}

	public static void displaySpecificCardMostRecentPayment(CreditCard currCard) {
		if (currCard.getMostRecentPayment() == null) {
			System.out.println("There are no payments at this time.");
			return;
		}
		System.out.println("The most recent payment for this card is on " + currCard.mostRecentPayment());
		System.out.println(currCard.getMostRecentPayment().toString());

	}

}
