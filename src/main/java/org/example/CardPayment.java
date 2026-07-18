package org.example;

public class CardPayment implements PaymentStrategy {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Card ending "
                + cardNumber.substring(cardNumber.length() - 4));
        return true; // simulate success
    }
}
