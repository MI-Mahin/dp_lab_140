package org.example;

import org.example.command.CommandManager;
import org.example.model.Issue;
import org.example.model.Priority;
import org.example.repository.IssueRepository;
import org.example.service.IssueService;
import org.example.service.LogService;
import org.example.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddCommentTest {
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
    void addComment_ShouldAddCommentToIssue() {
        // Act: Add a comment to an existing issue
        String commentContent = "This is a test comment";
        String commentAuthor = "Test User";
        issueService.addComment(testIssue.getId(), commentContent, commentAuthor);

        // Assert: Verify comment is added with correct content and author
        Optional<Issue> retrievedIssue = issueService.getIssue(testIssue.getId());

        assertTrue(retrievedIssue.isPresent());
        assertEquals(1, retrievedIssue.get().getComments().size());
        assertEquals(commentContent, retrievedIssue.get().getComments().get(0).getContent());
        assertEquals(commentAuthor, retrievedIssue.get().getComments().get(0).getAuthor());
    }
}

