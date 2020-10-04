package clientCode;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Room> roomList = new ArrayList<>();

        SensorNode livingSensor = new SensorNode();
        Room livingRoom = new Room("livingRoom", livingSensor.getCurrentRoomTemp(), false, livingSensor);
        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
        roomList.add(livingRoom);

        SensorNode kitchenSensor = new SensorNode();
        Room kitchenRoom = new Room("kitchenRoom", kitchenSensor.getCurrentRoomTemp(), false, kitchenSensor);
        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
        roomList.add(kitchenRoom);

        SensorNode bedroomSensor = new SensorNode();
        Room bedroom = new Room("bedroom", bedroomSensor.getCurrentRoomTemp(), false, bedroomSensor);
        bedroomTemp.setText("" + bedroom.getCurrentTemp());
        roomList.add(bedroom);

        SensorNode bathRoomSensor = new SensorNode();
        Room bathRoom = new Room("bathRoom", bathRoomSensor.getCurrentRoomTemp(), false, bathRoomSensor);
        bathroomTemp.setText("" + bathRoom.getCurrentTemp());
        roomList.add(bathRoom);

        SensorNode wcSensor = new SensorNode();
        Room wc = new Room("wc", wcSensor.getCurrentRoomTemp(), false, wcSensor);
        wcTemp.setText("" + wc.getCurrentTemp());
        roomList.add(wc);

        try {
            Iterator<Room> iterator = roomList.iterator();
            while (iterator.hasNext()) {
                SensorNode.connect("" + iterator.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
