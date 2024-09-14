interface PaymentMethod {
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing Credit Card Payment of " + amount);
    }
}

class PayPalPayment implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal Payment of " + amount);
    }
}

class DigitalWalletPayment implements PaymentMethod {
    public void processPayment(double amount) {
        System.out.println("Processing Digital Wallet Payment of " + amount);
    }
}