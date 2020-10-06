package clientCode;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class SensorNode {

    public double getCurrentRoomTemp() {
        Random random = new Random();

        return 15 + random.nextInt(10);
    }

    public  String connect(String data) throws IOException {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        String fromServer = "";

        dout.writeUTF(data);
        dout.flush();
        fromServer = din.readUTF();

        dout.close();
        s.close();

        return fromServer;
    }
}
