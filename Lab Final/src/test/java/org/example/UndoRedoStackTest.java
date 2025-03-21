package org.example;

import org.example.command.CommandManager;
import org.example.model.Issue;
import org.example.model.Priority;
import org.example.model.Status;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UndoRedoStackTest {
    private IssueRepository repository;
    private CommandManager commandManager;
    private IssueService issueService;
    private LogService logService;
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        repository = new IssueRepository();
        logService = new LogService();
        statisticsService = new StatisticsService();
        commandManager = new CommandManager(logService, statisticsService);
        issueService = new IssueService(repository, commandManager);
    }

    @Test
    void undoRedo_ShouldMaintainProperStacks() {
        // Arrange: Create an issue and change its status twice
        Issue issue = issueService.createIssue("Test Issue", "Description", Priority.MEDIUM);
        issueService.changeIssueStatus(issue.getId(), Status.IN_PROGRESS);
        issueService.changeIssueStatus(issue.getId(), Status.UNDER_REVIEW);

        // Act 1: Undo the last executed command twice
        issueService.undo(); // Undo UNDER_REVIEW -> IN_PROGRESS
        issueService.undo(); // Undo IN_PROGRESS -> OPEN

        // Assert 1: Issue should be back to OPEN
        Optional<Issue> retrievedIssue = issueService.getIssue(issue.getId());
        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.OPEN, retrievedIssue.get().getStatus());

        // Act 2: Redo one command that was just undone
        issueService.redo(); // Redo OPEN -> IN_PROGRESS

        // Assert 2: Issue should now be IN_PROGRESS
        retrievedIssue = issueService.getIssue(issue.getId());
        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.IN_PROGRESS, retrievedIssue.get().getStatus());

        // Act 3: Perform a new command
        issueService.changeIssueStatus(issue.getId(), Status.RESOLVED);

        // Assert 3: Issue should now be RESOLVED
        retrievedIssue = issueService.getIssue(issue.getId());
        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.RESOLVED, retrievedIssue.get().getStatus());

        // Act 4: Try to redo again (should not be possible)
        boolean canRedo = issueService.canRedo();
        issueService.redo(); // This should have no effect

        // Assert 4: Verify command stack maintains correct state after operations
        assertFalse(canRedo); // Cannot redo after new command
        retrievedIssue = issueService.getIssue(issue.getId());
        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.RESOLVED, retrievedIssue.get().getStatus()); // Status should still be RESOLVED
    }
}

