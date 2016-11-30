/**
 * Created by zhengwu on 11/21/16.
 */
import java.io.*;
import java.net.ServerSocket; import java.net.Socket;
import java.util.HashMap;

public class FoilMakerServer implements Runnable{
    Socket ssocket;
   BufferedReader bin = null;
    BufferedWriter bout = null;
    private HashMap<String,FoilUser> hm = new HashMap<String, FoilUser>();
    FoilMakerServer(Socket socket){
        this.ssocket = socket;

    }
        public static void main(String[] args) throws IOException { // Listen on port 9090

            //Integer.parseInt(args[0]
            ServerSocket listener = new ServerSocket(9001);
            System.out.println("Listening.....");
            try {
                while (true) {
                    Socket socket = listener.accept();
                    System.out.println("Connected..");
            // Wait for next client connection


            // Create data reader
                   // InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                    // in = new BufferedReader(isr);
                    // Create data writer
                   // out = new PrintWriter(socket.getOutputStream(), true); // Read client request
                    //String clientMessage = in.readLine();
                // Send reply to client
                    //out.println("Hello!. I received " + clientMessage);


                    new Thread(new FoilMakerServer(socket)).start();



// Copy line-by-line

                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    @Override
    public void run() {
        InputStreamReader isr = null;
        //System.out.println("can i get here?0");
        try {

            BufferedReader bin = new BufferedReader(new FileReader(new File("/Users/new/Desktop/cs180/project4/UserDatabase"))); // File writer
            BufferedWriter bout = new BufferedWriter(new FileWriter(new File("/Users/new/Desktop/cs180/project4/UserDatabase")));
            String line1;
            //System.out.println("can i get here?1");
            while ((line1 = bin.readLine()) != null) {
                System.out.println(line1);
               // System.out.println("can i get here?2");

            }
            isr = new InputStreamReader(this.ssocket.getInputStream());
            BufferedReader   in = new BufferedReader(isr);
            // Create data writer
            PrintWriter out = new PrintWriter(this.ssocket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                out.println("Hello!. I received " + line);
                System.out.println(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
// Finalize
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
