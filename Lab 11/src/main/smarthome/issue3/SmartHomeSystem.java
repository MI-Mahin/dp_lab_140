package main.smarthome.issue3;

import main.smarthome.issue1.StateManager;

import java.util.Random;

public class SmartHomeSystem {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();

        LightController lightController = new LightController();
        stateManager.registerObserver(lightController);

        MotionSensor sensor = new MotionSensor(stateManager, "Living Room");

        // Simulate motion and ambient light changes
        for (int i = 0; i < 5; i++) {
            sensor.detectMotion();
            stateManager.setState("ambientLight", new Random().nextInt(100)); // Simulate light levels
            Thread.sleep(2000);
        }

        // Change scene to "Movie"
        stateManager.setState("scene", "Movie");
    }
}
