package org.example.observer;

import org.example.command.Command;

public interface CommandObserver {
    void update(Command command);
}

