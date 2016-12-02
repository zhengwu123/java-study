/**
 * Created by zhengwu on 11/21/16.
 */
import java.io.*;
import java.net.ServerSocket; import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

public class FoilMakerServer implements Runnable {
    Socket ssocket;
    private HashMap<String, FoilUser> hm = new HashMap<String, FoilUser>();
    //This writer can writer to both clients
    static PrintWriter mainout;
    static PrintWriter mainoutholder;
    static String gameToken;
    FoilMakerServer(Socket socket) {
        this.ssocket = socket;

    }
    public static void main(String[] args) throws IOException { // Listen on port 9090

        BufferedReader bin = null;
        BufferedWriter bout = null;

        try {
            bout = new BufferedWriter(new FileWriter(new File("UserDatabase"), true));
            bin = new BufferedReader(new FileReader(new File("UserDatabase")));

            String line1;
            while ((line1 = bin.readLine()) != null) {
                System.out.println(line1);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
            int i=0;
            while (true) {
                // Wait for next client connection
                Socket socket = listener.accept();
                mainout = new PrintWriter(socket.getOutputStream(), true);
                mainoutholder=mainout;
                System.out.println(" New Client "+ i+" Connected..");
                //multi-thread server
                new Thread(new FoilMakerServer(socket)).start();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        InputStreamReader isr = null;
        BufferedReader tin = null;
        BufferedWriter tout = null;
        boolean loginFlag = false;
        boolean playingFlag = false;
        String token="";
       // String gameToken="";
        String guestToken="";
        String guestgameToken="";
        String username="";
        PrintWriter out;
        //System.out.println("can i get here?0");
        try {

            //reader and writer for sockets
            isr = new InputStreamReader(this.ssocket.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            // Create data writer
            out = new PrintWriter(this.ssocket.getOutputStream(), true);
            //reader and writer for files


            tout = new BufferedWriter(new FileWriter(new File("UserDatabase"), true));
            tin = new BufferedReader(new FileReader(new File("UserDatabase")));

            String line1;
            while ((line1 = tin.readLine()) != null) {
                System.out.println(line1);
                String[] words = line1.split(":");
                String user=words[0];
                String password=words[1];
                int score=Integer.parseInt(words[2]);
                int fooled=Integer.parseInt(words[3]);
                int beenfooled=Integer.parseInt(words[4]);
                FoilUser foilUser = new FoilUser(user,password,score,fooled,beenfooled);

                hm.put(words[0], foilUser);
            }
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                String[] words = line.split("--");
                //create user
                if (words[0].equals("CREATENEWUSER")) {
                    //check username
                    if (words[1].length() < 1 || words[1].length() > 9 || !isAlphaNumeric(words[1])) {
                        out.println("RESPONSE--CREATENEWUSER--INVALIDUSERNAME--");
                    }
                    //check password
                    if (words[2].length() == 0 || !isAlphaNumeric2(words[2])) {
                        out.println("RESPONSE--CREATENEWUSER--INVALIDUSERPASSWORD--");
                    }
                    if (hm.containsKey(words[1])) {
                        out.println("RESPONSE--CREATENEWUSER--USERALREADYEXISTS--");
                    }
                    if (isAlphaNumeric(words[1]) && isAlphaNumeric2(words[2]) && !hm.containsKey(words[1])) {
                        System.out.println("\n" + words[1] + ":" + words[2] + ":0:0:0");
                        tout.write("\n" + words[1] + ":" + words[2] + ":0:0:0");
                        tout.flush();
                        out.println("RESPONSE--CREATENEWUSER--SUCCESS--");
                        String user=words[1];
                        String password=words[2];
                        FoilUser foilUser = new FoilUser(user,password,0,0,0);
                        hm.put(words[1], foilUser);
                    } else {
                        out.println("RESPONSE--CREATENEWUSER--INVALIDMESSAGEFORMAT--");
                    }

                }
                //USER LOGIN
                if (words[0].equals("LOGIN")) {

                    if (words[1].length() < 1 || words[1].length() > 9 || !isAlphaNumeric(words[1])) {
                        out.println("RESPONSE--LOGIN--INVALIDUSERNAME--");
                    }
                    //check password
                    if (loginFlag) {
                        out.println("RESPONSE--LOGIN--USERALREADYLOGGEDIN--");
                    }
                    if (hm.containsKey(words[1]) && !hm.get(words[1]).getPassword().equals(words[2])) {
                        out.println("RESPONSE--LOGIN--INVALIDUSERPASSWORD--");
                    }
                    if (!hm.containsKey(words[1])) {
                        out.println("RESPONSE--LOGIN--UNKNOWNUSER--");

                    }
                    if (hm.containsKey(words[1]) && hm.get(words[1]).getPassword().equals(words[2])) {
                        token = generateRandomString();
                        username = words[1];
                        out.println("RESPONSE--LOGIN--SUCCESS--" + token);

                        loginFlag = true;
                    }
                }
                if (words[0].equals("STARTNEWGAME")) {
                    //create game. set main out to leader client writer
                    mainout = out;
                    if(!words[1].equals(token)){
                        out.println("RESPONSE--STARTNEWGAME--USERNOTLOGGEDIN--");
                    }
                    if(words[1].equals(token)){
                        gameToken = generateRandomString3();
                        out.println("RESPONSE--STARTNEWGAME--SUCCESS--"+gameToken);
                    }
                    if(!words[1].equals(token)){
                        out.println("RESPONSE--STARTNEWGAME--FAILURE--");
                    }


                }
                if (words[0].equals("JOINGAME")) {
                    mainoutholder=out;

                    if(!words[1].equals(token)){
                        out.println("RESPONSE--JOINGAME--USERNOTLOGGEDIN--"+words[2]);
                    }
                    if(words[1].equals(token)&& playingFlag==true){
                        out.println("RESPONSE--JOINGAME--FAILURE--"+words[2]);
                }
                    if(words[1].equals(token)&& playingFlag==false){
                        //gameToken = generateRandomString3();
                        mainout.println("NEWPARTICIPANT--"+username+"--0");
                        mainoutholder.println("RESPONSE--JOINGAME--SUCCESS--"+words[2]);
                    }
                    if(!words[2].equals(gameToken)){
                        System.out.println(gameToken +"------"+words[2]);
                        out.println("RESPONSE--JOINGAME--GAMEKEYNOTFOUND--"+words[2]);
                    }


                }
                if (words[0].equals("ALLPARTICIPANTSHAVEJOINED")) {
                    //System.out.println(words[1]+"==="+token+words[2]+"==="+gameToken);

                    if(!words[1].equals(token)){
                        out.println("RESPONSE--ALLPARTICIPANTSHAVEJOINED--USERNOTLOGGEDIN--");
                    }
                    if(!words[2].equals(gameToken)){
                        out.println("RESPONSE--ALLPARTICIPANTSHAVEJOINED--INVALIDGAMETOKEN--");
                    }
                    if(words[1].equals(token) && words[2].equals(gameToken)&& playingFlag==false){
                        playingFlag=true;
                       // BufferedReader bin = new BufferedReader(new FileReader(new File("WordleDeck")));

                        //String tline1;
                       // while ((tline1 = bin.readLine()) != null) {
                           // System.out.println(line1);

                       // }
                        mainoutholder.println("NEWGAMEWORD--A group of zebras--a dazzle");
                        mainout.println("NEWGAMEWORD--A group of zebras--a dazzle");

                    }


                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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


    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9_]*$";
        return s.matches(pattern);
    }

    public static boolean isAlphaNumeric2(String s) {
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{1,9}$";
        String pattern1 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{3,9}$";
        return s.matches(pattern) || s.matches(pattern1);
    }


    //This method generates random string of length 10
    String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String generateRandomString() {
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    public String generateRandomString3() {
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    public int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}

