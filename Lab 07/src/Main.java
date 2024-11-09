import java.util.List;

public class Main {
    public static void main(String[] args) {
        NotificationAggregator aggregator = new NotificationAggregator();

        List<Notification> notifications = aggregator.getAllNotifications();
        notifications.forEach(notification -> System.out.println(notification.getMessage()));

        aggregator.markNotificationAsRead("Twitter", "12345");
        //aggregator.deleteNotification("Facebook", "67890");
    }
}
