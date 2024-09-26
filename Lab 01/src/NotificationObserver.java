import java.util.Scanner;


interface NotificationObserver {
    void update(String event);
}


// Rider class to handle payment method selection
class Rider implements NotificationObserver {
    private String name;
    private PaymentMethod selectedPaymentMethod;

    public Rider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    public void selectPaymentMethod() {
        System.out.println("Select a payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Digital Wallet");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                selectedPaymentMethod = new CreditCardPayment();
                break;
            case 2:
                selectedPaymentMethod = new PayPalPayment();
                break;
            case 3:
                selectedPaymentMethod = new DigitalWalletPayment();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Credit Card.");
                selectedPaymentMethod = new CreditCardPayment();
                break;
        }
        System.out.println("Payment method selected: " + selectedPaymentMethod.getClass().getSimpleName());
    }

    @Override
    public void update(String event) {
        System.out.println("Rider " + name + " notified: " + event);
    }
}

class Driver implements NotificationObserver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String event) {
        System.out.println("Driver " + name + " notified: " + event);
    }
}



