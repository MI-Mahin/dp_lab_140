interface NotificationObserver {
    void update(String event);
}

class Rider implements NotificationObserver {
    private String name;

    public Rider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String event) {
        System.out.println("Rider " + name + " notified: " + event);
    }
}

class Driver implements NotificationObserver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String event) {
        System.out.println("Driver " + name + " notified: " + event);
    }
}
