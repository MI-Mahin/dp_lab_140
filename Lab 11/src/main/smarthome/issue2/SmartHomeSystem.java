package main.smarthome.issue2;

import main.smarthome.issue1.StateManager;

public class SmartHomeSystem {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();

        SmartLight light = new SmartLight();
        stateManager.registerObserver(light);

        MotionSensor hallwaySensor = new MotionSensor(stateManager, "Hallway");

        // Simulating periodic motion detection
        for (int i = 0; i < 5; i++) {
            hallwaySensor.detectMotion();
            Thread.sleep(2000); // Simulate delay between detections
        }
    }
}
