import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create rider and driver
        Rider rider = new Rider("Mahmud");
        Driver driver = new Driver("Mahin");

        // Create a trip using the RideTypeFactory
        RideType rideType = RideTypeFactory.createRideType("bike");
        Trip trip = new Trip("1", "Uttara", "Board Bazar", rideType, rider, driver, 10.0, 8.0);

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