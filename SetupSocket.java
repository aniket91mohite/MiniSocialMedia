package MainWindow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SetupSocket {

    public static Socket S;
    public static DataInputStream Din;
    public static DataOutputStream Dout;
    public static SetupSocket instance = new SetupSocket();

    public static SetupSocket getInstance(){
        return instance;
    }

    public static void setupSocket() throws Exception {
        S = new Socket("localhost", 1080);
        Din = new DataInputStream(S.getInputStream());
        Dout = new DataOutputStream(S.getOutputStream());
    }

    public static void closeSocket() throws Exception {
        Dout.close();
        Din.close();
        S.close();
    }
}
