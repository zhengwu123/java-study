/**
 * Created by zhengwu on 10/19/16.
 */
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FoilMakerClient extends JFrame {
    private int score;
    private String userToken;
    private String gameToken;
    JLabel loginnamelabel;
    JLabel loginnamelabel1;
    JPanel Mainpanel;
    JTextArea participateText = new JTextArea(15,15);
    JLabel participantsLabel = new JLabel("Participants:");
    JButton startGamebutton = new JButton("Start Game");
    CardLayout layout = new CardLayout();
    PrintWriter out;
    BufferedReader in;
    JPanel startPanel;
    JPanel waitingGamePanel;
    JLabel messageLabel = new JLabel("Others should use this key join your game,");
    JLabel keylabel = new JLabel();
    JButton startNewGameButton = new JButton("New game");
    JButton JoinGameButton = new JButton("Join a game");
    JPasswordField passwordField = new JPasswordField(15);
    JTextField nameField = new JTextField(15);
    JButton Loginbutton = new JButton("Login");
    JButton Registerbutton = new JButton("Register");
    JPanel panel = new JPanel();
    JLabel resultlabel = new JLabel("                                             ");
    JLabel resultlabel1 = new JLabel("                                                ");
    JLabel resultlabel2 = new JLabel("                                             ");
    JLabel resultlabel3 = new JLabel("                                             ");
    JLabel resultlabel4 = new JLabel("Joined game: waiting for leader                     ");
    JLabel resultlabel5 = new JLabel("                                             ");
    JLabel namelabel = new JLabel("username:");
    JLabel passlabel = new JLabel("Password:");
    Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    JPanel joinGamePanel ;
    JLabel joinname = new JLabel();
    JLabel joinMessage = new JLabel("Enter the game key to join");
    JTextField joinkeyText = new JTextField(5);
    JButton joinButton = new JButton("JoinGame");
    JPanel joingamewaitingpanel;
    JLabel waitingname;
    JLabel joingamewaitinglabel = new JLabel(" waiting for leader..");
    public FoilMakerClient() {
        score=0;
        Mainpanel = new JPanel(layout);
        add(Mainpanel);
        JPanel panel = new JPanel(new GridBagLayout());
        startPanel = new JPanel(new GridBagLayout());
        joinGamePanel = new JPanel(new GridBagLayout());
        joingamewaitingpanel = new JPanel(new GridBagLayout());
        joingamewaitingpanel.setBorder(border);
        Mainpanel.add(joingamewaitingpanel,"joinGamePanelwaiting");
        panel.setBorder(border);
        startPanel.setBorder(border);
        waitingGamePanel = new JPanel(new GridBagLayout());
        waitingGamePanel.setBorder(border);
        keylabel.setBackground(Color.white);
        Mainpanel.add(waitingGamePanel, "waiting");
        Mainpanel.add(panel, "loginpanel");
        Mainpanel.add(startPanel, "startpanel");
        layout.show(Mainpanel, "loginpanel");
        joinGamePanel.setBorder(border);
        Mainpanel.add(joinGamePanel,"joinGamePanel");
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(namelabel, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(passlabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(Loginbutton, c);
        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        panel.add(Registerbutton, c);
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(290, 0, 0, 70);
        c.anchor = GridBagConstraints.CENTER;
        resultlabel.setBorder(border);
        panel.add(resultlabel, c);
        event e = new event();
        Registerbutton.addActionListener(e);
        Loginbutton.addActionListener(e);
        String serverIP = "localhost";
        int serverPort = 2004;
        try

        {
            // Connect to server
            Socket socket = new Socket(serverIP, serverPort);
            // Create data writer
            out = new PrintWriter(socket.getOutputStream(), true);
            // Create data reader
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(isr);
            // Send message to server
            //out.println("Message to server");
            // Read server response
            //String serverMessage = in.readLine();
        } catch (
                IOException error
                )

        {
            error.printStackTrace();
        }
        loginnamelabel = new JLabel();
        loginnamelabel.setBorder(border);
        loginnamelabel1 = new JLabel();
        loginnamelabel1.setBorder(border);
        loginnamelabel.setBackground(Color.orange);
        loginnamelabel1.setText(loginnamelabel.getText());
        c.insets = new Insets(5, 170, 125, 5);
        c.gridx = 0;
        c.gridy = 0;
        startPanel.add(loginnamelabel1, c);
        loginnamelabel1.setBackground(Color.orange);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 5);
        startPanel.add(startNewGameButton, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 5, 70);
        startPanel.add(JoinGameButton, c);
        //add result label
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(100, 120, 0, 0);
        resultlabel1.setBorder(border);
        startPanel.add(resultlabel1, c);

        startNewGameButton.addActionListener(e);
        JoinGameButton.addActionListener(e);
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        c.insets = new Insets(5, 5, 5, 5);
        waitingGamePanel.add(messageLabel, c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(50, 5, 5, 5);
        waitingGamePanel.add(keylabel, c);
        keylabel.setBorder(border1);
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(5, 5, 5, 5);
        waitingGamePanel.add(participantsLabel, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(5, 5, 5, 5);
        participateText.setEnabled(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        waitingGamePanel.add(participateText, c);
        participateText.setBackground(Color.orange);
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.insets = new Insets(5, 100, 5, 5);
        waitingGamePanel.add(startGamebutton, c);
        startGamebutton.addActionListener(e);
        startGamebutton.setEnabled(false);
        boolean peoplejoined;
        //add result label
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(120, 0, 0, 70);
        resultlabel2.setBorder(border);
        waitingGamePanel.add(resultlabel2, c);
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx=0;
        c1.gridy=1;
        joinGamePanel.add(joinname,c1);
        c1.gridx=0;
        c1.gridy=2;
        joinGamePanel.add(joinMessage,c1);
        c1.gridx=0;
        c1.gridy=3;
        joinkeyText.setBackground(Color.orange);
        joinGamePanel.add(joinkeyText,c1);
        c1.gridx=0;
        c1.gridy=4;
        joinGamePanel.add(joinButton,c1);
        c1.gridx=0;
        c1.gridy=5;
        c1.insets = new Insets(120, 0, 0, 70);
        joinGamePanel.add(resultlabel3,c1);

        joinButton.addActionListener(e);

        c1.gridx=0;
        c1.gridy=0;
        c1.insets = new Insets(0, 0, 0, 0);
        loginnamelabel.setBorder(border);
        joingamewaitingpanel.add(loginnamelabel,c1);
        c1.gridx=0;
        c1.gridy=1;
        c1.insets = new Insets(0, -5, 0, 0);
        joingamewaitingpanel.add(joingamewaitinglabel,c1);
        c1.gridx=0;
        c1.gridy=2;
        resultlabel4.setBorder(border);
        c1.insets = new Insets(120, 60, 0, 60);
        joingamewaitingpanel.add(resultlabel4,c1);

    }
    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                if(e.getSource()==Registerbutton){
                String text = "CREATENEWUSER--" + nameField.getText() +"--"+ passwordField.getText();
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS"))
                        resultlabel.setText("success,user created!");
                    if(serverMessage.contains("INVALIDMESSAGEFORMAT"))
                        resultlabel.setText("error,invalid format!");
                    if(serverMessage.contains("INVALIDUSERNAME"))
                        resultlabel.setText("username can't be empty!");
                    if(serverMessage.contains("INVALIDUSERPASSWORD"))
                        resultlabel.setText("password can't be empty!");
                    if(serverMessage.contains("USERALREADYEXISTS"))
                        resultlabel.setText("error,user already exist!");
                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==Loginbutton){
                String text = "LOGIN--" + nameField.getText() +"--"+ passwordField.getText();
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS")) {
                        resultlabel1.setText("success,user logged in!");
                        userToken = serverMessage.substring(26);
                        System.out.println(userToken);
                        loginnamelabel.setText(nameField.getText());
                        loginnamelabel1.setText(nameField.getText());
                        messageLabel.setText("Others should use this key join your game,"+nameField.getText());
                        joinname.setText(nameField.getText());
                        layout.show(Mainpanel,"startpanel");

                    }
                    if(serverMessage.contains("INVALIDMESSAGEFORMAT"))
                        resultlabel1.setText("error,invalid format!");
                    if(serverMessage.contains("UNKNOWNUSER"))
                        resultlabel1.setText("username not registered!");
                    if(serverMessage.contains("INVALIDUSERPASSWORD"))
                        resultlabel1.setText("invalid password!");
                    if(serverMessage.contains("USERALREADYLOGGEDIN"))
                        resultlabel1.setText("error,user already logged in!");
                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==startNewGameButton)
            {
                String text = "STARTNEWGAME--" + userToken;
                out.println(text);
                try {
                    //String serverMessage = in.readLine();
                    String serverMessage = "";
                    while((serverMessage=in.readLine())!=null) {
                        System.out.println(serverMessage);

                        if (serverMessage.contains("SUCCESS")) {
                            System.out.println("get in");
                            resultlabel2.setText("success,gamed created!");
                            gameToken = serverMessage.substring(33);
                            System.out.println(gameToken);
                            loginnamelabel.setText(nameField.getText());
                            keylabel.setText("                                   " + gameToken);
                                break;
                        }
                        if (serverMessage.contains("USERNOTLOGGEDIN"))
                            resultlabel2.setText("error,invalid usertoken!");
                        if (serverMessage.contains("FAILURE"))
                            resultlabel2.setText("created game failed!");
                        if (serverMessage.contains("NEWPARTICIPANT")) {
                            String partitipantname = serverMessage.substring(16);
                            participateText.append("--> " + partitipantname + "\n");
                            resultlabel2.setText(partitipantname + "joined game!");
                            System.out.println("new participant");
                        }
                    }
                    layout.show(Mainpanel, "waiting");
                    //create new thread for reading process
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String serverMessage="";
                            try {
                                while ((serverMessage = in.readLine()) != null) {
                                    System.out.println(serverMessage);
                                    boolean flag = false;
                                    if (serverMessage.contains("SUCCESS")) {
                                        flag = true;
                                        System.out.println("get in");
                                        resultlabel2.setText("success,gamed created!");
                                        gameToken = serverMessage.substring(33);
                                        System.out.println(gameToken);
                                        layout.show(Mainpanel, "waiting");
                                        loginnamelabel.setText(nameField.getText());
                                        keylabel.setText("                                   " + gameToken);

                                    }
                                    if (serverMessage.contains("USERNOTLOGGEDIN"))
                                        resultlabel2.setText("error,invalid usertoken!");
                                    if (serverMessage.contains("FAILURE"))
                                        resultlabel2.setText("created game failed!");
                                    if (serverMessage.contains("NEWPARTICIPANT")) {
                                        String partitipantname = serverMessage.substring(16);
                                        participateText.append("--> " + partitipantname + "\n");
                                        resultlabel2.setText(partitipantname + "joined game!");
                                        System.out.println("new participant");
                                        startGamebutton.setEnabled(true);
                                    }
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

//                    new Thread(new Runnable() {
//
//
//                        @Override
//                        public void run() {
//                            try {
//                                while ((serverMessage = in.readLine()) != null) {
//                                    System.out.println(serverMessage);
//                                    boolean flag = false;
//                                    if (serverMessage.contains("SUCCESS")) {
//                                        flag = true;
//                                        System.out.println("get in");
//                                        resultlabel2.setText("success,gamed created!");
//                                        gameToken = serverMessage.substring(33);
//                                        System.out.println(gameToken);
//                                        layout.show(Mainpanel, "waiting");
//                                        loginnamelabel.setText(nameField.getText());
//                                        keylabel.setText("                                   " + gameToken);
//
//
//                                    }
//                                    if (serverMessage.contains("USERNOTLOGGEDIN"))
//                                        resultlabel2.setText("error,invalid usertoken!");
//                                    if (serverMessage.contains("FAILURE"))
//                                        resultlabel2.setText("created game failed!");
//                                    if (serverMessage.contains("NEWPARTICIPANT")) {
//                                        String partitipantname = serverMessage.substring(16);
//                                        participateText.append("--> " + partitipantname + "\n");
//                                        resultlabel2.setText(partitipantname + "joined game!");
//                                        System.out.println("new participant");
//                                    }
//                                }
//
//                            } catch (Exception e) {
//
//                        }
//
//
//                    }).start();


                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==JoinGameButton)
            {
                layout.show(Mainpanel,"joinGamePanel");

            }
            if(e.getSource()==joinButton)
            {
                String text = "JOINGAME--" + userToken +"--" +joinkeyText.getText();
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS")) {
                        resultlabel3.setText("joined a game!");
                        joinname.setText(nameField.getText());
                        layout.show(Mainpanel,"joinGamePanelwaiting");

                    }
                    if(serverMessage.contains("USERNOTLOGGEDIN"))
                        resultlabel3.setText("error,invalid usertoken!");
                    if(serverMessage.contains("FAILURE"))
                        resultlabel3.setText("created game failed!");
                    if(serverMessage.contains("GAMEKEYNOTFOUND"))
                        resultlabel3.setText("invalid game token!");

                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==startGamebutton)
            {
                String text = "ALLPARTICIPANTSHAVEJOINED--" + userToken +"--" +gameToken;
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS")) {
                        resultlabel3.setText("joined a game!");
                        joinname.setText(nameField.getText());
                        layout.show(Mainpanel,"joinGamePanelwaiting");

                    }
                    if(serverMessage.contains("USERNOTLOGGEDIN"))
                        resultlabel3.setText("error,invalid usertoken!");
                    if(serverMessage.contains("USERNOTGAMELEADER"))
                        resultlabel3.setText("User already playing the game!");
                    if(serverMessage.contains("GAMEKEYNOTFOUND"))
                        resultlabel3.setText("invalid game token!");

                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                FoilMakerClient gui = new FoilMakerClient();
                gui.setTitle("FoilMaker!");
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setSize(350,600);
                gui.setVisible(true);
            }
        };
        thread.start();




    }
}