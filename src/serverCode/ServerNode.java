package serverCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(3333);

        while (true) {
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "", str2 = "";

            str = din.readUTF();
            System.out.println(str);
            str2 = checkTemperature(str, 21.0); //do this fxml
            dout.writeUTF(str2);
            dout.flush();

            din.close();
            s.close();

        }

        //ss.close();
    }

    public static String checkTemperature(String data, double desiredTemp) {
        String[] dataArray = data.split("#");
        String result = "";

        if (Double.parseDouble(dataArray[1]) < desiredTemp) {
            //turn on heating
            result = "on#heating#" + dataArray[0];
        } else if (Double.parseDouble(dataArray[1]) > desiredTemp) {
            //turn on cooling
            result = "on#cooling#" + dataArray[0];
        } else {
            System.out.println("Desired temperature has been reached!");
        }

        return result;
    }
}

