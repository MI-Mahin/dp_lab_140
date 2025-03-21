package org.example.command;

import org.example.model.Comment;
import org.example.model.Issue;
import org.example.repository.IssueRepository;

public class AddCommentCommand extends AbstractCommand implements Command {
    private final String issueId;
    private final Comment comment;
    private final IssueRepository repository;

    public AddCommentCommand(String issueId, Comment comment, IssueRepository repository) {
        super(CommandType.UPDATE);
        this.issueId = issueId;
        this.comment = comment;
        this.repository = repository;
    }

    public void execute() {
        repository.findById(issueId).ifPresent(issue -> {
            issue.addComment(comment);
            repository.save(issue);
        });
    }

    public void undo() {
        repository.findById(issueId).ifPresent(issue -> {
            issue.removeComment(comment);
            repository.save(issue);
        });
    }

    public String getDescription() {
        return "Added comment by " + comment.getAuthor() + " to issue: " + issueId;
    }

    public String getIssueId() {
        return issueId;
    }

    public Comment getComment() {
        return comment;
    }
}

