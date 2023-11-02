package com.techelevator.VendingMachineItems;

public class Drink extends VendingItem{

    public Drink (String slot, String name, double price, String category) {
        super (slot, name, price, category);
    }

    public String message () {
        return "Glug Glug, Chug Chug!";
    }
}
