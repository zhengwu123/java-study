/**
 * Created by zhengwu on 10/23/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIpractise1 extends JFrame {
    private JLabel label;
    private JButton button;
    private JButton button2;
    private JTextField textField;

    public GUIpractise1(){
    setLayout(new FlowLayout());
        label = new JLabel(" Hi, fark u, I am a farking label.");
        add(label);
        textField = new JTextField(15);
        add(textField);
        button = new JButton("click me!!!");
        add(button);
        button2 = new JButton("I am button 2");
        add(button2);
        event e = new event();
        button.addActionListener(e);
        button2.addActionListener(e);

    }
    public class event implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button)
                label.setText("I am farking being clicked by button");
            if(e.getSource()==button2)
                label.setText("I am farking being clicked by button2");
        }
    }
    public static void main(String[] args) {
        GUIpractise1 gui = new GUIpractise1();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
        gui.setTitle("Hello world!");
    }
}
