public interface RideType {
    double calculateFare(double distance, double timeOfDay);
}

class CarpoolRide implements RideType {
    public double calculateFare(double distance, double timeOfDay) {
        return distance * 0.5;
    }
}

class LuxuryRide implements RideType {
    public double calculateFare(double distance, double timeOfDay) {
        return distance * 2.0 + timeOfDay * 1.5;
    }
}

class BikeRide implements RideType {
    public double calculateFare(double distance, double timeOfDay) {
        return distance * 0.8;
    }
}
