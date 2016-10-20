/**
 * Created by zhengwu on 10/19/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FoilMakerClient {
    public static void main(String[] args) {


        String serverIP = "localhost";
        int serverPort = 2001;
        try

        {
        // Connect to server
            Socket socket = new Socket(serverIP, serverPort);
        // Create data writer
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Create data reader
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader in = new BufferedReader(isr);
        // Send message to server
            out.println("Message to server");
        // Read server response
            String serverMessage = in.readLine();
        } catch (
                IOException e
                )

        {
            e.printStackTrace();
        }

    }
}