package code;

public class SensorNode {
    private double currentRoomTemp;

    public double getCurrentRoomTemp(Room room){
        return room.getTemperature();
    }
}
