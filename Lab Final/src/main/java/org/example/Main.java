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

public class Main {
    public static void main(String[] args) {
        // Initialize services
        IssueRepository repository = new IssueRepository();
        LogService logService = new LogService();
        StatisticsService statisticsService = new StatisticsService();
        CommandManager commandManager = new CommandManager(logService, statisticsService);
        IssueService issueService = new IssueService(repository, commandManager);

        // Add notification observer
        NotificationService notificationService = new NotificationService();
        commandManager.addObserver(notificationService);

        // Demo usage
        System.out.println("=== Task Manager Demo ===");

        // Create issues
        Issue bug = issueService.createIssue("Login Bug", "Users cannot login with correct credentials", Priority.HIGH);
        Issue feature = issueService.createIssue("Dark Mode", "Implement dark mode for the application", Priority.MEDIUM);

        System.out.println("\n=== Created Issues ===");
        repository.findAll().forEach(System.out::println);

        // Update issue status
        issueService.changeIssueStatus(bug.getId(), Status.IN_PROGRESS);
        System.out.println("\n=== After Status Change ===");
        repository.findById(bug.getId()).ifPresent(System.out::println);

        // Add comments
        issueService.addComment(bug.getId(), "I'm working on this issue", "Mahin");
        issueService.addComment(bug.getId(), "Found the root cause", "Mahin");

        System.out.println("\n=== After Adding Comments ===");
        repository.findById(bug.getId()).ifPresent(issue -> {
            System.out.println(issue);
            System.out.println("Comments:");
            issue.getComments().forEach(System.out::println);
        });

        // Demonstrate undo/redo
        System.out.println("\n=== Undo Last Action ===");
        issueService.undo(); // Undo adding the second comment
        repository.findById(bug.getId()).ifPresent(issue -> {
            System.out.println(issue);
            System.out.println("Comments after undo:");
            issue.getComments().forEach(System.out::println);
        });

        System.out.println("\n=== Redo Last Action ===");
        issueService.redo(); // Redo adding the second comment
        repository.findById(bug.getId()).ifPresent(issue -> {
            System.out.println(issue);
            System.out.println("Comments after redo:");
            issue.getComments().forEach(System.out::println);
        });

        // Show logs
        System.out.println("\n=== Command Logs ===");
        logService.getLogs().forEach(System.out::println);

        // Show statistics
        System.out.println("\n=== Statistics ===");
        System.out.println("Total commands executed: " + statisticsService.getTotalCommandCount());
        System.out.println("Issues created: " + statisticsService.getIssuesCreated());

        // Show notifications
        System.out.println("\n=== Notifications Sent ===");
        notificationService.getNotifications().forEach(System.out::println);
    }
}

