package clientCode;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    ImageView livingRoomHotWave;

    @FXML
    ImageView livingRoomColdWave;

    @FXML
    ImageView kitchenHotWave;

    @FXML
    ImageView kitchenColdWave;

    @FXML
    ImageView bathroomHotWave;

    @FXML
    ImageView bathroomFan;

    @FXML
    ImageView wcHotWave;

    @FXML
    ImageView wcFan;

    @FXML
    ImageView bedroomHotWave;

    @FXML
    ImageView bedroomColdWave;

    SensorNode livingSensor = new SensorNode();
    Room livingRoom = new Room("livingRoom", livingSensor.getCurrentRoomTemp(), false, livingSensor);

    SensorNode kitchenSensor = new SensorNode();
    Room kitchenRoom = new Room("kitchenRoom", kitchenSensor.getCurrentRoomTemp(), false, kitchenSensor);

    SensorNode bedroomSensor = new SensorNode();
    Room bedroom = new Room("bedroom", bedroomSensor.getCurrentRoomTemp(), false, bedroomSensor);

    SensorNode bathroomSensor = new SensorNode();
    Room bathroom = new Room("bathroom", bathroomSensor.getCurrentRoomTemp(), false, bathroomSensor);

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

        bathroomTemp.setText("" + bathroom.getCurrentTemp());
        roomList.add(bathroom);

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
                        System.out.println("cool");
                        //call coolingFunc
                        try {
                            coolingFunction(data[2], data[0], 21.0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (data[1].equals("heating")) {
                        System.out.println("heat");
                        //call heatingFunc

                        heatingFunction(data[2], data[0], 21.0);
                    }

                }
                return null;
            }
        };
        new Thread(task).start();

    }

    public void coolingFunction(String roomName, String onoff, double dtemp) throws InterruptedException {
        System.out.println("hi");
        if (roomName.equals("livingRoom")) {
            if (onoff.equals("on")) {
                double delta = livingRoom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(15000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() - 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(10000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() - 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(5000);
                        System.out.println("change in 1");
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
        } else if (roomName.equals("kitchenRoom")) {
            if (onoff.equals("on")) {
                double delta = kitchenRoom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (kitchenRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(15000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() - 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (kitchenRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(10000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() - 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Cooling System!");
                    while (kitchenRoom.getCurrentTemp() != dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(5000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() - 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            } else if (onoff.equals("off")) {
                //turn off animations
            }
        } else if (roomName.equals("bedroom")) {
            if (onoff.equals("on")) {
                double delta = bedroom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() != dtemp) {
                        Thread.sleep(15000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() != dtemp) {
                        Thread.sleep(10000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() != dtemp) {
                        Thread.sleep(5000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            } else if (onoff.equals("off")) {
                //turn off animations
            }
        } else if (roomName.equals("bathroom")) {
            if (onoff.equals("on")) {
                System.out.println("Turning on the Cooling System!");
                while (bathroom.getCurrentTemp() != dtemp) {
                    Thread.sleep(30000);
                    System.out.println("change in 1");
                    bathroom.setCurrentTemp(bathroom.getCurrentTemp() - 1);
                    bathroomTemp.setText("" + bathroom.getCurrentTemp());
                }
                // animation for 1 fan


            } else if (onoff.equals("off")) {
                //turn off animations
            }
        } else if (roomName.equals("wc")) {
            if (onoff.equals("on")) {

                System.out.println("Turning on the Cooling System!");
                while (wc.getCurrentTemp() != dtemp) {
                    Thread.sleep(30000);
                    System.out.println("change in 1");
                    wc.setCurrentTemp(wc.getCurrentTemp() - 1);
                    wcTemp.setText("" + wc.getCurrentTemp());
                }
                // animation for 1 fan
            }
        } else if (onoff.equals("off")) {
            //turn off animations
        }
    }

    public void coldWaveAnimation(ImageView image, int numOfFans) throws InterruptedException {
        String filename = "Web 1920 – 1222";
        long startTime = System.currentTimeMillis();
        int time = 0;
        switch (numOfFans) {
            case 1:
                time = 15000;
                break;
            case 2:
                time = 10000;
                break;
            case 3:
                time = 5000;
                break;
        }
        while (System.currentTimeMillis() - startTime <= time) {
            if (numOfFans == 1) {
                image.setImage(null);
                Thread.sleep(1000);
                Image img = new Image(getClass().getResource(filename + "2.png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
            } else if (numOfFans == 2) {
                Image img = new Image(getClass().getResource(filename + "2.png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
                img = new Image(getClass().getResource(filename + "1.png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
            } else {
                Image img = new Image(getClass().getResource(filename + "2.png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
                img = new Image(getClass().getResource(filename + "1.png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
                img = new Image(getClass().getResource(filename + ".png").toExternalForm());
                image.setImage(img);
                Thread.sleep(1000);
            }
        }

        image.setImage(null);
    }


    public void heatingFunction(String roomName, String onoff, double dtemp) {

    }

}
