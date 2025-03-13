package main.smarthome.issue7;

import main.smarthome.issue1.StateManager;

import java.util.Scanner;

public class RemoteAccessApp {
    private final StateManager stateManager;

    public RemoteAccessApp(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void displayHomeState() {
        System.out.println("\n Home Environment Status:");
        System.out.println(" Lights On: " + stateManager.getState("lightsOn"));
        System.out.println(" Temperature: " + stateManager.getState("targetTemperature") + "°C");
        System.out.println(" Motion Detected: " + stateManager.getState("motionDetected"));
        System.out.println(" Power Usage: " + stateManager.getState("powerUsage") + " kW");
    }

    public void generateNotifications() {
        if (Boolean.TRUE.equals(stateManager.getState("motionDetected"))) {
            System.out.println(" ALERT: Motion detected in your home!");
        }
        if (Double.parseDouble(stateManager.getState("powerUsage").toString()) > 2.5) {
            System.out.println(" Warning: High power consumption detected!");
        }
    }

    public void remoteControl(String command) {
        command = command.toLowerCase();
        if (command.contains("turn on the lights")) {
            stateManager.setState("lightsOn", true);
            System.out.println(" Lights turned ON.");
        } else if (command.contains("turn off the lights")) {
            stateManager.setState("lightsOn", false);
            System.out.println(" Lights turned OFF.");
        } else if (command.contains("set temperature to")) {
            double temp = extractTemperature(command);
            if (temp != -1) {
                stateManager.setState("targetTemperature", temp);
                System.out.println(" Temperature set to " + temp + "°C.");
            } else {
                System.out.println(" Invalid temperature command.");
            }
        } else {
            System.out.println(" Unknown command.");
        }
    }

    private double extractTemperature(String command) {
        String[] words = command.split(" ");
        for (String word : words) {
            try {
                return Double.parseDouble(word);
            } catch (NumberFormatException ignored) {}
        }
        return -1;
    }
}
