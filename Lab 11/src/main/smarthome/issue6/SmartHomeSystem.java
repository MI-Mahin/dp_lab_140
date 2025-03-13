package main.smarthome.issue6;

import main.smarthome.issue1.StateManager;

import java.util.Scanner;

public class SmartHomeSystem {
    public static void main(String[] args) {
        StateManager stateManager = new StateManager();
        VoiceCommandProcessor voiceProcessor = new VoiceCommandProcessor(stateManager);
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Voice Command System Active. Type commands below:");

        while (true) {
            System.out.print("You: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            voiceProcessor.processCommand(command);
        }

        voiceProcessor.printCommandHistory();
    }
}
