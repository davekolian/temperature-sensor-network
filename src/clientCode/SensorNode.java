package clientCode;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class SensorNode {

    public double getCurrentRoomTemp() {
        Random random = new Random();

        return 20 + random.nextInt(5);
    }

    public static void connect(String data) throws IOException {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String fromServer = "";

        dout.writeUTF(data);
        dout.flush();
        fromServer = din.readUTF();
        System.out.println(fromServer);


        dout.close();
        s.close();
    }
}
