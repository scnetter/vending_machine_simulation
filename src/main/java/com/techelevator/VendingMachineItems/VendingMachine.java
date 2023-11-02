package com.techelevator.VendingMachineItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Map<String, VendingItem> inventory;
    public VendingMachine() {
        inventory = new HashMap<String, VendingItem>();
        VendingItem vendingItem;
        File inputFile = new File("vendingmachine.csv");
        try(Scanner scanner = new Scanner(inputFile)) {

            while (scanner.hasNextLine()) {
                String inLine = scanner.nextLine();


                String[] itemArray = inLine.split("\\|");
                switch (itemArray[3]) {
                    case "Candy":
                        vendingItem = new Candy(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                        break;
                    case "Gum":
                        vendingItem = new Gum(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                        break;
                    case "Chips":
                        vendingItem = new Chips(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                        break;
                    case "Drink":
                        vendingItem = new Drink(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                        break;
                    default:
                        vendingItem = new VendingItem(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                }
                this.inventory.put(vendingItem.getSlot(), vendingItem);
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public Map<String, VendingItem> getInventory(){
        return this.inventory;
    }

}
