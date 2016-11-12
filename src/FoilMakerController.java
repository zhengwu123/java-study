import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * Created by zhengwu on 11/11/16.
 */
public class FoilMakerController {
    private FoilMakerClient view;
    private FoilMakerModel model;
    PrintWriter out;
    BufferedReader in;
    FoilMakerController(FoilMakerClient view, FoilMakerModel model){
        this.view = view;
        this.model = model;
        String serverIP = "localhost";


    }

    public FoilMakerClient getView(){
        return  view;
    }


    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)  {
            if(e.getSource()==view.Registerbutton){
                String text = "CREATENEWUSER--" + view.nameField.getText() +"--"+ view.passwordField.getText();
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS"))
                        view.resultlabel.setText("success,user created!");
                    if(serverMessage.contains("INVALIDMESSAGEFORMAT"))
                        view.resultlabel.setText("error,invalid format!");
                    if(serverMessage.contains("INVALIDUSERNAME"))
                        view.resultlabel.setText("username can't be empty!");
                    if(serverMessage.contains("INVALIDUSERPASSWORD"))
                        view.resultlabel.setText("password can't be empty!");
                    if(serverMessage.contains("USERALREADYEXISTS"))
                        view.resultlabel.setText("error,user already exist!");
                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==view.Loginbutton){
                String text = "LOGIN--" + view.nameField.getText() +"--"+ view.passwordField.getText();
                out.println(text);
                try {
                    String serverMessage = in.readLine();
                    if(serverMessage.contains("SUCCESS")) {
                        view.resultlabel1.setText("success,user logged in!");
                        view.setUserToken(serverMessage.substring(26));
                        view.loginnamelabel.setText(view.nameField.getText());
                        view.loginnamelabel1.setText(view.nameField.getText());
                        view.messageLabel.setText("Others should use this key join your game,"+view.nameField.getText());
                        view.joinname.setText(view.nameField.getText());
                        view.layout.show(view.Mainpanel,"startpanel");

                    }
                    if(serverMessage.contains("INVALIDMESSAGEFORMAT"))
                        view.resultlabel1.setText("error,invalid format!");
                    if(serverMessage.contains("UNKNOWNUSER"))
                        view.resultlabel1.setText("username not registered!");
                    if(serverMessage.contains("INVALIDUSERPASSWORD"))
                        view.resultlabel1.setText("invalid password!");
                    if(serverMessage.contains("USERALREADYLOGGEDIN"))
                        view.resultlabel1.setText("error,user already logged in!");
                }
                catch (
                        IOException error
                        )

                {
                    error.printStackTrace();
                }
            }
            if(e.getSource()==view.startNewGameButton)
            {
                String text = "STARTNEWGAME--" + view.getUserToken();
                out.println(text);
                try {
                    //String serverMessage = in.readLine();
                    String serverMessage = "";
                    while((serverMessage=in.readLine())!=null) {
                        System.out.println(serverMessage);

                        if (serverMessage.contains("SUCCESS")) {
                            System.out.println("get in");
                            view.resultlabel2.setText("success,gamed created!");
                            view.setGameToken(serverMessage.substring(33));
                            System.out.println(view.getUserToken());
                            view.loginnamelabel.setText(view.nameField.getText());
                            view.keylabel.setText("                                   " + view.getGameToken());
                            break;
                        }
                        if (serverMessage.contains("USERNOTLOGGEDIN"))
                            view.resultlabel2.setText("error,invalid usertoken!");
                        if (serverMessage.contains("FAILURE"))
                            view.resultlabel2.setText("created game failed!");
                        if (serverMessage.contains("NEWPARTICIPANT")) {
                            String partitipantname = serverMessage.substring(16);
                            view.participateText.append("--> " + partitipantname + "\n");
                            view.resultlabel2.setText(partitipantname + "joined game!");
                            System.out.println("new participant");
                        }
                    }
                    view.layout.show(view.Mainpanel, "waiting");
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
                                        view.resultlabel2.setText("success,gamed created!");
                                        view.setGameToken( serverMessage.substring(33));
                                        view.layout.show(view.Mainpanel, "waiting");
                                        view.loginnamelabel.setText(view.nameField.getText());
                                        view.keylabel.setText("                                   " + view.getGameToken());
                                        break;

                                    }
                                    if (serverMessage.contains("USERNOTLOGGEDIN"))
                                        view.resultlabel2.setText("error,invalid usertoken!");
                                    if (serverMessage.contains("FAILURE"))
                                        view.resultlabel2.setText("created game failed!");
                                    if (serverMessage.contains("NEWPARTICIPANT")) {
                                        String partitipantname = serverMessage.substring(16);
                                        view.participateText.append("--> " + partitipantname + "\n");
                                        view.resultlabel2.setText(partitipantname + "joined game!");
                                        System.out.println("new participant");
                                        view.startGamebutton.setEnabled(true);
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
            if(e.getSource()==view.JoinGameButton)
            {
                view.layout.show(view.Mainpanel,"joinGamePanel");

            }
            if(e.getSource()==view.joinButton)
            {
                String text = "JOINGAME--" + view.getUserToken() +"--" +view.joinkeyText.getText();
                view.setGameToken(view.joinkeyText.getText());
                out.println(text);
                try {

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String serverMessage="";
                            try {
                                while ((serverMessage = in.readLine()) != null) {
                                    if(serverMessage.contains("SUCCESS")) {
                                        view.resultlabel3.setText("joined a game!");
                                        view.joinname.setText(view.nameField.getText());
                                        view.layout.show(view.Mainpanel,"joinGamePanelwaiting");

                                    }
                                    if(serverMessage.contains("USERNOTLOGGEDIN"))
                                        view.resultlabel3.setText("error,invalid usertoken!");
                                    if(serverMessage.contains("FAILURE"))
                                        view.resultlabel3.setText("created game failed!");
                                    if(serverMessage.contains("GAMEKEYNOTFOUND"))
                                        view.resultlabel3.setText("invalid game token!");

                                    System.out.println(serverMessage);
                                    if(serverMessage.contains("NEWGAMEWORD")) {
                                        view.layout.show(view.Mainpanel, "gamepanel1");

                                        String s1 = serverMessage.substring(13);
                                        int index = s1.indexOf('-');
                                        s1 = s1.substring(0,index);
                                        view.gamePanel1TextField.setText(s1);
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
            if(e.getSource()==view.startGamebutton)
            {
                String text = "ALLPARTICIPANTSHAVEJOINED--" + view.getUserToken() +"--" +view.getGameToken();
                out.println(text);
                view.layout.show(view.Mainpanel, "gamepanel1");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String serverMessage="";
                        try {
                            while ((serverMessage = in.readLine()) != null) {


                                System.out.println(serverMessage);
                                if(serverMessage.contains("USERNOTLOGGEDIN")) {
                                    view.resultlabel3.setText("error,invalid usertoken!");
                                }
                                else if(serverMessage.contains("USERNOTGAMELEADER")) {
                                    view.resultlabel3.setText("User already playing the game!");
                                }
                                else if(serverMessage.contains("INVALIDGAMETOKEN")){
                                    view.resultlabel3.setText("invalid game token!");
                                }
                                else if(serverMessage.contains("NEWGAMEWORD")){
                                    String s1 = serverMessage.substring(13);
                                    int index = s1.indexOf('-');
                                    s1 = s1.substring(0,index);
                                    view.gamePanel1TextField.setText(s1);
                                    view.resultlabel3.setText("invalid game token!");

                                    break;
                                }



                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();

            }
            if(e.getSource()==view.gamePanel1submit) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String serverMessage="";
                        try {

                            String mySuggestion = "PLAYERSUGGESTION--" + view.getUserToken() + "--" + view.getGameToken() + "--" + view.gamePanel1suggestionText.getText();
                            out.println(mySuggestion);
                            while ((serverMessage = in.readLine()) != null) {

                                if (serverMessage.contains("USERNOTLOGGEDIN")) {
                                    view.gamePanel1resultlabel.setText("error,invalid usertoken!");
                                } else if (serverMessage.contains("INVALIDGAMETOKEN")) {
                                    view.gamePanel1resultlabel.setText("invalid game token!");
                                } else if (serverMessage.contains("UNEXPECTEDMESSAGETYPE")) {
                                    view.gamePanel1resultlabel.setText("unexpected message type!");
                                }
                                else if (serverMessage.contains("INVALIDMESSAGEFORMAT")) {
                                    view.gamePanel1resultlabel.setText("Invalid message format!");
                                }
                                else if(serverMessage.contains("ROUNDOPTIONS")){
                                    view. layout.show(view.Mainpanel, "pickoptionpanel");
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
                                        view.option1Radio.setText(option1);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view. option2Radio.setText(option2);
                                            view.option3Radio.setText(option3);
                                        }
                                        else{
                                            view. option3Radio.setText(option2);
                                            view.option2Radio.setText(option3);

                                        }
                                    }
                                    else if(ranInt==1) {
                                        view.option1Radio.setText(option2);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view. option2Radio.setText(option1);
                                            view. option3Radio.setText(option3);
                                        }
                                        else{
                                            view. option3Radio.setText(option1);
                                            view.option2Radio.setText(option3);

                                        }
                                    }
                                    else if(ranInt==2) {
                                        view.option1Radio.setText(option3);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view.option2Radio.setText(option1);
                                            view.option3Radio.setText(option2);
                                        }
                                        else{
                                            view.option3Radio.setText(option1);
                                            view.option2Radio.setText(option2);

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
            if(e.getSource()==view.pickoptionSubmit){
                view.FinalnameLabel.setText(view.loginnamelabel.getText());
                String choice =  "";
                if(view.option1Radio.isSelected())
                    choice = view.option1Radio.getText();
                else if(view.option2Radio.isSelected())
                    choice = view.option2Radio.getText();
                else if(view.option3Radio.isSelected())
                    choice = view.option3Radio.getText();
                System.out.println(choice);
                String msg = "PLAYERCHOICE"+ "--"+ view.getUserToken() + "--"+ view.getGameToken()+"--"+choice;
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
                                    view.pickoptionpanelresultlabel.setText("error,invalid usertoken!");
                                } else if (serverMessage.contains("INVALIDGAMETOKEN")) {
                                    view.pickoptionpanelresultlabel.setText("invalid game token!");
                                } else if (serverMessage.contains("UNEXPECTEDMESSAGETYPE")) {
                                    view.pickoptionpanelresultlabel.setText("unexpected message type!");
                                } else if (serverMessage.contains("INVALIDMESSAGEFORMAT")) {
                                    view.pickoptionpanelresultlabel.setText("Invalid message format!");
                                }
                                else if (serverMessage.contains("NEWGAMEWORD") ) {
                                    //if(e.getSource()==finalPanelNextRoundButton)
                                    //layout.show(Mainpanel,"gamepanel1");
                                    String s1 = serverMessage.substring(13);
                                    int index = s1.indexOf('-');
                                    s1 = s1.substring(0,index);
                                    view.gamePanel1TextField.setText(s1);
                                    break;
                                }

                                else if(serverMessage.contains("ROUNDOPTIONS")){
                                    view.layout.show(view.Mainpanel, "pickoptionpanel");
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
                                        view.option1Radio.setText(option1);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view.option2Radio.setText(option2);
                                            view.option3Radio.setText(option3);
                                        }
                                        else{
                                            view.option3Radio.setText(option2);
                                            view.option2Radio.setText(option3);

                                        }
                                    }
                                    else if(ranInt==1) {
                                        view.option1Radio.setText(option2);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view.option2Radio.setText(option1);
                                            view.option3Radio.setText(option3);
                                        }
                                        else{
                                            view.option3Radio.setText(option1);
                                            view.option2Radio.setText(option3);

                                        }
                                    }
                                    else if(ranInt==2) {
                                        view.option1Radio.setText(option3);
                                        int ranInt1 = random.nextInt(2);
                                        if(ranInt1==0){
                                            view.option2Radio.setText(option1);
                                            view.option3Radio.setText(option2);
                                        }
                                        else{
                                            view.option3Radio.setText(option1);
                                            view.option2Radio.setText(option2);

                                        }
                                    }
                                    // break;




                                }else if (serverMessage.contains("GAMEOVER")) {

                                    view.finalPanelNextRoundButton.setEnabled(false);

                                }
                                else if (serverMessage.contains("ROUNDRESULT")) {

                                    view.layout.show(view.Mainpanel, "finalPanel");
                                    String overallresult = "";
                                    String ss = serverMessage.substring(13);
                                    if(ss.contains(view.loginnamelabel.getText()+"--You got it right!")){
                                        view.finalPanelTextField1.setText(view.loginnamelabel.getText()+"--You got it right!");
                                        if(ss.contains(view.loginnamelabel+"--You got it right!.You fooled "))
                                            view.finalPanelTextField1.setText(view.loginnamelabel.getText()+"--You got it right! you fooled your opponent.");
                                    }
                                    if(ss.contains(view.loginnamelabel+"--You were fooled by"))
                                        view.finalPanelTextField1.setText(view.loginnamelabel.getText()+"--You were fooled by your opponent!");
                                    if(ss.contains(view.loginnamelabel+"--You fooled "))
                                        view.finalPanelTextField1.setText(view.loginnamelabel.getText()+"--You fooled your opponent!");


                                    String fs = serverMessage.substring(13);
                                    StringBuilder sb = new StringBuilder();
                                    for( int i=0;i<fs.length()-1;i++){
                                        if(Character.isLetter(fs.charAt(i))||fs.charAt(i)=='.'||fs.charAt(i)=='!'||fs.charAt(i)==' ')
                                            continue;
                                        if(fs.charAt(i)==fs.charAt(i+1)&& fs.charAt(i)=='-')
                                            continue;
                                        sb.append(fs.charAt(i));

                                    }
                                    sb.append(fs.charAt(fs.length()-1));
                                    String result = "(Host score)(fooled)(foolby), (Guest score)(fooled)(foolby).\n";
                                    view.finalPanelTextField2.setText(result+sb.toString());
                                }
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }).start();

            }
            if(e.getSource()==view.finalPanelNextRoundButton){
                //startGamebutton.doClick();
                view.layout.show(view.Mainpanel,"gamepanel1");
            }


        }
        public void close(){
            out.println("LOGOUT--");
        }
    }
}
