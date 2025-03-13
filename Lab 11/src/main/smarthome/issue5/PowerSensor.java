package main.smarthome.issue5;

import main.smarthome.issue1.StateManager;

import java.util.Random;

public class PowerSensor {
    private final StateManager stateManager;

    public PowerSensor(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void updatePowerUsage() {
        double powerUsage = 0.5 + new Random().nextDouble() * 2.5; // Simulate power usage between 0.5-3 kW
        stateManager.setState("powerUsage", powerUsage);
    }
}
