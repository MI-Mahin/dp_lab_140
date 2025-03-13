package main.smarthome.issue6;

import main.smarthome.issue1.StateManager;

import java.util.ArrayList;
import java.util.List;

public class VoiceCommandProcessor {
    private final StateManager stateManager;
    private final List<String> commandHistory = new ArrayList<>();

    public VoiceCommandProcessor(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void processCommand(String command) {
        commandHistory.add(command);
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
            System.out.println(" Unknown command: " + command);
        }
    }

    private double extractTemperature(String command) {
        String[] words = command.split(" ");
        for (int i = 0; i < words.length; i++) {
            try {
                return Double.parseDouble(words[i]);
            } catch (NumberFormatException ignored) {}
        }
        return -1;
    }

    public void printCommandHistory() {
        System.out.println("\n Command History:");
        for (String cmd : commandHistory) {
            System.out.println("- " + cmd);
        }
    }
}
