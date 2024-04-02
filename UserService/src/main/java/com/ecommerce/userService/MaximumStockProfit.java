package com.ecommerce.userService;

public class MaximumStockProfit {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0; // If there are less than 2 prices, profit is 0
        }

        int minPrice = prices[0]; // Initialize the minimum price to the first element
        int maxProfit = 0; // Initialize the maximum profit to 0

        // Iterate through the array starting from the second element
        for (int i = 1; i < prices.length; i++) {
            // Update the minimum price if a lower price is encountered
            minPrice = Math.min(minPrice, prices[i]);
            // Calculate the profit if selling at the current price
            int currentProfit = prices[i] - minPrice;
            // Update the maximum profit if the current profit is greater
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxProfit = maxProfit(prices);
        System.out.println("Maximum Profit: " + maxProfit); // Output: 5 (Buy at 1, Sell at 6)
    }
}

