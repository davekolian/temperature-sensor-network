package clientCode;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HouseController implements Initializable {
    @FXML
    Text livingRoomTemp;

    @FXML
    Text kitchenTemp;

    @FXML
    Text bathroomTemp;

    @FXML
    Text wcTemp;

    @FXML
    Text bedroomTemp;

    SensorNode livingSensor = new SensorNode();
    Room livingRoom = new Room("livingRoom", livingSensor.getCurrentRoomTemp(), false, livingSensor);

    SensorNode kitchenSensor = new SensorNode();
    Room kitchenRoom = new Room("kitchenRoom", kitchenSensor.getCurrentRoomTemp(), false, kitchenSensor);

    SensorNode bedroomSensor = new SensorNode();
    Room bedroom = new Room("bedroom", bedroomSensor.getCurrentRoomTemp(), false, bedroomSensor);

    SensorNode bathRoomSensor = new SensorNode();
    Room bathRoom = new Room("bathRoom", bathRoomSensor.getCurrentRoomTemp(), false, bathRoomSensor);

    SensorNode wcSensor = new SensorNode();
    Room wc = new Room("wc", wcSensor.getCurrentRoomTemp(), false, wcSensor);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Room> roomList = new ArrayList<>();

        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
        roomList.add(livingRoom);

        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
        roomList.add(kitchenRoom);

        bedroomTemp.setText("" + bedroom.getCurrentTemp());
        roomList.add(bedroom);

        bathroomTemp.setText("" + bathRoom.getCurrentTemp());
        roomList.add(bathRoom);

        wcTemp.setText("" + wc.getCurrentTemp());
        roomList.add(wc);


        Task task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                for (Room room : roomList) {
                    String result = SensorNode.connect("" + room);
                    System.out.println(result);
                    String[] data = result.split("#");
                    if (data[1].equals("cooling")) {
                        //call coolingFunc

                        try {
                            coolingFunction(data[2], data[0], 21.0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //call heatingFunc

                        heatingFunction(data[2], data[0], 21.0);

                    }

                }
                return null;
            }
        };
        new

                Thread(task).

                start();

    }

    public void coolingFunction(String roomName, String onoff, double dtemp) throws InterruptedException {
        System.out.println("hi");
        if (roomName.equals("livingRoom")) {
            if (onoff.equals("on")) {
                double delta = livingRoom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread.sleep(15000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() - 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread.sleep(10000);
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() - 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread.sleep(5000);
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() - 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            } else if (onoff.equals("off")) {
                //turn off animations
            }
        } else if(roomName.equals("kitchenRoom")){

        }
    }

    public void heatingFunction(String roomName, String onoff, double dtemp) {

    }

}
