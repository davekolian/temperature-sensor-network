package serverCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerNode {
    public static void start(String[] resultArray) throws IOException {
        int counter = 0;

        ServerSocket ss = new ServerSocket(3333);

        while (true) {
            Socket s = ss.accept();
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            String str = "", str2 = "";

            str = din.readUTF();
            System.out.println(str);
            str2 = checkTemperature(str, 21.0); //do this fxml
            assert resultArray != null;
            resultArray[counter] = str;

            if(counter == 4){
                ServerController.receiveTempRooms(resultArray);
                counter = 0;
                resultArray = null;
                str2 += "\nredo";
            }

            dout.writeUTF(str2);
            dout.flush();

            counter++;

            din.close();
            s.close();

        }

        //ss.close();
    }

    static ArrayList<String> listOfHC = new ArrayList<>(5);

    public static String checkTemperature(String data, double desiredTemp) {

        String[] dataArray = data.split("#");
        String result = "";

        if (Double.parseDouble(dataArray[1]) < desiredTemp) {
            //turn on heating
            result = "on#heating#" + dataArray[0];
            if (listOfHC.size() < 5) listOfHC.add(result);
        } else if (Double.parseDouble(dataArray[1]) > desiredTemp) {
            //turn on cooling
            result = "on#cooling#" + dataArray[0];
            if (listOfHC.size() < 5) listOfHC.add(result);
        } else {
            result = "good#good#good";
        }

        return result;
    }
}

