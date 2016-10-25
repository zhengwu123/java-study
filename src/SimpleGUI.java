/**
 * Created by zhengwu on 10/24/16.
 */
import java.awt.*;
import java.awt.event.ActionEvent; import java.awt.event.ActionListener; import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class SimpleGUI extends JFrame { /* Main panel */
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();
    /* Panel 1 */
    JPanel panelFirst = new JPanel();
    JButton buttonOne = new JButton("Switch to Panel 2");
    /* Panel 2 */
    JPanel panelSecond = new JPanel();
    JButton buttonSecond = new JButton("Switch to Panel 1");

    public SimpleGUI() {
/* Add button to panel 1 */
        panelFirst.add(buttonOne);
        panelFirst.setBackground(Color.CYAN);
/* Add button to panel 2 */
        panelSecond.add(buttonSecond);
        panelSecond.setBackground(Color.ORANGE);
/* Add panel 1 and panel 2 to main panel */
        mainPanel.setLayout(layout);
        mainPanel.add(panelFirst, "1");
        mainPanel.add(panelSecond, "2");
/* Add action listener to button 1 on panel 1 */
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                layout.show(mainPanel, "2"); /* Show panel 2 when button clicked */
            }
        });
/* Add action listener to button 2 on panel 2 */
        buttonSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                layout.show(mainPanel, "1"); /* Show panel 1 when button clicked */
            }
        });
/* Set up frame */
/* Add main panel to GUI frame */
        add(mainPanel);
/* Set dimensions */
        setLocation(200, 200);
    }

    public static void main(String[] args) {
        SimpleGUI gui = new SimpleGUI();
        gui.pack();
        gui.setVisible(true);
    }
}