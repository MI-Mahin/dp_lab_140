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

public class RedoCommandTest {
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
    void redoCommand_ShouldReapplyUndoneAction() {
        // Create an issue
        Issue issue = issueService.createIssue("Test Issue", "Test Description", Priority.MEDIUM);
        String issueId = issue.getId();

        // Change status
        issueService.changeIssueStatus(issueId, Status.IN_PROGRESS);

        // Undo the status change
        issueService.undo();

        // Act: Execute a command, undo it, then redo it
        issueService.redo();

        // Assert: Verify the system state reflects the reapplied command
        Optional<Issue> retrievedIssue = issueService.getIssue(issueId);

        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.IN_PROGRESS, retrievedIssue.get().getStatus());
    }
}

