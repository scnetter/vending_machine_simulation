package com.techelevator.VendingMachineItems;

public class VendingItem {
    private String name;
    private double price;
    private String slot;
    private int remaining;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public VendingItem(String slot, String name, double price){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.remaining = 5;
    }
}
