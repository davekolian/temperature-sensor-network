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

    @FXML
    Text houseTempFake;

    public static boolean showScreen = false;
    public static boolean isSameTemp = false;

    public int counter = 0;
    public int totalCounter = 0;
    public int timeCounter = 3000;

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
                while (true) {
                    isSameTemp = compareHouseRoomTemp();
                    if (showScreen && !isSameTemp) {
                        toHide.setVisible(true);
                    } else if (isSameTemp) {
                        toHide.setVisible(false);
                    } else {
                        toHide.setVisible(false);
                    }
                }
            }
        });
        th.start();

        //Before client starts -> dont show screen
        //After client starts -> show screen till ALL same temp
        //When ALL same temp -> dont show
        //When button is pressed wait time till confirmed
    }

    public static void receiveTempRooms(String result) {
        showScreen = true;

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

    public static String getHouseTempText() {
        //System.out.println(statHouseTemp.getText());
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
            if (text.getText().equals("")) return false;
            if (Double.parseDouble(text.getText()) != Double.parseDouble(houseTemp.getText())) {
                return false;
            }
        }
        return true;
    }

    public String newTemp = "";

    @FXML
    public void changeTemp(Event event) {
        long startTime = System.currentTimeMillis();
        totalCounter++;
        if (((Button) event.getSource()).getText().equals("+")) {
            System.out.println("inc");
            newTemp = "" + (Double.parseDouble(houseTempFake.getText()) + 1);
            houseTempFake.setText(newTemp);
            counter++;
        } else if (((Button) event.getSource()).getText().equals("-")) {
            System.out.println("dec");
            newTemp = "" + (Double.parseDouble(houseTempFake.getText()) - 1);
            houseTempFake.setText(newTemp);
            counter--;
        }

        Thread t = new Thread(() -> {
                while (System.currentTimeMillis() - startTime <= timeCounter) {
                    //System.out.println("please wait for timer");
                    showScreen = false;
                }

                totalCounter = 0;
                System.out.println("timer done show new temp in real and put screen");

                houseTemp.setText(houseTempFake.getText());
                if (houseTemp.getText().equals(houseTempFake.getText()))
                    showScreen = true;

                System.out.println(System.currentTimeMillis() - startTime + " " + counter + " " + totalCounter);

        });
        t.start();
    }

}

