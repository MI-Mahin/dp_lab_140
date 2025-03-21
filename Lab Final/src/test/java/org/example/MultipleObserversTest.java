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

public class MultipleObserversTest {
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
    void multipleObservers_ShouldAllBeNotified() {
        // Create multiple observers
        AtomicInteger observer1Count = new AtomicInteger(0);
        AtomicInteger observer2Count = new AtomicInteger(0);

        CommandObserver observer1 = command -> observer1Count.incrementAndGet();
        CommandObserver observer2 = command -> observer2Count.incrementAndGet();

        commandManager.addObserver(observer1);
        commandManager.addObserver(observer2);

        // Act: Execute command with multiple registered observers
        issueService.createIssue("Test Issue", "Description", Priority.MEDIUM);

        // Assert: Verify all observers were notified exactly once
        assertEquals(1, observer1Count.get());
        assertEquals(1, observer2Count.get());
    }
}

