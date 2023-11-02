package com.techelevator.VendingMachineItems;

public class Candy extends VendingItem{

    public Candy (String slot, String name, double price, String category) {
        super (slot, name, price, category);
    }

    public String message () {
        return "Munch Munch, Mmm Mmm Good!";
    }
}
