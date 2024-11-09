import java.util.List;

public interface NotificationService {
    List<Notification> fetchNotifications();
    Notification getNotificationById(String id);
}
