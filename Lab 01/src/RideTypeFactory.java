class RideTypeFactory {
    public static RideType createRideType(String type) {
        switch (type.toLowerCase()) {
            case "carpool":
                return new CarpoolRide();
            case "luxury":
                return new LuxuryRide();
            case "bike":
                return new BikeRide();
            default:
                throw new IllegalArgumentException("Unknown ride type");
        }
    }
}
