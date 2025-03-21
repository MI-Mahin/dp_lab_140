package org.example.service;

import org.example.command.*;
import org.example.model.Comment;
import org.example.model.Issue;
import org.example.model.Priority;
import org.example.model.Status;
import org.example.repository.IssueRepository;

import java.util.Collection;
import java.util.Optional;

public class IssueService {
    private final IssueRepository repository;
    private final CommandManager commandManager;

    public IssueService(IssueRepository repository, CommandManager commandManager) {
        this.repository = repository;
        this.commandManager = commandManager;
    }

    public Issue createIssue(String title, String description, Priority priority) {
        Issue issue = new Issue(title, description, priority);
        CreateIssueCommand command = new CreateIssueCommand(issue, repository);
        commandManager.executeCommand(command);
        return issue;
    }

    public void updateIssue(Issue issue) {
        UpdateIssueCommand command = new UpdateIssueCommand(issue, repository);
        commandManager.executeCommand(command);
    }

    public void changeIssueStatus(String issueId, Status newStatus) {
        ChangeIssueStatusCommand command = new ChangeIssueStatusCommand(issueId, newStatus, repository);
        commandManager.executeCommand(command);
    }

    public void addComment(String issueId, String content, String author) {
        Comment comment = new Comment(content, author);
        AddCommentCommand command = new AddCommentCommand(issueId, comment, repository);
        commandManager.executeCommand(command);
    }

    public Optional<Issue> getIssue(String id) {
        return repository.findById(id);
    }

    public Collection<Issue> getAllIssues() {
        return repository.findAll();
    }

    public void undo() {
        commandManager.undo();
    }

    public void redo() {
        commandManager.redo();
    }

    public boolean canUndo() {
        return commandManager.canUndo();
    }

    public boolean canRedo() {
        return commandManager.canRedo();
    }
}

