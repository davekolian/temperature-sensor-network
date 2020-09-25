package code;

public class Room {
    private int volume;
    private double temperature;
    boolean isWindowOpen;
    boolean isWindowExist;
    boolean isFanOn;
    SensorNode sensorNode;
    HeatingSystem heatingSystem;
    CoolingSystem coolingSystem;

    public Room(int volume, double temperature) {
        this.volume = volume;
        this.temperature = temperature;
    }

    public Room(int volume, double temperature, boolean isWindowOpen, boolean isWindowExist) {
        this.volume = volume;
        this.temperature = temperature;
        this.isWindowOpen = isWindowOpen;
        this.isWindowExist = isWindowExist;
    }

    public Room(int volume, double temperature, boolean isWindowOpen, boolean isWindowExist, SensorNode sensorNode, HeatingSystem heatingSystem, CoolingSystem coolingSystem) {
        this.volume = volume;
        this.temperature = temperature;
        this.isWindowOpen = isWindowOpen;
        this.isWindowExist = isWindowExist;
        this.sensorNode = sensorNode;
        this.heatingSystem = heatingSystem;
        this.coolingSystem = coolingSystem;
    }

    public Room(int volume, double temperature, boolean isWindowOpen, boolean isWindowExist, SensorNode sensorNode, boolean isFanOn) {
        this.volume = volume;
        this.temperature = temperature;
        this.isWindowOpen = isWindowOpen;
        this.isWindowExist = isWindowExist;
        this.sensorNode = sensorNode;
        this.isFanOn = isFanOn;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isWindowOpen() {
        return isWindowOpen;
    }

    public void setWindowOpen(boolean windowOpen) {
        isWindowOpen = windowOpen;
    }

    public boolean isWindowExist() {
        return isWindowExist;
    }

    public void setWindowExist(boolean windowExist) {
        isWindowExist = windowExist;
    }

    public boolean isFanOn() {
        return isFanOn;
    }

    public void setFanOn(boolean fanOn) {
        isFanOn = fanOn;
    }

    public SensorNode getSensorNode() {
        return sensorNode;
    }

    public void setSensorNode(SensorNode sensorNode) {
        this.sensorNode = sensorNode;
    }

    public HeatingSystem getHeatingSystem() {
        return heatingSystem;
    }

    public void setHeatingSystem(HeatingSystem heatingSystem) {
        this.heatingSystem = heatingSystem;
    }

    public CoolingSystem getCoolingSystem() {
        return coolingSystem;
    }

    public void setCoolingSystem(CoolingSystem coolingSystem) {
        this.coolingSystem = coolingSystem;
    }
}
