package com.techelevator.VendingMachineItems;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    public void logActivity(String message) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObject = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
        File logFile = new File("Log.txt");
        PrintWriter writer = null;

        if (logFile.exists()) {
            try {
                writer = new PrintWriter(new FileWriter(logFile, true));
            } catch (FileNotFoundException e) {
                e.getMessage();
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            try {
                writer = new PrintWriter(logFile);
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
        }
        writer.append(currentDateTime.format(myFormatObject) + " " + message + "\n");
        writer.flush();
        writer.close();
    }

    private final Map<String, VendingItem> inventory;

    private double balance;

    public VendingMachine() {
        inventory = new HashMap<String, VendingItem>();
        VendingItem vendingItem;
        File inputFile = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(inputFile)) {

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
                    case "Chip":
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }


    public Map<String, VendingItem> getInventory() {
        return this.inventory;
    }

    public void increaseBalance(double money) {
        balance += money;
    }

    public void decreaseBalance(double money) {
        balance -= money;
    }

    public Map<String, String> returnChange() {
        return ChangeCalculator.calculateChange(balance);
    }

    public void displayCurrentInventory() {
        System.out.printf("%-4s %-20s %-10s %-10s \n", "Slot", "Item Name", "Price", "Remaining");
        System.out.println("----------------------------------------------");
        Map<String, VendingItem> inventory = getInventory();
        for (VendingItem item : inventory.values()) {
            if (item.getRemaining() > 0) {
                System.out.printf("%-4s %-20s %-10.2f %4d \n", item.getSlot(), item.getName(), item.getPrice(), item.getRemaining());
            } else {
                System.out.printf("%-4s %-20s %-10.2f %8s \n", item.getSlot(), item.getName(), item.getPrice(), "SOLD OUT");
            }

        }
    }

}
