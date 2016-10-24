/**
 * Created by zhengwu on 10/23/16.
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    JLabel label1;
    JLabel label2;
    JLabel label3 = new JLabel("Select your pizza style:");
    JLabel label4 = new JLabel("Choose your topping:");
    JRadioButton radioButton1 = new JRadioButton("Small");
    JRadioButton radioButton2 = new JRadioButton("Medium");
    JRadioButton radioButton3 = new JRadioButton("Large");
    JCheckBox checkBox1 = new JCheckBox("Garlic");
    JCheckBox checkBox2= new JCheckBox("Jalapenos");
    JCheckBox checkBox3= new JCheckBox("Extra cheese");
    JCheckBox checkBox4= new JCheckBox("Bacon");
    String[] comboStirng = {"Margherita","Prosciutto","Diavola","Verdure","Calzone"};
    JComboBox comboBox = new JComboBox(comboStirng);
    JButton button = new JButton("submit");
    JLabel outputLabel = new JLabel("output");
    JTextArea textArea = new JTextArea(15,15);
    Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
    ButtonGroup group = new ButtonGroup();
    String text ="Your custom pizza\n";
public GUI(){
    JPanel panel = new JPanel( new GridBagLayout());
    panel.setBorder(border);
    JPanel panel2 = new JPanel( new FlowLayout());
    panel2.setBorder(border);
    setLayout(new GridLayout(0,2));
    GridBagConstraints c = new GridBagConstraints();
    getContentPane().add(panel);
    getContentPane().add(panel2);
    c.anchor = GridBagConstraints.FIRST_LINE_START;
    c.insets = new Insets(5,5,5,5);
    c.anchor = GridBagConstraints.NORTHWEST;
    c.gridx =0;
    c.gridy=0;
    textArea.setBorder(border);
    label1 = new JLabel("Options.");
    panel.add(label1,c);
    label2 = new JLabel("Select your Pizza size:");
    c.gridx =0;
    c.gridy++;
    panel.add(label2,c);
    c.gridy=2;
    c.gridx=0;
    group.add(radioButton1);
    group.add(radioButton2);
    group.add(radioButton3);
    panel.add(radioButton1,c);
    c.gridy=2;
    c.gridx++;
    panel.add(radioButton2,c);
    c.gridy=2;
    c.gridx++;
    panel.add(radioButton3,c);
    c.gridy++;
    c.gridx=0;
    panel.add(label3,c);
    c.gridy++;
    c.gridx=0;
    panel.add(comboBox,c);
    c.gridy=6;
    c.gridx=0;
    panel.add(label4,c);
    c.gridy=7;
    c.gridx=0;
    panel.add(checkBox1,c);
    c.gridy=7;
    c.gridx++;
    panel.add(checkBox2,c);
    c.gridy=7;
    c.gridx++;
    panel.add(checkBox3,c);
    c.gridy=7;
    c.gridx++;
    panel.add(checkBox4,c);
    c.gridy=8;
    c.gridx=1;
    panel.add(button,c);
    c.gridy=1;
    c.gridx=3;
    panel2.add(outputLabel);
    c.gridy=3;
    c.gridx=3;
    panel2.add(textArea);
    event e = new event();

    button.addActionListener(e);
    radioButton1.addActionListener(e);
    radioButton2.addActionListener(e);
    radioButton3.addActionListener(e);
    checkBox1.addActionListener(e);
    checkBox2.addActionListener(e);
    checkBox3.addActionListener(e);
    checkBox4.addActionListener(e);
    comboBox.addActionListener(e);
}
    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button)
                textArea.setText(text);
            if(e.getSource()==radioButton1)
                text+= "Size:Small\n";
            if(e.getSource()==radioButton2)
                text+= "Size:Medium\n";
            if(e.getSource()==radioButton3)
                text+= "Size:Large\n";
            if(e.getSource()==comboBox)
                text+="Style:"+comboBox.getSelectedItem().toString()+"\n" +"Toppings:";
            if(e.getSource()==checkBox1)
                text+="Garlic";
            if(e.getSource()==checkBox2)
                text+="Jalapenos";
            if(e.getSource()==checkBox3)
                text+="Extra cheese";
            if(e.getSource()==checkBox4)
                text+="Bacon";

        }
    }
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setTitle("Pizza Shop");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    }
}
