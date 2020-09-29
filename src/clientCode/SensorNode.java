package clientCode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class SensorNode {


    public double getCurrentRoomTemp() {
        Random random = new Random();

        return 20 + random.nextInt(5);
    }

    public static void sendData(ArrayList<Room> roomList) throws Exception {
        Socket s = new Socket("localhost", 3333);
        //DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;
        String str = "";
        while (counter < roomList.size()) {
            String item = roomList.get(counter).getName() + " " + roomList.get(counter).getCurrentTemp() + "\n";
            str += item;
            counter++;
        }
        dout.writeUTF(str);
        dout.flush();
        dout.close();
        s.close();
    }
}
