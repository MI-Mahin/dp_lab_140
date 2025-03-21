package org.example;

import org.example.command.CommandManager;
import org.example.model.Issue;
import org.example.model.Priority;
import org.example.model.Status;
import org.example.observer.NotificationService;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverNotificationTest {
    private IssueRepository repository;
    private CommandManager commandManager;
    private IssueService issueService;
    private LogService logService;
    private StatisticsService statisticsService;
    private NotificationService notificationService;

    @BeforeEach
    void setUp() {
        repository = new IssueRepository();
        logService = new LogService();
        statisticsService = new StatisticsService();
        commandManager = new CommandManager(logService, statisticsService);
        issueService = new IssueService(repository, commandManager);
        notificationService = new NotificationService();
        commandManager.addObserver(notificationService);
    }

    @Test
    void observer_ShouldBeNotifiedOfCommands() {
        // Act: Execute a command with registered observer
        Issue issue = issueService.createIssue("Test Issue", "Test Description", Priority.MEDIUM);
        issueService.changeIssueStatus(issue.getId(), Status.IN_PROGRESS);

        // Assert: Verify observer was notified of the command execution
        assertEquals(2, notificationService.getNotificationCount());
    }
}

