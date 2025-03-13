package main.smarthome.issue1;

import java.util.*;

public class StateManager {
    private final Map<String, Object> state = new HashMap<>();
    private final List<main.smarthome.issue1.Observer> observers = new ArrayList<>();

    public void registerObserver(main.smarthome.issue1.Observer observer) {
        observers.add(observer);
    }

    public void unregisterObserver(main.smarthome.issue1.Observer observer) {
        observers.remove(observer);
    }

    public void setState(String key, Object value) {
        state.put(key, value);
        notifyObservers(key, value);
    }

    private void notifyObservers(String key, Object value) {
        for (Observer observer : observers) {
            observer.update(key, value);
        }
    }

    public Object getState(String key) {
        return state.get(key);
    }
}
