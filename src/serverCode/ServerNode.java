package serverCode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode {
    public static void main(String[] args) throws Exception {
        processData(collectData());
    }

    public static String collectData() throws Exception {
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        //DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        str = din.readUTF();
        System.out.println(str);

        din.close();
        s.close();
        ss.close();

        return str;
    }

    public static void processData(String data) {
        String[] datar = new String[4];
        int nextLine = 0;

        String str = "";
        for (Character c : data.toCharArray()) {
            if (c == '\n') {
                datar[nextLine] = str;
                nextLine++;
                str = "";
            } else {
                str += c;
            }
        }

        for (int i = 0; i < 4; i++) {
            String[] result = datar[i].split(" ");
            checkTemperature(result[0], Double.parseDouble(result[1]), 21.0);
        }
    }

    public static void checkTemperature(String roomName, double temp, double dtemp) {
        System.out.println("Checking temperature of " + roomName);
        System.out.println("Current temperature is " + temp);

        if (temp > dtemp) {
            //Make it cooler
            try {
                System.out.println("Adjusting temperature, please wait 5 seconds!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(roomName + " is now " + dtemp + " C!");
            System.out.println();

        } else if (temp < dtemp) {
            //Make it warmer
            try {
                System.out.println("Adjusting temperature, please wait 5 seconds!");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(roomName + " is now " + dtemp + " C!");
            System.out.println();
        } else {
            System.out.println("Temperature in " + roomName + " is optimal!");
        }
    }

}
