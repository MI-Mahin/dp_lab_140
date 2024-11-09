import java.util.ArrayList;
import java.util.List;

public class TwitterNotificationService implements NotificationService {

    @Override
    public List<Notification> fetchNotifications() {
        return new ArrayList<>();
    }

    @Override
    public Notification getNotificationById(String id) {
        return new TwitterNotification();
    }
}


