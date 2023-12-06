package apphandicaped.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainInterface {

    public static void changePanel(Container container, String panelName) {
        CardLayout cardLayout = (CardLayout) ((JPanel) container).getLayout();
        cardLayout.show((JPanel) container, panelName);
    }

    private static void createAndShowMainGUI() {
        // Create and set up the window.

        JFrame frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Upper panel
        JPanel upperPanel = new JPanel();
        JButton bHome = new JButton("Home");
        JLabel titleLabel = new JLabel("Welcome to My App");
        upperPanel.add(titleLabel);
        upperPanel.add(bHome);

        // Lower panel
        JPanel lowerPanel = new JPanel(new CardLayout(2, 1));

        JPanel pLogin = LoginInterface.SimpleInterface();
        pLogin.setLayout(null);

        JPanel pRegister = new RegisterInterface();
        JPanel firstPanel = new JPanel();

        JButton bLogin = new JButton("Login");
        JButton bRegister = new JButton("Register");

        // Add panels to the CardLayout
        // frame.add(frame, "main");
        // frame.add(pLogin, "login");
        // frame.add(pRegister, "register");
        bHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePanel(lowerPanel, "home");
            }
        });

        bLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // switch to Login panel
                changePanel(lowerPanel, "login");
            }
        });

        bRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // switch to Register panel
                changePanel(lowerPanel, "register");
            }
        });

        firstPanel.add(bLogin);
        firstPanel.add(bRegister);

        // Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(lowerPanel, BorderLayout.CENTER);

        // Add login and register panels to the CardLayout
        CardLayout cardLayout = (CardLayout) lowerPanel.getLayout();
        lowerPanel.add(firstPanel, "home");
        lowerPanel.add(pLogin, "login");
        lowerPanel.add(pRegister, "register");

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowMainGUI();
            }
        });
    }
}