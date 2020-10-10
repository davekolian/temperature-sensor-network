package serverCode;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode implements Runnable {
    public static boolean finished;
    public String startValue;
    public Thread t;
    public ServerSocket ss;
    public int port;
    public Double desiredTemp = 21.0;

    public ServerNode(String startValue, int port) {
        this.startValue = startValue;
        this.port = port;
        t = new Thread(this);
        finished = false;
        t.start();
    }

    @Override
    public void run() {
        int counter = 0;

        try {
            ss = new ServerSocket(port);

            while (!finished) {
                Socket s = ss.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());

                String str = "", str2 = "";

                str = din.readUTF();

                if(str.equals("")){
                    din.close();
                    dout.close();
                    s.close();
                }

                str2 = checkTemperature(str); //do this fxml
                System.out.println(str2);

                ServerController.receiveTempRooms(str);

                dout.writeUTF(str2);
                dout.flush();

                counter++;

                dout.close();
                din.close();
                s.close();

            }
        } catch (Exception e) {
            try {
                finished = true;
                ss.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void stopMe() throws IOException {
        finished = true;
        ss.close();
    }

    public String checkTemperature(String data) {
        if(data.equals("init")){
            desiredTemp = Double.parseDouble(ServerController.getHouseTempText());
            return "change#"+desiredTemp;
        }

        desiredTemp = Double.parseDouble(ServerController.getHouseTempText());

        String[] dataArray = data.split("#");
        String result = "";

        if (Double.parseDouble(dataArray[1]) < desiredTemp) {
            //turn on heating
            result = "on#heating#" + dataArray[0];
        } else if (Double.parseDouble(dataArray[1]) > desiredTemp) {
            //turn on cooling
            result = "on#cooling#" + dataArray[0];
        } else {
            result = "good#good#good";
        }

        return result;
    }
}

