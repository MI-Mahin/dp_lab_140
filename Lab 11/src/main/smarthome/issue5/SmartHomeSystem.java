package main.smarthome.issue5;

import main.smarthome.issue1.StateManager;

public class SmartHomeSystem {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();

        EnergyMonitor energyMonitor = new EnergyMonitor();
        stateManager.registerObserver(energyMonitor);

        PowerSensor powerSensor = new PowerSensor(stateManager);

        // Simulating power usage updates
        for (int i = 0; i < 5; i++) {
            powerSensor.updatePowerUsage();
            Thread.sleep(2000);
        }
    }
}
