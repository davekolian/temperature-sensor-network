package clientCode;

import java.util.ArrayList;

public class House {
    public static void main(String[] args) {
        ArrayList<Room> roomList = new ArrayList<>();

        SensorNode livingSensor = new SensorNode();
        Room livingRoom = new Room("livingRoom",livingSensor.getCurrentRoomTemp(), false, livingSensor);
        roomList.add(livingRoom);

        SensorNode kitchenSensor = new SensorNode();
        Room kitchenRoom = new Room("kitchenRoom",kitchenSensor.getCurrentRoomTemp(), false, kitchenSensor);
        roomList.add(kitchenRoom);

        SensorNode bedRoomSensor = new SensorNode();
        Room bedRoom = new Room("bedRoom", bedRoomSensor.getCurrentRoomTemp(), false, bedRoomSensor);
        roomList.add(bedRoom);

        SensorNode bathRoomSensor = new SensorNode();
        Room bathRoom = new Room("bathRoom", bathRoomSensor.getCurrentRoomTemp(), false, bathRoomSensor);
        roomList.add(bathRoom);

        try {
            SensorNode.sendData(roomList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

