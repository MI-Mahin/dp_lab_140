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

public class UndoCommandTest {
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
    void undoCommand_ShouldRevertLastAction() {
        // Create an issue
        Issue issue = issueService.createIssue("Test Issue", "Test Description", Priority.MEDIUM);
        String issueId = issue.getId();

        // Change status
        issueService.changeIssueStatus(issueId, Status.IN_PROGRESS);

        // Act: Execute a command and then undo it
        issueService.undo();

        // Assert: Verify the system state is reverted to its previous state
        Optional<Issue> retrievedIssue = issueService.getIssue(issueId);

        assertTrue(retrievedIssue.isPresent());
        assertEquals(Status.OPEN, retrievedIssue.get().getStatus());
    }
}

