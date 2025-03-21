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

public class CreateIssueTest {
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
    void createIssue_ShouldAddIssueToRepository() {
        // Act: Create a new issue with title, description, and priority
        String title = "Test Issue";
        String description = "This is a test issue";
        Priority priority = Priority.MEDIUM;

        Issue issue = issueService.createIssue(title, description, priority);

        // Assert: Verify that the issue exists in repository with correct properties and default status
        Optional<Issue> retrievedIssue = issueService.getIssue(issue.getId());

        assertTrue(retrievedIssue.isPresent());
        assertEquals(title, retrievedIssue.get().getTitle());
        assertEquals(description, retrievedIssue.get().getDescription());
        assertEquals(priority, retrievedIssue.get().getPriority());
        assertEquals(Status.OPEN, retrievedIssue.get().getStatus()); // Default status should be OPEN
    }
}

