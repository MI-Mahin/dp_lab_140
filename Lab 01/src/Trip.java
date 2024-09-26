import java.util.List;       // Import the List interface
import java.util.ArrayList;  // Import the ArrayList class

class Trip {
    private String id;
    private String pickupLocation;
    private String dropOffLocation;
    private RideType rideType;
    private double fare;
    private double distance;
    private double timeOfDay;
    private String status;
    private Rider rider;
    private Driver driver;
    private List<NotificationObserver> observers; // List to hold observers
    private PaymentMethod paymentMethod;

    public Trip(String id, String pickupLocation, String dropOffLocation, RideType rideType, Rider rider, Driver driver, double distance, double timeOfDay) {
        this.id = id;
        this.pickupLocation = pickupLocation;
        this.dropOffLocation = dropOffLocation;
        this.rideType = rideType;
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.timeOfDay = timeOfDay;
        this.status = "Pending";
        this.observers = new ArrayList<>(); // Initialize the ArrayList
        this.paymentMethod = rider.getSelectedPaymentMethod(); // Set payment method from rider
    }

    // Getter methods for accessing private fields
    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public RideType getRideType() {
        return rideType;
    }

    public double getDistance() {
        return distance;
    }

    public double getTimeOfDay() {
        return timeOfDay;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        System.out.println("Payment method updated to: " + paymentMethod.getClass().getSimpleName());
    }

    public void startTrip() {
        this.status = "Started";
        notifyObservers("Trip started from " + pickupLocation);
    }

    public void completeTrip() {
        this.fare = rideType.calculateFare(distance, timeOfDay);
        this.status = "Completed";
        notifyObservers("Trip completed at " + dropOffLocation + ". Fare: " + fare);
    }

    public void processPayment() {
        if (paymentMethod != null) {
            paymentMethod.processPayment(fare);
        } else {
            System.out.println("No payment method selected.");
        }
    }

    // Method to add observers (Rider, Driver, etc.)
    public void addNotificationObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    // Method to notify all observers of an event
    private void notifyObservers(String event) {
        for (NotificationObserver observer : observers) {
            observer.update(event);
        }
    }
}
