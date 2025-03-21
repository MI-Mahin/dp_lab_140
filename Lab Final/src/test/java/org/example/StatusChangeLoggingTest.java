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

import static org.junit.jupiter.api.Assertions.*;

public class StatusChangeLoggingTest {
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
    void changeIssueStatusCommand_ShouldLogCorrectDescription() {
        // Act: Change issue status and check logs
        Issue issue = issueService.createIssue("Test Issue", "Description", Priority.MEDIUM);
        issueService.changeIssueStatus(issue.getId(), Status.IN_PROGRESS);

        // Assert: Verify log contains correct status change description
        String expectedLogContent = "Changed issue status from OPEN to IN_PROGRESS for issue: " + issue.getId();
        assertTrue(logService.getLogs().get(1).contains(expectedLogContent));
    }
}

