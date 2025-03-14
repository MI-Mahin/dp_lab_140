package core;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History(editor);

        // Set initial content and backup state
        editor.setContent("Hello, World!");
        editor.setCursorPosition(5);
        editor.addSelection("Hello");
        history.backup(); // Save current state
        editor.displayState();

        // Modify the editor and backup state
        editor.setContent("New Content");
        editor.setCursorPosition(2);
        editor.clearSelections();
        history.backup(); // Save modified state
        editor.displayState();

        // Undo last change
        history.undo(); // Restore previous state
        editor.displayState();

        // Undo again (should restore initial state)
        history.undo(); // Restore initial state
        editor.displayState();

        // Attempt to undo with no history
        history.undo(); // No more history to undo
    }
}
