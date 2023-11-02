package com.techelevator;

import com.techelevator.VendingMachineItems.VendingItem;
import com.techelevator.VendingMachineItems.VendingMachine;
import com.techelevator.view.VendingMenu;

import java.util.Map;


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
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			VendingMachine vendMachine = new VendingMachine();

			switch (choice) {
				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					// display vending machine items
//					// List<Items> items = vendingMachine.getInventory()
//					// for(Item item : items){
//					//    printf
					System.out.printf("%-4s %-20s %-10s \n", "Slot", "Item Name", "Price");
					System.out.println("--------------------------------");
					Map<String, VendingItem> inventory = vendMachine.getInventory();
					for(VendingItem item : inventory.values()){
						System.out.printf("%-4s %-20s %-10.2f \n", item.getSlot(), item.getName(), item.getPrice());
					}
					break;
				case MAIN_MENU_OPTION_PURCHASE:
					break;
				case MAIN_MENU_OPTION_EXIT:
					running = false;
			}
		}
	}

	public static void main(String[] args) {
		VendingMenu menu = new VendingMenu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
