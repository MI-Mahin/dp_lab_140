package org.example.command;

import java.time.LocalDateTime;

public abstract class AbstractCommand {
    private final LocalDateTime timestamp;
    private final CommandType type;

    protected AbstractCommand(CommandType type) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CommandType getType() {
        return type;
    }
}
