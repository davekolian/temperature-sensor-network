package clientCode;

public class Room {
    private double currentTemp;
    private boolean isWindowOpen;
    private SensorNode sensor;

    public Room(double currentTemp, boolean isWindowOpen, SensorNode sensor) {
        this.currentTemp = currentTemp;
        this.isWindowOpen = isWindowOpen;
        this.sensor = sensor;
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
}
