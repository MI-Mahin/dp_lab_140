package main.smarthome.issue3;

import main.smarthome.issue1.StateManager;

import java.util.Random;

public class MotionSensor {
    private final StateManager stateManager;
    private final String location;

    public MotionSensor(StateManager stateManager, String location) {
        this.stateManager = stateManager;
        this.location = location;
    }

    public void detectMotion() {
        boolean motionDetected = new Random().nextBoolean();
        stateManager.setState("motionDetected", motionDetected);
    }
}
