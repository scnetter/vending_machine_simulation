package com.techelevator.VendingMachineItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Map<String, VendingItem> inventory;
    public VendingMachine() {

        File inputFile = new File("vendingmachine.csv");
        try(Scanner scanner = new Scanner(inputFile)) {

            while (scanner.hasNextLine()) {
                String inLine = scanner.nextLine();


                String[] itemArray = inLine.split("\\|");
                VendingItem vendingItem = new VendingItem(itemArray[0], itemArray[1], Double.parseDouble(itemArray[2]), itemArray[3]);
                this.inventory.put(vendingItem.getSlot(), vendingItem);
            }
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
