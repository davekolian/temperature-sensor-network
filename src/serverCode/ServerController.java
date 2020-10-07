package serverCode;

import clientCode.HouseController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    @FXML
    Text livingRoomTemp;

    static Text statLivingRoomTemp;

    @FXML
    Text bedroomTemp;

    static Text statBedroomTemp;

    @FXML
    Text kitchenTemp;

    static Text statKitchenTemp;

    @FXML
    Text bathroomTemp;

    static Text statBathroomTemp;

    @FXML
    Text wcTemp;

    static Text statWcTemp;

    @FXML
    Button houseHighBtn;

    @FXML
    Button houseLowBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerController.statLivingRoomTemp = livingRoomTemp;
        ServerController.statBedroomTemp = bedroomTemp;
        ServerController.statKitchenTemp = kitchenTemp;
        ServerController.statBathroomTemp = bathroomTemp;
        ServerController.statWcTemp = wcTemp;

        String[] data = new String[5];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerNode.start(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }

    public static void receiveTempRooms(String result) {

        String[] data = result.split("#");
        if (data[0].equals("livingRoom")) {
            statLivingRoomTemp.setText("" + data[1]);
        } else if (data[0].equals("kitchenRoom")) {
            statKitchenTemp.setText("" + data[1]);
        } else if (data[0].equals("bedroom")) {
            statBedroomTemp.setText("" + data[1]);
        } else if (data[0].equals("bathroom")) {
            statBathroomTemp.setText("" + data[1]);
        } else if (data[0].equals("wc")) {
            statWcTemp.setText("" + data[1]);
        }
    }

    @FXML
    public void increaseTemp(Event event){
        System.out.println("inc");
    }

    @FXML
    public void decreaseTemp(Event event){
        System.out.println("dec");
    }


}
