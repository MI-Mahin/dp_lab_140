public interface Notification {
    String getId();
    String getMessage();
    boolean isRead();
    void markAsRead();
    void markAsUnread();
    void delete();
}
