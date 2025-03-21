package org.example.model;

import java.time.LocalDateTime;
import java.util.*;

public class Issue implements Cloneable {
    private final String id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private final LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private String assignedTo;
    private Set<String> tags;
    private List<Comment> comments;

    public Issue(String title, String description, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = Status.OPEN; // Default status
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = this.creationDate;
        this.tags = new HashSet<>();
        this.comments = new ArrayList<>();
    }

    private Issue(String id, String title, String description, Priority priority, Status status,
                  LocalDateTime creationDate, LocalDateTime lastModifiedDate, String assignedTo,
                  Set<String> tags, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.assignedTo = assignedTo;
        this.tags = tags;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        updateLastModifiedDate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateLastModifiedDate();
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
        updateLastModifiedDate();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        updateLastModifiedDate();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    private void updateLastModifiedDate() {
        this.lastModifiedDate = LocalDateTime.now();
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        updateLastModifiedDate();
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    public void addTag(String tag) {
        this.tags.add(tag);
        updateLastModifiedDate();
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
        updateLastModifiedDate();
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        updateLastModifiedDate();
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
        updateLastModifiedDate();
    }

    @Override
    public Issue clone() {
        Set<String> clonedTags = new HashSet<>(this.tags);
        List<Comment> clonedComments = new ArrayList<>();
        for (Comment comment : this.comments) {
            clonedComments.add(comment.clone());
        }

        return new Issue(
                this.id,
                this.title,
                this.description,
                this.priority,
                this.status,
                this.creationDate,
                this.lastModifiedDate,
                this.assignedTo,
                clonedTags,
                clonedComments
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", assignedTo='" + assignedTo + '\'' +
                ", tags=" + tags +
                ", comments=" + comments +
                '}';
    }
}

