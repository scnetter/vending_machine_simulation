package com.techelevator.VendingMachineItems;

import java.util.HashMap;
import java.util.Map;

public class ChangeCalculator {
    public static Map<String, String> calculateChange(double dollarAmount) {
        // Define the available coin denominations
        double[] coinDenominations = {0.01, 0.05, 0.1, 0.25, 1.0, 5.0, 10.0, 20.0, 50.0, 100.0};

        // Create a map to store the result
        Map<String, String> change = new HashMap<>();

        // Convert dollar amount to cents for easier calculations
        int centsAmount = (int) (dollarAmount * 100);

        // Iterate through coin denominations in descending order
        for (int i = coinDenominations.length - 1; i >= 0; i--) {
            double coinValue = coinDenominations[i];
            int numCoins = (int) (centsAmount / (coinValue * 100));

            // If we can use this coin, update the change map
            if (numCoins > 0) {
                change.put(formatCoin(coinValue, coinDenominations[i]), Integer.toString(numCoins));
                centsAmount -= numCoins * (coinValue * 100);
            }
        }

        return change;
    }

    private static String formatCoin(double coinValue, double denomination) {
        if (coinValue >= 1.0) {
            return "$" + (int) coinValue + " Bill(s)";
        } else {
            String tempCoinValue = Double.toString(coinValue);
            switch((Double.toString(coinValue))){
                case "0.25":
                    return "Quarter(s)";
                case "0.1":
                    return "Dime(s)";
                case "0.05":
                    return "Nickel(s)";
                case "0.01":
                    return "Pennies";
                default:
                    return "Something went wrong.";
            }
            //return Integer.toString((int) (coinValue * 100)) + "¢";
        }
    }
}
