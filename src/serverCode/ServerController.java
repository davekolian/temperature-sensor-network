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
    Text houseTemp;

    static Text statHouseTemp;

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

    public ServerNode server;

    public ServerNode server2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerController.statLivingRoomTemp = livingRoomTemp;
        ServerController.statBedroomTemp = bedroomTemp;
        ServerController.statKitchenTemp = kitchenTemp;
        ServerController.statBathroomTemp = bathroomTemp;
        ServerController.statWcTemp = wcTemp;
        ServerController.statHouseTemp = houseTemp;


        server = new ServerNode(houseTemp.getText(), 3333);
        server2 = new ServerNode(houseTemp.getText(), 3334);


    }

    public static void receiveTempRooms(String result) {
        if (result.equals("init"))
            return;

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
    public void increaseTemp(Event event) throws IOException {
        System.out.println("inc");
        String newTemp = "" + (Double.parseDouble(houseTemp.getText()) + 1);
        houseTemp.setText(newTemp);
/*        server.stopMe();

        server = new ServerNode(newTemp);*/

    }

    @FXML
    public void decreaseTemp(Event event) throws IOException {
        System.out.println("dec");
        String newTemp = "" + (Double.parseDouble(houseTemp.getText()) - 1);
        houseTemp.setText(newTemp);
/*        server.stopMe();

        server = new ServerNode(newTemp, 3333);*/
    }

    public static String getHouseTempText() {
        System.out.println(statHouseTemp.getText());
        return statHouseTemp.getText();
    }

}

