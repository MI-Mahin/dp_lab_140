package org.example.command;

import java.time.LocalDateTime;

public interface Command {
    void execute();
    void undo();
    String getDescription();
    LocalDateTime getTimestamp();
    CommandType getType();
}

