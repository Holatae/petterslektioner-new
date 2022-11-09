package petterslektioner.numbergame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGameMainGUI extends Container {
    private JButton easyButton;
    private JButton mediumButton;
    private JButton hardButton;
    private JButton impossibleButton;

    private JLabel welcomeLabel;
    private JPanel mainPanel;


    public NumberGameMainGUI() {
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Show a Error window
                JOptionPane.showMessageDialog(null, "Easy Button Pressed");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setContentPane(new NumberGameMainGUI().mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
