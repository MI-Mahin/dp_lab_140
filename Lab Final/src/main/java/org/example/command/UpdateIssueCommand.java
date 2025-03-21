package org.example.command;

import org.example.model.Issue;
import org.example.repository.IssueRepository;

public class UpdateIssueCommand extends AbstractCommand implements Command {
    private final Issue newState;
    private final Issue oldState;
    private final IssueRepository repository;

    public UpdateIssueCommand(Issue newState, IssueRepository repository) {
        super(CommandType.UPDATE);
        this.newState = newState;
        this.repository = repository;
        this.oldState = repository.findById(newState.getId())
                .orElseThrow(() -> new IllegalArgumentException("Issue not found: " + newState.getId()));
    }

    public void execute() {
        repository.save(newState);
    }

    public void undo() {
        repository.save(oldState);
    }

    public String getDescription() {
        return "Updated issue: " + newState.getTitle();
    }

    public Issue getNewState() {
        return newState;
    }
}