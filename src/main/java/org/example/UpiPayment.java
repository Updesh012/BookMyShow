package org.example;

public class UpiPayment implements PaymentStrategy {

    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid ₹" + amount + " via UPI: " + upiId);
        return true;
    }

}
