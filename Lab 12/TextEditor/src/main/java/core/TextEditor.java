package core;

import java.util.ArrayList;
import java.util.List;

public class TextEditor {
    private String content;
    private int cursorPosition;
    private List<String> selections;

    // Constructor
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
        this.selections = new ArrayList<>();
    }

    // Set content
    public void setContent(String content) {
        this.content = content;
        this.cursorPosition = content.length(); // Move cursor to end
    }

    // Set cursor position with validation
    public void setCursorPosition(int position) {
        if (position < 0 || position > content.length()) {
            System.out.println("Invalid cursor position!");
        } else {
            this.cursorPosition = position;
        }
    }

    // Add a selection
    public void addSelection(String selection) {
        selections.add(selection);
    }

    // Clear selections
    public void clearSelections() {
        selections.clear();
    }

    // Create Memento (Save State)
    public EditorMemento createMemento() {
        return new EditorMemento(content, cursorPosition, selections);
    }

    // Restore from Memento
    public void restoreFromMemento(EditorMemento memento) {
        if (memento != null) {
            this.content = memento.getContent();
            this.cursorPosition = memento.getCursorPosition();
            this.selections = new ArrayList<>(memento.getSelections()); // Restore selections
        }
    }

    // Display current state
    public void displayState() {
        System.out.println("Content: " + content);
        System.out.println("Cursor Position: " + cursorPosition);
        System.out.println("Selections: " + selections);
    }
}
