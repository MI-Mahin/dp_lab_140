package org.example;

import org.example.command.Command;
import org.example.command.CommandManager;
import org.example.model.Priority;
import org.example.observer.CommandObserver;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverRemovalTest {
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
    void removeObserver_ShouldStopNotifications() {
        // Create an observer
        AtomicInteger notificationCount = new AtomicInteger(0);
        CommandObserver observer = command -> notificationCount.incrementAndGet();

        commandManager.addObserver(observer);

        // Act: Execute commands before and after removing an observer
        issueService.createIssue("Issue 1", "Description", Priority.MEDIUM);
        assertEquals(1, notificationCount.get());

        commandManager.removeObserver(observer);

        issueService.createIssue("Issue 2", "Description", Priority.MEDIUM);

        // Assert: Verify observer stops receiving notifications after removal
        assertEquals(1, notificationCount.get()); // Count should still be 1
    }
}

