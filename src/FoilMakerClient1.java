import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhengwu on 10/22/16.
 */
public class FoilMakerClient1 {
    private  JPanel jPanelMain;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    String []  message = {"Margherita", "Prosciutto","Diavola","Verdure","Calzone"};
    private JComboBox comboBox1 = new JComboBox(message);
    private JCheckBox garlicCheckBox;
    private JCheckBox jalapenosCheckBox;
    private JCheckBox extraCheeseCheckBox;
    private JCheckBox baconCheckBox;
    private JButton submitButton;
    private JTextArea textArea1;
    private JPasswordField passwordField1;

    public FoilMakerClient1() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("I am farking clicked");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FoilMakerClient1");

        frame.setContentPane( new FoilMakerClient1().jPanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
