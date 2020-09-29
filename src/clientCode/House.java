package clientCode;

import java.util.ArrayList;

public class House {
    public static void main(String[] args) {
        ArrayList<Room> roomList = new ArrayList<>();

        SensorNode livingSensor = new SensorNode();
        Room livingRoom = new Room("livingRoom",livingSensor.getCurrentRoomTemp(), false, livingSensor);

        SensorNode kitchenSensor = new SensorNode();
        Room kitchenRoom = new Room("kitchenRoom",kitchenSensor.getCurrentRoomTemp(), false, kitchenSensor);

        roomList.add(livingRoom);
        roomList.add(kitchenRoom);

        try {
            SensorNode.sendData(roomList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

