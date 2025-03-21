package org.example.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Comment implements Cloneable {
    private final String id;
    private String content;
    private String author;
    private final LocalDateTime createdAt;

    public Comment(String content, String author) {
        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    private Comment(String id, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public Comment clone() {
        return new Comment(this.id, this.content, this.author, this.createdAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

