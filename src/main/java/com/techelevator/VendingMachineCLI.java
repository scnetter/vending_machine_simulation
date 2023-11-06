package com.techelevator;

import com.techelevator.VendingMachineItems.VendingItem;
import com.techelevator.VendingMachineItems.VendingMachine;
import com.techelevator.view.VendingMenu;

import java.util.Map;
import java.util.Scanner;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private VendingMenu menu;

	public VendingMachineCLI(VendingMenu menu) {
		this.menu = menu;
	}

	public void run() {
		VendingMachine vendingMachine = new VendingMachine();
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					vendingMachine.displayCurrentInventory();
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					purchaseMenu(vendingMachine);
					break;
				case MAIN_MENU_OPTION_EXIT:
					running = false;
			}
		}
	}

	public void purchaseMenu(VendingMachine vendingMachine) {
		System.out.printf("\nCurrent Money Provided: $%.2f \n", vendingMachine.getBalance());
		String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
		Boolean running = true;
		while (running)
			switch (purchaseChoice) {
				case PURCHASE_MENU_OPTION_FEED_MONEY:
					System.out.print("Enter amount to add to balance: ");
					Scanner moneyIn = new Scanner(System.in);
					double userInput = moneyIn.nextDouble();
					moneyIn.nextLine();
					vendingMachine.increaseBalance(userInput);
					System.out.printf("\nCurrent Money Provided: $%.2f \n", vendingMachine.getBalance());
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					break;
				case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
					purchaseItem(vendingMachine);
					purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					break;
				case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
					System.out.printf("Thank you for your purchase!\n Your change is: \n");
					for(String denominations : vendingMachine.returnChange().values()){
						System.out.printf("%-4s ", denominations);
					}
					running = false;
					break;
			}
	}

	public void purchaseItem(VendingMachine vendingMachine){
		vendingMachine.displayCurrentInventory();
		System.out.print("Please select an item to purchase: ");
		Scanner userSelection = new Scanner(System.in);
		String itemSelected = userSelection.nextLine();

		if (vendingMachine.getInventory().containsKey(itemSelected)) {
			VendingItem item = vendingMachine.getInventory().get(itemSelected);
			if (item.getRemaining() == 0) {
				System.out.println(itemSelected + " is sold out.");
			} else if (item.getPrice() <= vendingMachine.getBalance()) {
				vendingMachine.decreaseBalance(item.getPrice());
				item.removeItem();
				System.out.println(item.getName() + " Cost: " + item.getPrice() + " You have: " + vendingMachine.getBalance() + " remaining.\n" + item.message());
			} else {
				System.out.println("Insufficient funds.");
			}
		} else {
			System.out.println("That is not a valid item.");
		}
	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
