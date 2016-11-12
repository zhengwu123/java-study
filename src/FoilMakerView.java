
/**
 * Created by zhengwu on 11/11/16.
 */
//package project3;
    import javax.swing.*;
    import javax.swing.border.BevelBorder;
    import javax.swing.border.Border;
    import javax.swing.border.EtchedBorder;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.WindowAdapter;
    import java.awt.event.WindowEvent;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;
    import java.util.Random;

    public class FoilMakerView extends JFrame {
        private int score;
        private String roundResult;
        String userToken;
        String gameToken;
        JLabel loginnamelabel;
        JLabel loginnamelabel1;
        JLabel FinalnameLabel = new JLabel("  ");
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
        JLabel gamePanel1resultlabel = new JLabel("                                             ");
        JLabel pickoptionpanelresultlabel = new JLabel("Pick your choice            ");
        JLabel namelabel = new JLabel("username:");
        JLabel passlabel = new JLabel("Password:");
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
        JPanel joinGamePanel ;
        JPanel gamepanel1;
        JLabel joinname = new JLabel();
        JLabel joinMessage = new JLabel("Enter the game key to join");
        JTextField joinkeyText = new JTextField(5);
        JButton joinButton = new JButton("JoinGame");
        JPanel joingamewaitingpanel;
        JLabel waitingname;
        JLabel joingamewaitinglabel = new JLabel(" waiting for leader..");
        JLabel gamePanel1Title = new JLabel("What is the word for");
        JTextArea gamePanel1TextField = new JTextArea(15,15);
        JLabel gamePanel1suggestionLabel = new JLabel("Your suggestion");
        JTextField gamePanel1suggestionText = new JTextField(25);
        JButton gamePanel1submit = new JButton("Submit Suggestion");
        JLabel pickoptionpanellabel = new JLabel("Pick your option below:");
        JRadioButton option1Radio = new JRadioButton();
        JRadioButton option2Radio= new JRadioButton();
        JRadioButton option3Radio= new JRadioButton();
        ButtonGroup group = new ButtonGroup();
        JButton pickoptionSubmit = new JButton("Submit Option");
        JPanel pickoptionpanel;
        //------oh my lady gaga--final-panel
        JPanel finalPanel;
        JLabel finalPanellabel1 = new JLabel("Round Result");
        JTextArea finalPanelTextField1 = new JTextArea(15,15);
        JLabel finalPanellabel2 = new JLabel("Overall Results");
        JTextArea finalPanelTextField2 = new JTextArea(15,15);
        JButton finalPanelNextRoundButton = new JButton("Next Round");
        JLabel finalPanelresultlabel = new JLabel("Click <Next Round> when ready!");

        public FoilMakerView() {
            score=0;

            Mainpanel = new JPanel(layout);

            add(Mainpanel);
            JPanel panel = new JPanel(new GridBagLayout());
            startPanel = new JPanel(new GridBagLayout());
            joinGamePanel = new JPanel(new GridBagLayout());
            joingamewaitingpanel = new JPanel(new GridBagLayout());
            gamepanel1 = new JPanel(new GridBagLayout());
            pickoptionpanel = new JPanel(new GridBagLayout());
            pickoptionpanel.setBorder(border);
            Mainpanel.add(pickoptionpanel,"pickoptionpanel");
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
            gamepanel1.setBorder(border);
            finalPanel = new JPanel(new GridBagLayout());
            finalPanel.setBorder(border);

            finalPanellabel1.setBorder(border);
            finalPanellabel2.setBorder(border);
            Mainpanel.add(gamepanel1,"gamepanel1");
            Mainpanel.add(finalPanel,"finalPanel");
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
            gamePanel1submit.addActionListener(e);
            pickoptionSubmit.addActionListener(e);

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

            c1.gridx=0;
            c1.gridy=0;
            c1.insets = new Insets(0, 0, 0, 0);
            gamepanel1.setBorder(border);
            gamepanel1.add(gamePanel1Title,c1);
            c1.gridx=0;
            c1.gridy=1;
            c1.insets = new Insets(0, 0, 0, 0);
            gamepanel1.add(gamePanel1TextField,c1);
            c1.gridx=0;
            c1.gridy=2;
            gamePanel1TextField.setBackground(Color.orange);
            gamePanel1TextField.setEditable(false);
            gamePanel1TextField.setBorder(border);
            gamepanel1.add(gamePanel1TextField,c1);
            c1.gridx=0;
            c1.gridy=3;
            gamepanel1.add(gamePanel1suggestionLabel,c1);
            c1.gridx=0;
            c1.gridy=4;
            gamepanel1.add(gamePanel1suggestionText,c1);
            c1.gridx=0;
            c1.gridy=5;
            gamepanel1.add(gamePanel1submit,c1);
            c1.gridx=0;
            c1.gridy=6;
            c1.insets = new Insets(120, 60, 0, 60);
            gamePanel1resultlabel.setBorder(border);
            gamepanel1.add(gamePanel1resultlabel,c1);
            option1Radio.addActionListener(e);
            option2Radio.addActionListener(e);
            option3Radio.addActionListener(e);

            //------option panel
            group.add(option1Radio);
            group.add(option2Radio);
            group.add(option3Radio);

            c1.gridx=0;
            c1.gridy=0;
            c1.insets = new Insets(5, 5, 5, 5);
            pickoptionpanel.setBorder(border);
            pickoptionpanel.add(loginnamelabel,c1);
            c1.gridx=0;
            c1.gridy=1;
            c1.insets = new Insets(5, 5, 5, 5);
            pickoptionpanel.add(pickoptionpanellabel,c1);
            c1.gridx=0;
            c1.gridy=2;
            c1.gridx = 0;
            c1.anchor = GridBagConstraints.WEST;
            c1.gridy = GridBagConstraints.RELATIVE;

            c1.insets = new Insets(5, 5, 5, 5);
            pickoptionpanel.add(option1Radio,c1);
            c1.gridx=0;
            c1.gridy=3;
            c1.insets = new Insets(5, 5, 5, 5);
            pickoptionpanel.add(option2Radio,c1);
            c1.gridx=0;
            c1.gridy=4;
            c1.insets = new Insets(5, 5, 5, 5);
            pickoptionpanel.add(option3Radio,c1);
            c1.gridx=0;
            c1.gridy=5;
            c1.insets = new Insets(0, 0, 0, 0);
            c1.anchor = GridBagConstraints.CENTER;
            pickoptionpanel.add(pickoptionSubmit,c1);
            c1.gridx=0;
            c1.gridy=6;
            c1.insets = new Insets(120, 60, 0, 60);
            pickoptionpanelresultlabel.setBorder(border);
            pickoptionpanel.add(pickoptionpanelresultlabel,c1);

            //--------FINAL page----

            FinalnameLabel.setBorder(border);
            FinalnameLabel.setBackground(Color.orange);
            finalPanelTextField1.setBackground(Color.yellow);
            finalPanelTextField1.setBorder(border);
            finalPanelTextField2.setBackground(Color.pink);
            finalPanelTextField2.setBorder(border);
            finalPanelTextField1.setLineWrap(true);
            finalPanelTextField2.setLineWrap(true);
            c1.gridx=0;
            c1.gridy = GridBagConstraints.RELATIVE;
            c1.insets = new Insets(2, 2, 2, 2);
            finalPanel.add(FinalnameLabel,c1);
            finalPanel.add(finalPanellabel1,c1);
            c1.weightx=1.0;
            c1.weighty=1.0;
            finalPanelTextField1.setEditable(false);
            finalPanelTextField2.setEditable(false);
            c1.fill = GridBagConstraints.BOTH;
            finalPanel.add(finalPanelTextField1,c1);
            c1.weightx=0;
            c1.weighty=0;
            finalPanel.add(finalPanellabel2,c1);
            c1.weightx=1.0;
            c1.weighty=1.0;
            c1.fill = GridBagConstraints.BOTH;
            finalPanel.add(finalPanelTextField2,c1);
            c1.weightx=0;
            c1.weighty=0;
            finalPanel.add(finalPanelNextRoundButton,c1);
            finalPanel.add(finalPanelresultlabel,c1);
            gamePanel1suggestionText.addActionListener(e);
            finalPanelNextRoundButton.addActionListener(e);
            this.addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    out.println("LOGOUT--");
                }
            });

        }
        public class event implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e)  {
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
                                            startGamebutton.setEnabled(true);
                                            break;
                                        }
                                    }
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }).start();


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
                    gameToken = joinkeyText.getText();
                    out.println(text);
                    try {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String serverMessage="";
                                try {
                                    while ((serverMessage = in.readLine()) != null) {
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

                                        System.out.println(serverMessage);
                                        if(serverMessage.contains("NEWGAMEWORD")) {
                                            layout.show(Mainpanel, "gamepanel1");

                                            String s1 = serverMessage.substring(13);
                                            int index = s1.indexOf('-');
                                            s1 = s1.substring(0,index);
                                            gamePanel1TextField.setText(s1);
                                            break;
                                        }

                                    }
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }).start();

                    }
                    catch (Exception e1){

                        e1.printStackTrace();
                    }
                }
                if(e.getSource()==startGamebutton)
                {
                    String text = "ALLPARTICIPANTSHAVEJOINED--" + userToken +"--" +gameToken;
                    out.println(text);
                    layout.show(Mainpanel, "gamepanel1");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String serverMessage="";
                            try {
                                while ((serverMessage = in.readLine()) != null) {


                                    System.out.println(serverMessage);
                                    if(serverMessage.contains("USERNOTLOGGEDIN")) {
                                        resultlabel3.setText("error,invalid usertoken!");
                                    }
                                    else if(serverMessage.contains("USERNOTGAMELEADER")) {
                                        resultlabel3.setText("User already playing the game!");
                                    }
                                    else if(serverMessage.contains("INVALIDGAMETOKEN")){
                                        resultlabel3.setText("invalid game token!");
                                    }
                                    else if(serverMessage.contains("NEWGAMEWORD")){
                                        String s1 = serverMessage.substring(13);
                                        int index = s1.indexOf('-');
                                        s1 = s1.substring(0,index);
                                        gamePanel1TextField.setText(s1);
                                        resultlabel3.setText("invalid game token!");

                                        break;
                                    }



                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                }
                if(e.getSource()==gamePanel1submit) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String serverMessage="";
                            try {

                                String mySuggestion = "PLAYERSUGGESTION--" + userToken + "--" + gameToken + "--" + gamePanel1suggestionText.getText();

                                //else{
                                // gamePanel1submit.setEnabled(false);
                                // }
                                out.println(mySuggestion);
                                while ((serverMessage = in.readLine()) != null) {

                                    if (serverMessage.contains("USERNOTLOGGEDIN")) {
                                        gamePanel1resultlabel.setText("error,invalid usertoken!");
                                    } else if (serverMessage.contains("INVALIDGAMETOKEN")) {
                                        gamePanel1resultlabel.setText("invalid game token!");
                                    } else if (serverMessage.contains("UNEXPECTEDMESSAGETYPE")) {
                                        gamePanel1resultlabel.setText("unexpected message type!");
                                    }
                                    else if (serverMessage.contains("INVALIDMESSAGEFORMAT")) {
                                        gamePanel1resultlabel.setText("Invalid message format!");
                                    }
                                    else if(serverMessage.contains("ROUNDOPTIONS")){
                                        layout.show(Mainpanel, "pickoptionpanel");
                                        System.out.println(serverMessage);
                                        String s1 = serverMessage.substring(14);
                                        int index1 = s1.indexOf('-');
                                        String option1 = s1.substring(0,index1);
                                        System.out.println(option1);
                                        s1= s1.substring(index1+2);
                                        index1=s1.indexOf('-');
                                        String option2 = s1.substring(0,index1);
                                        System.out.println(option2);
                                        s1= s1.substring(index1+2);
                                        String option3 = s1.substring(0);
                                        Random random = new Random();
                                        int ranInt = random.nextInt(3);
                                        if(ranInt==0) {
                                            option1Radio.setText(option1);
                                            int ranInt1 = random.nextInt(2);
                                            if(ranInt1==0){
                                                option2Radio.setText(option2);
                                                option3Radio.setText(option3);
                                            }
                                            else{
                                                option3Radio.setText(option2);
                                                option2Radio.setText(option3);

                                            }
                                        }
                                        else if(ranInt==1) {
                                            option1Radio.setText(option2);
                                            int ranInt1 = random.nextInt(2);
                                            if(ranInt1==0){
                                                option2Radio.setText(option1);
                                                option3Radio.setText(option3);
                                            }
                                            else{
                                                option3Radio.setText(option1);
                                                option2Radio.setText(option3);

                                            }
                                        }
                                        else if(ranInt==2) {
                                            option1Radio.setText(option3);
                                            int ranInt1 = random.nextInt(2);
                                            if(ranInt1==0){
                                                option2Radio.setText(option1);
                                                option3Radio.setText(option2);
                                            }
                                            else{
                                                option3Radio.setText(option1);
                                                option2Radio.setText(option2);

                                            }
                                        }
                                        break;





                                    }


                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                }
                if(e.getSource()==pickoptionSubmit){
                    FinalnameLabel.setText(loginnamelabel.getText());
                    String choice =  "";
                    if(option1Radio.isSelected())
                        choice = option1Radio.getText();
                    else if(option2Radio.isSelected())
                        choice = option2Radio.getText();
                    else if(option3Radio.isSelected())
                        choice = option3Radio.getText();
                    System.out.println(choice);
                    String msg = "PLAYERCHOICE"+ "--"+ userToken + "--"+ gameToken+"--"+choice;
                    out.println(msg);

                    new Thread(new Runnable()
                    {
                        @Override
                        public void run() {
                            String serverMessage;
                            try {
                                int count =0;
                                while ((serverMessage = in.readLine()) != null) {
                                    if (serverMessage.contains("USERNOTLOGGEDIN")) {
                                        pickoptionpanelresultlabel.setText("error,invalid usertoken!");
                                    } else if (serverMessage.contains("INVALIDGAMETOKEN")) {
                                        pickoptionpanelresultlabel.setText("invalid game token!");
                                    } else if (serverMessage.contains("UNEXPECTEDMESSAGETYPE")) {
                                        pickoptionpanelresultlabel.setText("unexpected message type!");
                                    } else if (serverMessage.contains("INVALIDMESSAGEFORMAT")) {
                                        pickoptionpanelresultlabel.setText("Invalid message format!");
                                    }
                                    else if (serverMessage.contains("NEWGAMEWORD")) {



                                    }else if (serverMessage.contains("GAMEOVER")) {

                                        finalPanelNextRoundButton.setEnabled(false);

                                    }
                                    else if (serverMessage.contains("ROUNDRESULT")) {

                                        layout.show(Mainpanel, "finalPanel");
                                        String overallresult = "";
                                        String s = serverMessage;
                                        s = s.substring(13);
                                    /*if (s.contains("fooled")) {
                                        int index = s.indexOf(".");
                                        String sentence1 = s.substring(0, index);
                                        s = s.substring(index + 1);
                                        index = s.indexOf(".");
                                        String sentence2 = s.substring(0, index);
                                        s = s.substring(index + 3);
                                        index = s.indexOf("-");
                                        String score1 = s.substring(0, index);
                                        s = s.substring(index + 2);
                                        index = s.indexOf("-");
                                        String fooled1 = s.substring(0, index);
                                        s = s.substring(index + 2);
                                        index = s.indexOf("-");
                                        String fooledby1 = s.substring(0, index);
                                        s = s.substring(index + 2);
                                        index = s.indexOf(".");
                                        String sentence3 = s.substring(index);
                                        s = s.substring(index + 3);
                                        index = s.indexOf("-");
                                        String score2 = s.substring(0, index);
                                        s = s.substring(index + 2);
                                        index = s.indexOf("-");
                                        String fooled2 = s.substring(0, index);
                                        s = s.substring(index + 2);
                                        String fooledby2 = s;
                                        String currentusername = "";
                                        String guestusername = "";
                                        if (sentence3.contains(loginnamelabel.getText())) {
                                            finalPanelTextField1.setText(sentence3);
                                            guestusername = loginnamelabel.getText();
                                        }
                                        if (sentence1.contains(loginnamelabel.getText())) {
                                            finalPanelTextField1.setText(sentence1 + sentence2);
                                            currentusername = loginnamelabel.getText();
                                        }
                                        overallresult += currentusername + "==>Score" + score1 + "fooled: " + fooled1 + "fooledby:" + fooledby1 + "\n" +
                                                guestusername + "==>Score" + score2 + "fooled: " + fooled2 + "fooledby:" + fooledby2 + "\n";

                                        finalPanelTextField2.setText(overallresult);

                                    }*/
                                        String ss = serverMessage.substring(13);
                                        if(ss.contains(loginnamelabel.getText()+"--You got it right!")){
                                            finalPanelTextField1.setText(loginnamelabel.getText()+"--You got it right!");
                                            if(ss.contains(loginnamelabel+"--You got it right!.You fooled "))
                                                finalPanelTextField1.setText(loginnamelabel.getText()+"--You got it right! you fooled your opponent.");
                                        }
                                        if(ss.contains(loginnamelabel+"--You were fooled by"))
                                            finalPanelTextField1.setText(loginnamelabel.getText()+"--You were fooled by your opponent!");
                                        if(ss.contains(loginnamelabel+"--You fooled "))
                                            finalPanelTextField1.setText(loginnamelabel.getText()+"--You fooled your opponent!");

                                        finalPanelTextField2.setText(serverMessage.substring(13));
                                    }
                                }
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }).start();

                }
                if(e.getSource()==finalPanelNextRoundButton){
                    //startGamebutton.doClick();
                    layout.show(Mainpanel,"gamepanel1");
                }


            }

        }
        public void close(){
            out.println("LOGOUT--");
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

