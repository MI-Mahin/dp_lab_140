import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Create rider and driver
        Rider rider = new Rider("Mahmud");
        Driver driver = new Driver("Mahin");

        // Rider selects payment method at the beginning
        rider.selectPaymentMethod();

        // Create a trip using the RideTypeFactory
        RideType rideType = RideTypeFactory.createRideType("Luxury");
        Trip trip = new Trip("1", "Uttara", "Boardbazart", rideType, rider, driver, 10.0, 8.0);

        // Add observers (rider and driver) to the trip
        trip.addNotificationObserver(rider);
        trip.addNotificationObserver(driver);

        // Start the trip
        trip.startTrip();

        // Change payment method during the trip
        System.out.println("Do you want to change the payment method? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String changePayment = scanner.nextLine();

        if (changePayment.equalsIgnoreCase("yes")) {
            rider.selectPaymentMethod();
            trip.setPaymentMethod(rider.getSelectedPaymentMethod());
        }

        // Complete the trip and process the payment
        trip.completeTrip();
        trip.processPayment();
    }
}
