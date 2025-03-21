package org.example;

import org.example.command.CommandManager;
import org.example.model.Priority;
import org.example.model.Status;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLoggingTest {
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
    void logger_ShouldStoreCommandHistoryLogs() {
        // Act: Execute multiple commands
        var issue = issueService.createIssue("Test Issue", "Description", Priority.MEDIUM);
        issueService.changeIssueStatus(issue.getId(), Status.IN_PROGRESS);
        issueService.addComment(issue.getId(), "Test comment", "User");

        // Assert: Verify logs contain correct command descriptions and count
        assertEquals(3, logService.getLogCount());
        assertTrue(logService.getLogs().get(0).contains("Created issue"));
        assertTrue(logService.getLogs().get(1).contains("Changed issue status"));
        assertTrue(logService.getLogs().get(2).contains("Added comment"));
    }
}


