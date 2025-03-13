package main.smarthome.issue4;

import main.smarthome.issue1.StateManager;

import java.util.Random;

public class TemperatureSensor {
    private final StateManager stateManager;

    public TemperatureSensor(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void updateTemperature() {
        double temp = 15 + new Random().nextDouble() * 15; // Simulate temperature between 15-30°C
        stateManager.setState("currentTemperature", temp);
    }
}
