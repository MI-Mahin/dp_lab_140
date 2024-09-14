import java.util.ArrayList;
import java.util.List;

class NotificationService {
    private List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String event) {
        for (NotificationObserver observer : observers) {
            observer.update(event);
        }
    }
}