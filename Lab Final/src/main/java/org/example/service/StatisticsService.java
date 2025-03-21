package org.example.service;

import org.example.command.Command;
import org.example.command.CommandType;
import org.example.command.CreateIssueCommand;

import java.util.EnumMap;
import java.util.Map;

public class StatisticsService {
    private final Map<CommandType, Integer> commandCounts = new EnumMap<>(CommandType.class);
    private int issuesCreated = 0;

    public StatisticsService() {
        // Initialize counters for all command types
        for (CommandType type : CommandType.values()) {
            commandCounts.put(type, 0);
        }
    }

    public void recordCommand(Command command) {
        // Increment command type counter
        CommandType type = command.getType();
        commandCounts.put(type, commandCounts.get(type) + 1);

        // Track issues created
        if (command instanceof CreateIssueCommand) {
            issuesCreated++;
        }
    }

    public int getCommandCount(CommandType type) {
        return commandCounts.getOrDefault(type, 0);
    }

    public int getTotalCommandCount() {
        return commandCounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getIssuesCreated() {
        return issuesCreated;
    }

    public Map<CommandType, Integer> getCommandCounts() {
        return new EnumMap<>(commandCounts);
    }
}

