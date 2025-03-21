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

public class ChangeStatusTest {
    private IssueRepository repository;
    private CommandManager commandManager;
    private IssueService issueService;
    private LogService logService;
    private StatisticsService statisticsService;
    private Issue testIssue;

    @BeforeEach
    void setUp() {
        repository = new IssueRepository();
        logService = new LogService();
        statisticsService = new StatisticsService();
        commandManager = new CommandManager(logService, statisticsService);
        issueService = new IssueService(repository, commandManager);

        // Create a test issue
        testIssue = issueService.createIssue("Test Issue", "Test Description", Priority.MEDIUM);
    }

    @Test
    void changeStatus_ShouldUpdateIssueStatus() {
        // Act: Change the status of an existing issue
        Status newStatus = Status.IN_PROGRESS;
        issueService.changeIssueStatus(testIssue.getId(), newStatus);

        // Assert: Verify the issue status is updated to the new value
        Optional<Issue> retrievedIssue = issueService.getIssue(testIssue.getId());

        assertTrue(retrievedIssue.isPresent());
        assertEquals(newStatus, retrievedIssue.get().getStatus());
    }
}

