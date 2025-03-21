package org.example.service;

import org.example.command.Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    private final List<String> logs = new ArrayList<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logCommand(Command command) {
        String log = formatLog(command.getTimestamp(), "EXECUTE", command.getDescription());
        logs.add(log);
    }

    public void logUndo(Command command) {
        String log = formatLog(LocalDateTime.now(), "UNDO", command.getDescription());
        logs.add(log);
    }

    public void logRedo(Command command) {
        String log = formatLog(LocalDateTime.now(), "REDO", command.getDescription());
        logs.add(log);
    }

    private String formatLog(LocalDateTime timestamp, String action, String description) {
        return String.format("[%s] %s: %s", timestamp.format(formatter), action, description);
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public int getLogCount() {
        return logs.size();
    }
}

