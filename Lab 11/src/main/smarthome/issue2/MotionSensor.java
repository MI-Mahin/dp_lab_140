package main.smarthome.issue2;

import main.smarthome.issue1.StateManager;

import java.time.LocalDateTime;
import java.util.Random;

public class MotionSensor {
    private final StateManager stateManager;
    private final String location;

    public MotionSensor(StateManager stateManager, String location) {
        this.stateManager = stateManager;
        this.location = location;
    }

    public void detectMotion() {
        boolean motionDetected = new Random().nextBoolean(); // Simulating motion detection
        LocalDateTime timestamp = LocalDateTime.now();

        if (motionDetected) {
            System.out.println("Motion detected at " + location + " at " + timestamp);
            stateManager.setState("motionDetected", true);
            stateManager.setState("motionDetails", new MotionData(location, timestamp));
        } else {
            System.out.println("No motion detected at " + location);
            stateManager.setState("motionDetected", false);
        }
    }
}
