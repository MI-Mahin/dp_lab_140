import java.util.List;

class Admin {
    private static Admin instance;

    private Admin() {}

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    public void manageDriver(Driver driver) {
        System.out.println("Managing driver: " + driver.getName());
    }

    public void manageRider(Rider rider) {
        System.out.println("Managing rider: " + rider.getName());
    }

    public void viewTripHistory(List<Trip> trips) {
        for (Trip trip : trips) {
            System.out.println("Trip from " + trip.getPickupLocation() + " to " + trip.getDropOffLocation() + " - Status: " + trip.getStatus());
        }
    }

    public void handleDispute(String disputeDetails) {
        System.out.println("Handling dispute: " + disputeDetails);
    }
}
