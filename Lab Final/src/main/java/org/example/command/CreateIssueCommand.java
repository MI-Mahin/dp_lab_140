package org.example.command;

import org.example.model.Issue;
import org.example.repository.IssueRepository;

public class CreateIssueCommand extends AbstractCommand implements Command {
    private final Issue issue;
    private final IssueRepository repository;

    public CreateIssueCommand(Issue issue, IssueRepository repository) {
        super(CommandType.CREATE);
        this.issue = issue;
        this.repository = repository;
    }

    public void execute() {
        repository.save(issue);
    }

    public void undo() {
        repository.delete(issue.getId());
    }

    public String getDescription() {
        return "Created issue: " + issue.getTitle();
    }

    public Issue getIssue() {
        return issue;
    }
}

