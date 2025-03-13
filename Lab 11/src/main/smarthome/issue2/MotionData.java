package main.smarthome.issue2;

import java.time.LocalDateTime;

public class MotionData {
    private final String location;
    private final LocalDateTime timestamp;

    public MotionData(String location, LocalDateTime timestamp) {
        this.location = location;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "MotionData{location='" + location + "', timestamp=" + timestamp + "}";
    }
}
