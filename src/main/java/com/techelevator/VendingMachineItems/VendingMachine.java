package com.techelevator.VendingMachineItems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    private Map<String, VendingItem> inventory;
    public VendingMachine() throws FileNotFoundException {

        File inputFile = new File("vendingmachine.csv");
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String inLine = scanner.nextLine();


            String[] itemArray = inLine.split("\\|");
        }
    }

}
