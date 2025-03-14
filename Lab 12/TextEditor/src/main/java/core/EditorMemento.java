package core;

import java.util.List;

public class EditorMemento {
    private final String content;
    private final int cursorPosition;
    private final List<String> selections;

    // Constructor to initialize state
    public EditorMemento(String content, int cursorPosition, List<String> selections) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.selections = List.copyOf(selections); // Immutable copy for safety
    }

    // Getters to retrieve stored state
    public String getContent() {
        return content;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public List<String> getSelections() {
        return selections;
    }
}

