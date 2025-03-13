package main.smarthome.issue7;

import main.smarthome.issue1.StateManager;

import java.util.Scanner;

public class SmartHomeSystem {
    public static void main(String[] args) throws InterruptedException {
        StateManager stateManager = new StateManager();
        RemoteAccessApp remoteApp = new RemoteAccessApp(stateManager);
        Scanner scanner = new Scanner(System.in);

        // Simulating some initial states
        stateManager.setState("lightsOn", false);
        stateManager.setState("targetTemperature", 24.0);
        stateManager.setState("motionDetected", false);
        stateManager.setState("powerUsage", 1.2);

        System.out.println(" Remote Access App Started!");

        while (true) {
            remoteApp.displayHomeState();
            remoteApp.generateNotifications();

            System.out.print("\nEnter command (or type 'exit' to quit): ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            remoteApp.remoteControl(command);
        }
    }
}
