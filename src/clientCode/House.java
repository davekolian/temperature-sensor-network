package clientCode;

public class House {
    public static void main(String[] args){
       // SensorNode livingSensor = new SensorNode();
        //Room livingRoom = new Room(livingSensor.doEverything("livingRoom") , false, livingSensor);

        SensorNode kitchenSensor = new SensorNode();
        Room kitchenRoom = new Room(kitchenSensor.doEverything("kitchenRoom") , false, kitchenSensor);

    }



}

