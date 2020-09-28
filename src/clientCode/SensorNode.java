package clientCode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class SensorNode {

    public double doEverything(String name){
        double result = getCurrentRoomTemp();
        try {
            sendData(name + " Temperature: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public double getCurrentRoomTemp() {
        Random random = new Random();

        return 20 + random.nextInt(5);
    }

    public void sendData(String name) throws Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dout.writeUTF(name);
        dout.flush();



        dout.close();
        s.close();
    }
}
