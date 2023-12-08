package apphandicaped.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainInterfaceController {

    private static MainInterfaceController instance;
     public JFrame frame ;
     public JPanel lowerPanel ;
     public JPanel upperPanel; 
     public JPanel pLogin ;
     public JPanel pRegister ;
     public JPanel pMissionInterface ;
     public JPanel pFirstPanel ;

     public JButton bHome;

    public MainInterfaceController() {
        super();
    }
    public static synchronized MainInterfaceController getInstance() {
        if (instance == null){
            instance = new MainInterfaceController();
        } 
            return instance;
    }
    public void changePanel(Container container, String panelName) {
        CardLayout cardLayout = (CardLayout) ((JPanel) container).getLayout();
        cardLayout.show((JPanel) container, panelName);
    }
    public void changeLowerPanel(String panelName){
        changePanel(lowerPanel, panelName);
    }

    private void createAndShowMainGUI() throws SQLException {
        // Create and set up the window.

        JFrame frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Upper panel
        upperPanel = new JPanel();
        bHome = new JButton("Home");
        JLabel titleLabel = new JLabel("Welcome to our App");
        upperPanel.add(titleLabel);
        upperPanel.add(bHome);

        // Lower panel
        lowerPanel = new JPanel(new CardLayout(2, 1));

        pLogin = new LoginInterface();
        pLogin.setLayout(null);

        pRegister = new RegisterInterface();

        pMissionInterface = new MissionInterface();

        pFirstPanel = new JPanel();

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

        pFirstPanel.add(bLogin);
        pFirstPanel.add(bRegister);

        // Add panels to the frame
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(lowerPanel, BorderLayout.CENTER);

        // Add login and register panels to the CardLayout
        CardLayout cardLayout = (CardLayout) lowerPanel.getLayout();
        lowerPanel.add(pFirstPanel, "home");
        lowerPanel.add(pLogin, "login");
        lowerPanel.add(pRegister, "register");
        lowerPanel.add(pMissionInterface, "missionInterface");

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        MainInterfaceController MIC = MainInterfaceController.getInstance();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MIC.createAndShowMainGUI();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
