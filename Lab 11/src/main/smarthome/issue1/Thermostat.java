package main.smarthome.issue1;

public class Thermostat implements Observer {
    private double temperature;

    @Override
    public void update(String key, Object value) {
        if (key.equals("temperature")) {
            temperature = (double) value;
            System.out.println("Thermostat adjusted to " + temperature + "°C");
        }
    }
}
