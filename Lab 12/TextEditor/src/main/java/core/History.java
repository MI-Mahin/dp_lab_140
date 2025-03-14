package core;

import java.util.ArrayList;
import java.util.List;

public class History {
    private final TextEditor editor;
    private final List<EditorMemento> mementoHistory = new ArrayList<>();

    // Constructor
    public History(TextEditor editor) {
        this.editor = editor;
    }

    // Backup the current state of the editor
    public void backup() {
        mementoHistory.add(editor.createMemento());
        System.out.println("State saved.");
    }

    // Undo the last change by restoring the previous state
    public void undo() {
        if (mementoHistory.isEmpty()) {
            System.out.println("No history to undo.");
        } else {
            EditorMemento memento = mementoHistory.remove(mementoHistory.size() - 1);
            editor.restoreFromMemento(memento);
            System.out.println("Undone to previous state.");
        }
    }

    // Display the current history states
    public void displayHistory() {
        System.out.println("History:");
        for (int i = 0; i < mementoHistory.size(); i++) {
            System.out.println("State " + (i + 1) + ": " + mementoHistory.get(i).getContent());
        }
    }
}
