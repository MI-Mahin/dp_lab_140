package org.example.observer;

import org.example.command.Command;
import org.example.command.CommandType;
import org.example.command.CreateIssueCommand;
import org.example.command.ChangeIssueStatusCommand;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements CommandObserver {
    private final List<String> notifications = new ArrayList<>();

    @Override
    public void update(Command command) {
        if (command instanceof CreateIssueCommand) {
            CreateIssueCommand createCommand = (CreateIssueCommand) command;
            String notification = "New issue created: " + createCommand.getIssue().getTitle();
            sendNotification(notification);
        } else if (command instanceof ChangeIssueStatusCommand) {
            ChangeIssueStatusCommand statusCommand = (ChangeIssueStatusCommand) command;
            String notification = "Issue status changed from " + statusCommand.getOldStatus() +
                    " to " + statusCommand.getNewStatus() +
                    " for issue: " + statusCommand.getIssueId();
            sendNotification(notification);
        }
    }

    private void sendNotification(String message) {
        // In a real system, this would send emails, push notifications, etc.
        System.out.println("NOTIFICATION: " + message);
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return new ArrayList<>(notifications);
    }

    public int getNotificationCount() {
        return notifications.size();
    }
}

