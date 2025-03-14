# Text Editor Project - Issue Tracking

## Issue #1: Initial Project Setup
**Title:** Set up basic Text Editor project structure

**Description:**
- Create a new C# console application named "TextEditor"
- Set up the initial project structure
- Create placeholder files for core classes:
  - TextEditor.cs
  - EditorMemento.cs
  - History.cs
  - Program.cs

**Acceptance Criteria:**
- Project compiles successfully
- All placeholder files are created
- Basic namespaces are defined

---

## Issue #2: Implement Core TextEditor Class
**Title:** Implement basic TextEditor functionality

**Description:**
Implement the core TextEditor class with basic text editing capabilities:
- Add private fields for content, cursor position, and selections
- Implement constructor
- Create methods:
  - SetContent(string content)
  - SetCursorPosition(int position)
  - AddSelection(string selection)
  - ClearSelections()
  - DisplayState()

**Acceptance Criteria:**
- All methods are implemented
- Basic validation is in place for cursor position
- Console output shows current state clearly
- Class compiles without errors

---

## Issue #3: Implement Memento Pattern Base
**Title:** Create EditorMemento class and basic state management

**Description:**
Implement the Memento pattern's core components:
1. Create EditorMemento class with:
   - Properties for Content, CursorPosition, and Selections
   - Constructor to initialize state
2. Add to TextEditor:
   - CreateMemento() method
   - RestoreFromMemento(EditorMemento memento) method

**Acceptance Criteria:**
- EditorMemento class properly stores editor state
- TextEditor can create and restore from mementos
- All properties are properly encapsulated

---

## Issue #4: Implement History Management
**Title:** Add History class for state management

**Description:**
Create the History (Caretaker) class to manage editor states:
- Implement:
  - Constructor taking TextEditor instance
  - Backup() method to save current state
  - Undo() method to restore previous state
- Add proper state management using List<EditorMemento>

**Acceptance Criteria:**
- History tracks editor states correctly
- Undo restores to previous state
- Proper handling of empty history
- Clear console feedback for operations

---

## Issue #5: Add File Persistence
**Title:** Implement save/load functionality using files

**Description:**
Add file-based persistence to History class:
1. Implement:
   - SaveToFile(string filePath) method
   - LoadFromFile(string filePath) method
2. Use JSON serialization for state persistence
3. Add proper error handling for file operations

**Acceptance Criteria:**
- States can be saved to JSON files
- States can be loaded from JSON files
- Proper error handling for file operations
- Clear console feedback for success/failure

---

## Issue #6: Create Unit Tests
**Title:** Implement comprehensive unit tests

**Description:**
Create unit tests for all major functionality:
- Set up xUnit test project
- Create test classes for:
  - Basic editor operations
  - Memento creation/restoration
  - History management
  - File operations

**Test Cases:**
- SetContent_ShouldUpdateContent
- SetCursorPosition_ValidPosition_ShouldUpdatePosition
- AddSelection_ShouldAddToSelections
- ClearSelections_ShouldRemoveAllSelections
- SaveAndLoadState_ShouldPreserveEditorState
- Undo_ShouldRestorePreviousState

**Acceptance Criteria:**
- All test cases pass
- Good test coverage of core functionality
- Tests are well-documented
- Edge cases are covered
