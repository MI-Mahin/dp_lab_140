package main.smarthome.issue5;

import main.smarthome.issue1.Observer;

import java.util.ArrayList;
import java.util.List;

public class EnergyMonitor implements Observer {
    private double totalEnergyConsumed = 0.0; // kWh
    private double peakUsage = 0.0; // Maximum power usage
    private final List<Double> consumptionHistory = new ArrayList<>();
    private final double anomalyThreshold = 1.5; // 50% above average usage

    @Override
    public void update(String key, Object value) {
        if (key.equals("powerUsage")) {
            double currentUsage = (double) value;
            trackEnergyUsage(currentUsage);
        }
    }

    private void trackEnergyUsage(double currentUsage) {
        consumptionHistory.add(currentUsage);
        totalEnergyConsumed += currentUsage;

        // Update peak usage
        if (currentUsage > peakUsage) {
            peakUsage = currentUsage;
        }

        // Calculate average usage
        double avgUsage = consumptionHistory.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        // Detect unusual power spikes
        if (currentUsage > avgUsage * anomalyThreshold) {
            System.out.println("⚠ Warning: Unusual power spike detected! Current Usage: " + currentUsage + " kW");
        }

        System.out.println("Current Power Usage: " + currentUsage + " kW | Total Energy: " + totalEnergyConsumed + " kWh | Peak Usage: " + peakUsage + " kW");
    }
}
