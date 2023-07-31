package com.console.main;

/**
 *
 * @author RAVEN
 */
public class ModelData {

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public ModelData(String month, double amount,  double profit) {
        this.month = month;
        this.amount = amount;
        
        this.profit = profit;
    }

    public ModelData() {
    }

    private String month;
    private double amount;
    private double profit;
}
