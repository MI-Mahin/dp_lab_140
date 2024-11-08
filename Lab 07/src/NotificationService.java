import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications();
    void markAsRead(Notification notification);
    void markAsUnread(Notification notification);
    void deleteNotification(Notification notification);
}
