package serverCode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNode {
    public static void main(String[] args) throws Exception {
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
    }


}
