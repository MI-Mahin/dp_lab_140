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
    private NotificationService notificationService;

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
        this.notificationService = new NotificationService();
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void startTrip() {
        this.status = "Started";
        notificationService.notifyObservers("Trip started from " + pickupLocation);
    }

    public void completeTrip() {
        this.fare = rideType.calculateFare(distance, timeOfDay);
        this.status = "Completed";
        notificationService.notifyObservers("Trip completed at " + dropOffLocation + ". Fare: " + fare);
    }

    public double getFare() {
        return fare;
    }

    public String getStatus() {
        return status;
    }

    public void addNotificationObserver(NotificationObserver observer) {
        notificationService.addObserver(observer);
    }
}
