package main.smarthome.issue1;

public class SmartHomeSystem {
    public static void main(String[] args) {
        StateManager stateManager = new StateManager();

        SmartLight light = new SmartLight();
        Thermostat thermostat = new Thermostat();

        stateManager.registerObserver(light);
        stateManager.registerObserver(thermostat);

        stateManager.setState("motionDetected", true);
        stateManager.setState("temperature", 22.5);
    }
}
