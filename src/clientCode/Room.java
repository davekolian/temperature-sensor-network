package clientCode;

public class Room {
    private String name;
    private double currentTemp;
    private boolean isWindowOpen;
    private SensorNode sensor;

    public Room(String name, double currentTemp, boolean isWindowOpen, SensorNode sensor) {
        this.name = name;
        this.currentTemp = currentTemp;
        this.isWindowOpen = isWindowOpen;
        this.sensor = sensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public boolean isWindowOpen() {
        return isWindowOpen;
    }

    public void setWindowOpen(boolean windowOpen) {
        isWindowOpen = windowOpen;
    }

    public SensorNode getSensor() {
        return sensor;
    }

    public void setSensor(SensorNode sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return getName() + "#" + getCurrentTemp() + "#" + isWindowOpen();
    }
}
