package main.smarthome.issue2;

import main.smarthome.issue1.Observer;

public class SmartLight implements Observer {
    private boolean isOn = false;

    @Override
    public void update(String key, Object value) {
        if (key.equals("motionDetected")) {
            isOn = (boolean) value;
            System.out.println("Smart Light turned " + (isOn ? "ON" : "OFF"));
        } else if (key.equals("motionDetails")) {
            System.out.println("Light responding to motion at " + value);
        }
    }
}
