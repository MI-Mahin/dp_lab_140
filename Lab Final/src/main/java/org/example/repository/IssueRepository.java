package org.example.repository;

import org.example.model.Issue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IssueRepository {
    private final Map<String, Issue> issues = new HashMap<>();

    public void save(Issue issue) {
        issues.put(issue.getId(), issue);
    }

    public Optional<Issue> findById(String id) {
        Issue issue = issues.get(id);
        return issue != null ? Optional.of(issue.clone()) : Optional.empty();
    }

    public void delete(String id) {
        issues.remove(id);
    }

    public Collection<Issue> findAll() {
        return issues.values().stream()
                .map(Issue::clone)
                .toList();
    }

    public boolean exists(String id) {
        return issues.containsKey(id);
    }
}

