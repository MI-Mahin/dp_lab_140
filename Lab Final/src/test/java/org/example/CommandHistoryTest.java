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

public class CommandHistoryTest {
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
    void commandHistory_ShouldTrackAllExecutedCommands() {
        // Act: Execute multiple commands
        var issue = issueService.createIssue("Test Issue", "Description", Priority.MEDIUM);
        issueService.changeIssueStatus(issue.getId(), Status.IN_PROGRESS);
        issueService.addComment(issue.getId(), "Test comment", "User");

        // Assert: Verify command history contains all executed commands in correct order
        assertEquals(3, commandManager.getCommandHistory().size());
        assertEquals("Created issue: Test Issue", commandManager.getCommandHistory().get(0).getDescription());
        assertEquals("Changed issue status from OPEN to IN_PROGRESS for issue: " + issue.getId(),
                commandManager.getCommandHistory().get(1).getDescription());
        assertEquals("Added comment by User to issue: " + issue.getId(),
                commandManager.getCommandHistory().get(2).getDescription());
    }
}

