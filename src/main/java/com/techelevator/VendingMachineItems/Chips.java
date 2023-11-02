package com.techelevator.VendingMachineItems;

public class Chips extends VendingItem{

    public Chips (String slot, String name, double price, String category) {
        super (slot, name, price, category);
    }

    public String message () {
        return "Crunch Crunch, It's Yummy!";
    }
}
