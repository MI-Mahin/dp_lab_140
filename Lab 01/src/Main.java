import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create rider and driver
        Rider rider = new Rider("Alice");
        Driver driver = new Driver("Bob");

        // Create a trip using the RideTypeFactory
        RideType rideType = RideTypeFactory.createRideType("Luxury");
        Trip trip = new Trip("1", "123 Main St", "456 Elm St", rideType, rider, driver, 10.0, 14.0);

        // Add notifications
        trip.addNotificationObserver(rider);
        trip.addNotificationObserver(driver);

        // Start and complete the trip
        trip.startTrip();
        trip.completeTrip();

        // Handle payment
        PaymentMethod paymentMethod = new CreditCardPayment();
        paymentMethod.processPayment(trip.getFare());

        // Admin management
        Admin admin = Admin.getInstance();
        List<Trip> trips = new ArrayList<>();
        trips.add(trip);
        admin.manageDriver(driver);
        admin.manageRider(rider);
        admin.viewTripHistory(trips);
    }
}