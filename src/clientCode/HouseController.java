package clientCode;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
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

    public double dtemp = 0.0;


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

        String res = null;
        try {
            res = SensorNode.connect("init", 3333);

            String[] tempAr = res.split("#");
            dtemp = Double.parseDouble(tempAr[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread startNew = new Thread(new Runnable() {
            @Override
            public void run() {
                checkingHouseTemp(roomList);
            }
        });
        startNew.start();


        Thread newClient = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String res = SensorNode.connect("init", 3334);
                        String[] data = res.split("#");
                        if (Double.parseDouble(data[1]) != dtemp) {
                            dtemp = Double.parseDouble(data[1]);
                            checkingHouseTemp(roomList);
                            System.out.println("NS: " + res);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        newClient.start();

    }


    public void coolingFunction(String roomName, String onoff) throws InterruptedException {
        if (roomName.equals("livingRoom")) {
            if (onoff.equals("on")) {
                double delta = livingRoom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (livingRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, livingRoom.getCurrentTemp() - dtemp, 1);
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
                    while (livingRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, livingRoom.getCurrentTemp() - dtemp, 2);
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
                    while (livingRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(livingRoomColdWave, livingRoom.getCurrentTemp() - dtemp, 3);
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


            }
        } else if (roomName.equals("kitchenRoom")) {
            if (onoff.equals("on")) {
                double delta = kitchenRoom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (kitchenRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, kitchenRoom.getCurrentTemp() - dtemp, 1);
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
                    while (kitchenRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, kitchenRoom.getCurrentTemp() - dtemp, 2);
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
                    while (kitchenRoom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(kitchenColdWave, kitchenRoom.getCurrentTemp() - dtemp, 3);
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


            }
        } else if (roomName.equals("bedroom")) {
            if (onoff.equals("on")) {
                double delta = bedroom.getCurrentTemp() - dtemp;

                if (delta <= 1.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(bedroomColdWave, bedroom.getCurrentTemp() - dtemp, 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(15000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(bedroomColdWave, bedroom.getCurrentTemp() - dtemp, 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(10000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Cooling System!");
                    while (bedroom.getCurrentTemp() > dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    coldWaveAnimation(bedroomColdWave, bedroom.getCurrentTemp() - dtemp, 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(5000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() - 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            }
        } else if (roomName.equals("bathroom")) {
            if (onoff.equals("on")) {
                System.out.println("Turning on the Cooling System!");
                while (bathroom.getCurrentTemp() > dtemp) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                coldWaveAnimation(bathroomFan, bathroom.getCurrentTemp() - dtemp, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                    Thread.sleep(30000);
                    System.out.println("change in 1");
                    bathroom.setCurrentTemp(bathroom.getCurrentTemp() - 1);
                    bathroomTemp.setText("" + bathroom.getCurrentTemp());
                }
                // animation for 1 fan


            }
        } else if (roomName.equals("wc")) {
            if (onoff.equals("on")) {

                System.out.println("Turning on the Cooling System!");
                while (wc.getCurrentTemp() > dtemp) {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                coldWaveAnimation(wcFan, wc.getCurrentTemp() - dtemp, 0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    t.start();
                    Thread.sleep(30000);
                    System.out.println("change in 1");
                    wc.setCurrentTemp(wc.getCurrentTemp() - 1);
                    wcTemp.setText("" + wc.getCurrentTemp());
                }
                // animation for 1 fan
            }
        }
    }

    public void coldWaveAnimation(ImageView image, double delta, int numOfFans) throws InterruptedException {
        String filename = "Web 1920 – 1222";
        long startTime = System.currentTimeMillis();
        int time = 0;
        switch (numOfFans) {
            case 0:
                time = 30000;
                break;
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
            if (numOfFans == 0) {
                image.setRotate(image.getRotate() + 15);
                Thread.sleep(60);
            } else if (numOfFans == 1) {
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

        if (numOfFans != 0 && delta <= 1.0) image.setImage(null);
        else if (numOfFans == 0 && delta <= 1.0) image.setRotate(0);
    }


    public void heatingFunction(String roomName, String onoff) throws InterruptedException {
        if (roomName.equals("livingRoom")) {
            if (onoff.equals("on")) {
                double delta = dtemp - livingRoom.getCurrentTemp();

                if (delta <= 1.5) {
                    System.out.println("Turning on the Heating System!");
                    while (livingRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(livingRoomHotWave, dtemp - livingRoom.getCurrentTemp(), 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(17000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() + 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Heating System!");
                    while (livingRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(livingRoomHotWave, dtemp - livingRoom.getCurrentTemp(), 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(12000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() + 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Heating System!");
                    while (livingRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(livingRoomHotWave, dtemp - livingRoom.getCurrentTemp(), 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(7000);
                        System.out.println("change in 1");
                        livingRoom.setCurrentTemp(livingRoom.getCurrentTemp() + 1);
                        livingRoomTemp.setText("" + livingRoom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            }
        } else if (roomName.equals("kitchenRoom")) {
            if (onoff.equals("on")) {
                double delta = dtemp - kitchenRoom.getCurrentTemp();

                if (delta <= 1.5) {
                    System.out.println("Turning on the Heating System!");
                    while (kitchenRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(kitchenHotWave, dtemp - kitchenRoom.getCurrentTemp(), 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(17000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() + 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Heating System!");
                    while (kitchenRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(kitchenHotWave, dtemp - kitchenRoom.getCurrentTemp(), 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(12000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() + 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Heating System!");
                    while (kitchenRoom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(kitchenHotWave, dtemp - kitchenRoom.getCurrentTemp(), 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(7000);
                        System.out.println("change in 1");
                        kitchenRoom.setCurrentTemp(kitchenRoom.getCurrentTemp() + 1);
                        kitchenTemp.setText("" + kitchenRoom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            }
        } else if (roomName.equals("bedroom")) {
            if (onoff.equals("on")) {
                double delta = dtemp - bedroom.getCurrentTemp();

                if (delta <= 1.5) {
                    System.out.println("Turning on the Heating System!");
                    while (bedroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bedroomHotWave, dtemp - bedroom.getCurrentTemp(), 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(17000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() + 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Heating System!");
                    while (bedroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bedroomHotWave, dtemp - bedroom.getCurrentTemp(), 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(12000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() + 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Heating System!");
                    while (bedroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bedroomHotWave, dtemp - bedroom.getCurrentTemp(), 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(7000);
                        System.out.println("change in 1");
                        bedroom.setCurrentTemp(bedroom.getCurrentTemp() + 1);
                        bedroomTemp.setText("" + bedroom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            }
        } else if (roomName.equals("bathroom")) {
            if (onoff.equals("on")) {
                double delta = dtemp - bathroom.getCurrentTemp();

                if (delta <= 1.5) {
                    System.out.println("Turning on the Heating System!");
                    while (bathroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bathroomHotWave, dtemp - bathroom.getCurrentTemp(), 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(17000);
                        System.out.println("change in 1");
                        bathroom.setCurrentTemp(bathroom.getCurrentTemp() + 1);
                        bathroomTemp.setText("" + bathroom.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Heating System!");
                    while (bathroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bathroomHotWave, dtemp - bathroom.getCurrentTemp(), 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(12000);
                        System.out.println("change in 1");
                        bathroom.setCurrentTemp(bathroom.getCurrentTemp() + 1);
                        bathroomTemp.setText("" + bathroom.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Heating System!");
                    while (bathroom.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(bathroomHotWave, dtemp - bathroom.getCurrentTemp(), 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(7000);
                        System.out.println("change in 1");
                        bathroom.setCurrentTemp(bathroom.getCurrentTemp() + 1);
                        bathroomTemp.setText("" + bathroom.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5

            }
        } else if (roomName.equals("wc")) {
            if (onoff.equals("on")) {
                double delta = dtemp - wc.getCurrentTemp();

                if (delta <= 1.5) {
                    System.out.println("Turning on the Heating System!");
                    while (wc.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(wcHotWave, dtemp - wc.getCurrentTemp(), 1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(17000);
                        System.out.println("change in 1");
                        wc.setCurrentTemp(wc.getCurrentTemp() + 1);
                        wcTemp.setText("" + wc.getCurrentTemp());
                    }
                    // animation for 1 fan
                } else if (delta > 1.5 && delta <= 2.5) {
                    System.out.println("Turning on the Heating System!");
                    while (wc.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(wcHotWave, dtemp - wc.getCurrentTemp(), 2);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(12000);
                        System.out.println("change in 1");
                        wc.setCurrentTemp(wc.getCurrentTemp() + 1);
                        wcTemp.setText("" + wc.getCurrentTemp());
                    }
                } else {
                    System.out.println("Turning on the Heating System!");
                    while (wc.getCurrentTemp() < dtemp) {
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hotWaveAnimation(wcHotWave, dtemp - wc.getCurrentTemp(), 3);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                        Thread.sleep(7000);
                        System.out.println("change in 1");
                        wc.setCurrentTemp(wc.getCurrentTemp() + 1);
                        wcTemp.setText("" + wc.getCurrentTemp());
                    }
                }

                //3 fans - > 5 secs | if delta >2.5
                //2 fans -> 10 secs | if delta >1.5 and <2.5
                //1 fans -> 15 secs | if delta <1.5


            }
        }

    }

    public void hotWaveAnimation(ImageView image, double delta, int numOfFans) throws InterruptedException {
        String filename = "Web 1920 – 12";
        long startTime = System.currentTimeMillis();
        int time = 0;
        switch (numOfFans) {
            case 1:
                time = 17000;
                break;
            case 2:
                time = 12000;
                break;
            case 3:
                time = 7000;
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

        if (delta <= 1.0) image.setImage(null);
    }

    public void checkingHouseTemp(ArrayList<Room> roomList) {
        for (Room room : roomList) {
            String result = null;
            try {
                result = SensorNode.connect("" + room, 3333);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);

            double finaldtemp = dtemp;
            System.out.println(dtemp);
            //If not in a new thread each room will be executed one by one
            String finalResult = result;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    String[] data = finalResult.split("#");
                    if (data[1].equals("cooling")) {
                        //call coolingFunc
                        try {
                            coolingFunction(data[2], data[0]);
                            if (room.getCurrentTemp() == finaldtemp) {
                                SensorNode.connect(room.getName() + "#" + room.getCurrentTemp() + "#false", 3333);
                            }
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    } else if (data[1].equals("heating")) {
                        //call heatingFunc
                        try {
                            heatingFunction(data[2], data[0]);
                            if (room.getCurrentTemp() == finaldtemp) {
                                SensorNode.connect(room.getName() + "#" + room.getCurrentTemp() + "#false", 3333);
                            }
                        } catch (InterruptedException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start();
        }
    }
}