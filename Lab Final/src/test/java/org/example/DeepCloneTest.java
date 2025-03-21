package org.example;

import org.example.model.Comment;
import org.example.model.Issue;
import org.example.model.Priority;
import org.example.model.Status;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DeepCloneTest {

    @Test
    void issue_Clone_ShouldCreateCompleteDeepCopy() {
        // Act: Create and clone an issue with all properties set
        Issue original = new Issue("Test Issue", "Description", Priority.HIGH);
        original.setStatus(Status.IN_PROGRESS);
        original.setAssignedTo("User1");
        original.addTag("bug");
        original.addTag("critical");

        Comment comment = new Comment("Test comment", "User2");
        original.addComment(comment);

        Issue clone = original.clone();

        // Assert: Verify clone is independent copy with identical values
        assertEquals(original.getId(), clone.getId());
        assertEquals(original.getTitle(), clone.getTitle());
        assertEquals(original.getDescription(), clone.getDescription());
        assertEquals(original.getPriority(), clone.getPriority());
        assertEquals(original.getStatus(), clone.getStatus());
        assertEquals(original.getCreationDate(), clone.getCreationDate());
        assertEquals(original.getLastModifiedDate(), clone.getLastModifiedDate());
        assertEquals(original.getAssignedTo(), clone.getAssignedTo());
        assertEquals(original.getTags(), clone.getTags());
        assertEquals(original.getComments().size(), clone.getComments().size());

        // Verify deep copy by modifying the clone and checking the original remains unchanged
        clone.setTitle("Modified Title");
        clone.setStatus(Status.RESOLVED);
        clone.addTag("new-tag");
        clone.getComments().get(0).setContent("Modified comment");

        assertNotEquals(original.getTitle(), clone.getTitle());
        assertNotEquals(original.getStatus(), clone.getStatus());
        assertNotEquals(original.getTags(), clone.getTags());

        // Original comment content should remain unchanged
        assertEquals("Test comment", original.getComments().get(0).getContent());
    }
}

