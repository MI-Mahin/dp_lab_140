package org.example;

import org.example.command.CommandManager;
import org.example.command.CommandType;
import org.example.model.Priority;
import org.example.model.Status;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsCollectionTest {
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
    void statisticsCollector_ShouldTrackCommandCounts() {
        // Act: Execute multiple commands
        var issue1 = issueService.createIssue("Issue 1", "Description 1", Priority.LOW);
        var issue2 = issueService.createIssue("Issue 2", "Description 2", Priority.HIGH);
        issueService.changeIssueStatus(issue1.getId(), Status.IN_PROGRESS);
        issueService.addComment(issue2.getId(), "Test comment", "User");

        // Assert: Verify command counts are tracked correctly by type
        assertEquals(2, statisticsService.getCommandCount(CommandType.CREATE));
        assertEquals(2, statisticsService.getCommandCount(CommandType.UPDATE));
        assertEquals(0, statisticsService.getCommandCount(CommandType.DELETE));
        assertEquals(4, statisticsService.getTotalCommandCount());
        assertEquals(2, statisticsService.getIssuesCreated());
    }
}

