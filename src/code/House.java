package code;

public class House {
    public static void main(String[] args) {
        Room livingRoom = new Room(60, 24.0, false, true, new SensorNode(), new HeatingSystem(), new CoolingSystem());
        Room bedRoom = new Room(67, 21.1, false, true);
        Room kitchenRoom = new Room(53, 23.0, false, true);
        Room bathRoom = new Room(20, 22.0, true, true, new SensorNode(),false);

        System.out.println("current temp of living: " + livingRoom.getSensorNode().getCurrentRoomTemp(livingRoom));
        checkTemperature(livingRoom, livingRoom.getTemperature(), 22.0);



    }

    public static void checkTemperature(Room room, double temp, double desiredTemp){
        if(temp > desiredTemp) {
            //turn on cooling
            room.setTemperature(22.0);
        }
        else if (temp < desiredTemp) {
            //turn on heating
        }
        else System.out.println("GG!");
    }

    public static void turnOnCooling(Room room, CoolingSystem coolingSystem){
        
    }
}
