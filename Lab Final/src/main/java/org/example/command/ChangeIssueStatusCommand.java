package org.example.command;

import org.example.model.Issue;
import org.example.model.Status;
import org.example.repository.IssueRepository;

public class ChangeIssueStatusCommand extends AbstractCommand implements Command {
    private final String issueId;
    private final Status newStatus;
    private final Status oldStatus;
    private final IssueRepository repository;

    public ChangeIssueStatusCommand(String issueId, Status newStatus, IssueRepository repository) {
        super(CommandType.UPDATE);
        this.issueId = issueId;
        this.newStatus = newStatus;
        this.repository = repository;

        Issue issue = repository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found: " + issueId));
        this.oldStatus = issue.getStatus();
    }

    public void execute() {
        repository.findById(issueId).ifPresent(issue -> {
            issue.setStatus(newStatus);
            repository.save(issue);
        });
    }

    public void undo() {
        repository.findById(issueId).ifPresent(issue -> {
            issue.setStatus(oldStatus);
            repository.save(issue);
        });
    }

    public String getDescription() {
        return "Changed issue status from " + oldStatus + " to " + newStatus + " for issue: " + issueId;
    }

    public String getIssueId() {
        return issueId;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public Status getOldStatus() {
        return oldStatus;
    }
}

