package com.techelevator.VendingMachineItems;

public class Gum extends VendingItem{

    public Gum (String slot, String name, double price, String category) {
        super (slot, name, price, category);
    }

    public String message () {
        return "Chew Chew, Pop!";
    }
}
