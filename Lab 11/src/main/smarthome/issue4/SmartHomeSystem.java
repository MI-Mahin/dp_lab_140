package main.smarthome.issue4;

import main.smarthome.issue1.StateManager;

public class SmartHomeSystem {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();

        TemperatureController tempController = new TemperatureController();
        stateManager.registerObserver(tempController);

        TemperatureSensor tempSensor = new TemperatureSensor(stateManager);

        // Simulating temperature changes
        for (int i = 0; i < 5; i++) {
            tempSensor.updateTemperature();
            Thread.sleep(2000);
        }

        // Adjust target temperature dynamically
        stateManager.setState("targetTemperature", 24.0);
    }
}
