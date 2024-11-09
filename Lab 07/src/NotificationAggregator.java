import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationAggregator {
    private Map<String, NotificationService> notificationServices;

    public NotificationAggregator() {
        notificationServices = new HashMap<>();
        notificationServices.put("Twitter", new TwitterNotificationService());
        //notificationServices.put("Facebook", new FacebookNotificationService());
    }

    public List<Notification> getAllNotifications() {
        List<Notification> allNotifications = new ArrayList<>();
        for (NotificationService service : notificationServices.values()) {
            allNotifications.addAll(service.fetchNotifications());
        }
        return allNotifications;
    }

    public Notification getNotification(String platform, String id) {
        NotificationService service = notificationServices.get(platform);
        return service != null ? service.getNotificationById(id) : null;
    }

    public void markNotificationAsRead(String platform, String id) {
        Notification notification = getNotification(platform, id);
        if (notification != null) {
            notification.markAsRead();
        }
    }

    public void deleteNotification(String platform, String id) {
        Notification notification = getNotification(platform, id);
        if (notification != null) {
            notification.delete();
        }
    }
}
