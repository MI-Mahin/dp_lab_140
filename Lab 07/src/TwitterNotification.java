public class TwitterNotification implements Notification {
    private String id;
    private String message;
    private boolean read;

    // Constructor, Getters, and Setters

    @Override
    public String getId() { return id; }

    @Override
    public String getMessage() { return message; }

    @Override
    public boolean isRead() { return read; }

    @Override
    public void markAsRead() { this.read = true; /* API call to mark as read */ }

    @Override
    public void markAsUnread() { this.read = false; /* API call to mark as unread */ }

    @Override
    public void delete() { /* API call to delete notification */ }
}
