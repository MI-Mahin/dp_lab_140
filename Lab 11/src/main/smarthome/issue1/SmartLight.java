package main.smarthome.issue1;

public class SmartLight implements Observer {
    private boolean isOn = false;

    @Override
    public void update(String key, Object value) {
        if (key.equals("motionDetected")) {
            isOn = (boolean) value;
            System.out.println("Smart Light turned " + (isOn ? "ON" : "OFF"));
        }
    }
}
