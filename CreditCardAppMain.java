package CreditCardApp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreditCardAppMain {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int option;

		do {

			displayMenu();
			option = getMenuOption(input);
			switch (option) {
			case 0:
				exitingMethod();
				break;
			case 1:
				addNewCard(input);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;

			}

		} while (option != 0);

		/**
		 * 1. Add a new CreditCard 
		 * 2. Remove a CreditCard 
		 * 3. Display total outstanding balances 
		 * 4. Display total available credit 
		 * 5. Display largest purchase 
		 * 6. Display most recent payment 
		 * 7. Display total spent on certain category of expense 
		 * 8. For which type of Purchase was the most money spent 
		 * 9. Manage a specific Credit Card 
		 * a. Display current balance 
		 * b. Display current credit limit 
		 * c. Add a Purchase 
		 * d. Add a Payment 
		 * e. Add a Fee 
		 * f. Display most recent Purchase 
		 * g. Display most recent Payment
		 * 
		 */

	}

	public static void addNewCard(Scanner input) {
		//get LocalDate issueDate, CreditCardType creditCardType, CreditCardStatus status, double creditCardLimit
		
		// LocalDate
		try {
			System.out.println("Enter a date dd/MM/yyyy format:");
			String str = input.next();
			DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(str, dt);
			input.nextLine();// clearing the buffer
		} catch (java.time.DateTimeException e) {
			System.out.println("You have entered an invalid date.\nPlease try again.");
			addNewCard(input);
		}
		
		//CreditCardType
		//VISA, MASTERCARD, AMEX
		System.out.println("What type of credit card would you like to add?\n"
				+ "1. Visa\n"
				+ "2. MasterCard\n"
				+ "3. Amex");
		int typeOfCard = input.nextInt();
		while(typeOfCard > 3 || typeOfCard < 1) {
			System.out.println("Enter a valid option!");
			typeOfCard = input.nextInt();
		}
		CreditCardType type;
		if(typeOfCard == 3)
			type = CreditCardType.AMEX;
		else if(typeOfCard == 2)
			type = CreditCardType.MASTERCARD;
		else
			type = CreditCardType.VISA;
		
		
		//CreditCardStatus
		//ACTIVE, CANCELLED, LOST, EXPIRED

	}

	public static void exitingMethod() {
		System.out.println("GoodBye!\nExiting the program...");
		System.exit(0);// Normal Exit

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

	public static void displayMenu() {
		System.out.println("Choose an Option:\n" + "1. Add a new CreditCard\n" + "2. Remove a CreditCard\n"
				+ "3. Display total outstanding balances\n" + "4. Display total available credit\n"
				+ "5. Display largest purchase\n" + "6. Display most recent payment\n"
				+ "7. Display total spent on certain category of expense\n"
				+ "8. Display type of Purchase the most money was spent on\n" + "9. Manage a specific Credit Card\n"
				+ "0. Exit");

	}

}
