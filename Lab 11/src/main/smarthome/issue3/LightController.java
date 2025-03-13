package main.smarthome.issue3;

import main.smarthome.issue1.Observer;

public class LightController implements Observer {
    private int brightness = 100; // Default brightness level (0-100)
    private boolean isOn = false;
    private String currentScene = "Normal"; // Default scene

    @Override
    public void update(String key, Object value) {
        switch (key) {
            case "ambientLight":
                adjustBrightness((int) value);
                break;
            case "motionDetected":
                updateLightState((boolean) value);
                break;
            case "scene":
                setScene((String) value);
                break;
        }
    }

    private void adjustBrightness(int ambientLightLevel) {
        if (ambientLightLevel < 30) { // Low light condition
            brightness = 100;
        } else if (ambientLightLevel < 60) {
            brightness = 60;
        } else {
            brightness = 30; // Dim in bright conditions
        }
        System.out.println("Light brightness adjusted to " + brightness + "%");
    }

    private void updateLightState(boolean motionDetected) {
        isOn = motionDetected;
        System.out.println("Light turned " + (isOn ? "ON" : "OFF"));
    }

    public void setScene(String scene) {
        this.currentScene = scene;
        switch (scene.toLowerCase()) {
            case "reading":
                brightness = 80;
                break;
            case "movie":
                brightness = 20;
                break;
            case "relaxing":
                brightness = 50;
                break;
            default:
                brightness = 100;
        }
        System.out.println("Scene set to " + scene + " | Brightness: " + brightness + "%");
    }
}

