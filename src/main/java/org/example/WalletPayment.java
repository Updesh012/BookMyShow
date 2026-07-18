package org.example;

public class WalletPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Wallet");
        return true;
    }
}
