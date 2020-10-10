package clientCode;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class SensorNode {

    public double getCurrentRoomTemp() {
        Random random = new Random();

        return 15 + random.nextInt(10);
    }

    public static String connect(String data, int port) throws IOException {
        Socket s = new Socket("localhost", port);
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
