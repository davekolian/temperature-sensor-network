package serverCode;

import clientCode.HouseController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    Pane toHide;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerController.statLivingRoomTemp = livingRoomTemp;
        ServerController.statBedroomTemp = bedroomTemp;
        ServerController.statKitchenTemp = kitchenTemp;
        ServerController.statBathroomTemp = bathroomTemp;
        ServerController.statWcTemp = wcTemp;
        ServerController.statHouseTemp = houseTemp;


        ServerNode server = new ServerNode(houseTemp.getText(), 3333);
        ServerNode server2 = new ServerNode(houseTemp.getText(), 3334);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(compareHouseRoomTemp()){
                        toHide.setVisible(false);
                    } else {
                        toHide.setVisible(true);
                    }
                }
            }
        });
        th.start();


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
    }

    @FXML
    public void decreaseTemp(Event event) throws IOException {
        System.out.println("dec");
        String newTemp = "" + (Double.parseDouble(houseTemp.getText()) - 1);
        houseTemp.setText(newTemp);
    }

    public static String getHouseTempText() {
        System.out.println(statHouseTemp.getText());
        return statHouseTemp.getText();
    }

    public boolean compareHouseRoomTemp() {
        ArrayList<Text> textArrayList = new ArrayList<>();
        textArrayList.add(livingRoomTemp);
        textArrayList.add(kitchenTemp);
        textArrayList.add(bedroomTemp);
        textArrayList.add(bathroomTemp);
        textArrayList.add(wcTemp);

        for (Text text : textArrayList) {
            if(text.getText().equals("")) return false;
            if (Double.parseDouble(text.getText()) != Double.parseDouble(houseTemp.getText())) {
                return false;
            }
        }
        return true;
    }

}

