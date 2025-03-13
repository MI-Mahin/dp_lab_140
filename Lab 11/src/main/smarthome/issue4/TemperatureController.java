package main.smarthome.issue4;

import main.smarthome.issue1.Observer;

public class TemperatureController implements Observer {
    private double currentTemperature;
    private double targetTemperature = 22.0; // Default target temperature (in °C)
    private final double hysteresis = 1.5; // Prevents rapid cycling
    private boolean heatingOn = false;
    private boolean coolingOn = false;

    @Override
    public void update(String key, Object value) {
        if (key.equals("currentTemperature")) {
            this.currentTemperature = (double) value;
            controlTemperature();
        } else if (key.equals("targetTemperature")) {
            this.targetTemperature = (double) value;
            controlTemperature();
        }
    }

    private void controlTemperature() {
        if (currentTemperature < targetTemperature - hysteresis) {
            heatingOn = true;
            coolingOn = false;
        } else if (currentTemperature > targetTemperature + hysteresis) {
            heatingOn = false;
            coolingOn = true;
        } else {
            heatingOn = false;
            coolingOn = false;
        }

        System.out.println("Current Temperature: " + currentTemperature + "°C | Target: " + targetTemperature + "°C");
        System.out.println("Heating: " + (heatingOn ? "ON" : "OFF") + " | Cooling: " + (coolingOn ? "ON" : "OFF"));
    }
}
