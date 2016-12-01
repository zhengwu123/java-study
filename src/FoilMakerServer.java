/**
 * Created by zhengwu on 11/21/16.
 */
import java.io.*;
import java.net.ServerSocket; import java.net.Socket;
import java.util.HashMap;

public class FoilMakerServer implements Runnable{
    Socket ssocket;
    private HashMap<String,String> hm = new HashMap<String, String>();




    FoilMakerServer(Socket socket){
        this.ssocket = socket;



    }
        public static void main(String[] args) throws IOException { // Listen on port 9090

            BufferedReader bin=null;
            BufferedWriter bout =null;
            try {
                bout = new BufferedWriter(new FileWriter(new File("UserDatabase"),true));
                bin = new BufferedReader(new FileReader(new File("UserDatabase")));


                String line1;
                while ((line1 = bin.readLine()) != null) {
                    System.out.println(line1);

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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

            //Integer.parseInt(args[0]
            ServerSocket listener = new ServerSocket(9001);
            System.out.println("Listening.....");
            try {
                while (true) {
                    // Wait for next client connection
                    Socket socket = listener.accept();
                    System.out.println("Connected..");
                    //multi-thread server
                    new Thread(new FoilMakerServer(socket)).start();

                }
            }catch (Exception e){
                e.printStackTrace();
            }


        }

    @Override
    public void run() {
        InputStreamReader isr = null;
        BufferedReader tin=null;
        BufferedWriter tout =null;
        //System.out.println("can i get here?0");
        try {

            //reader and writer for sockets
            isr = new InputStreamReader(this.ssocket.getInputStream());
            BufferedReader   in = new BufferedReader(isr);
            // Create data writer
            PrintWriter out = new PrintWriter(this.ssocket.getOutputStream(), true);
            //reader and writer for files


                tout = new BufferedWriter(new FileWriter(new File("UserDatabase"),true));
                tout.write("\nzzzz:zzzz:0:0:0");
                tin = new BufferedReader(new FileReader(new File("UserDatabase")));
            String line;
            while ((line = in.readLine()) != null) {
                String[] words = line.split("--");
                //create user
                //System.out.println(line);
               // System.out.println(words[0]+ ":"+words[1]+":"+words[2]);
               if(words[0].equals("CREATENEWUSER")){
                    //check username
                    if(words[1].length()<1 || words[1].length()>9 ||!isAlphaNumeric(words[1])) {
                        out.println("RESPONSE--CREATENEWUSER--INVALIDUSERNAME--");
                    }
                    //check password
                    if(words[2].length()==0 ||!isAlphaNumeric2(words[2])) {
                        out.println("RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--");
                    }
                    if(isAlphaNumeric(words[1])&& isAlphaNumeric2(words[2])){
                        System.out.println("\n"+words[1]+":"+words[2]+":0:0:0");
                        tout.write("\n"+words[1]+":"+words[2]+":0:0:0");
                        out.println("RESPONSE--CREATENEWUSER--SUCCESS--");
                    }

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Finalize
            if (tin != null) {
                try {
                    tin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (tout != null) {
                try {
                    tout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }


    public static boolean isAlphaNumeric(String s){
        String pattern= "^[a-zA-Z0-9_]*$";
        return s.matches(pattern);
    }

    public static boolean isAlphaNumeric2(String s){
        String pattern= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{1,9}$";
        //String pattern2= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{1,9}$";
        String pattern1= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{3,9}$";
        return s.matches(pattern)||s.matches(pattern1);
    }
}
