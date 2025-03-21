package org.example.command;

import org.example.observer.CommandObserver;
import org.example.service.LogService;
import org.example.service.StatisticsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();
    private final List<CommandObserver> observers = new ArrayList<>();
    private final LogService logService;
    private final StatisticsService statisticsService;

    public CommandManager(LogService logService, StatisticsService statisticsService) {
        this.logService = logService;
        this.statisticsService = statisticsService;
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear(); // Clear redo stack when a new command is executed

        // Log the command
        logService.logCommand(command);

        // Update statistics
        statisticsService.recordCommand(command);

        // Notify observers
        notifyObservers(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);

            // Log the undo operation
            logService.logUndo(command);

            // Notify observers
            notifyObservers(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);

            // Log the redo operation
            logService.logRedo(command);

            // Notify observers
            notifyObservers(command);
        }
    }

    public void addObserver(CommandObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CommandObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Command command) {
        for (CommandObserver observer : observers) {
            observer.update(command);
        }
    }

    public List<Command> getCommandHistory() {
        return new ArrayList<>(undoStack);
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}

