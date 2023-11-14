package apphandicaped.UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 


public class MainInterface {

    private static void createAndShowMainGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("main");

        //est ce que je met des pane dans le layeredPane ou c'est juste un seul pane ?
        JLayeredPane layeredPane = new JLayeredPane();

        JPanel pMain = new JPanel(new GridLayout(2,1));
        JPanel pLogin = new JPanel();
        JPanel pRegister = new JPanel();

        JButton bLogin=new JButton("Login");
        JButton bRegister=new JButton("Register");



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        bLogin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //switch to Login page
                    }  
                });  

        bLogin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                        //switch to Register page
                }  
            }); 




        frame.add(layeredPane);
        layeredPane.add(pMain);
        pMain.add(bLogin);
        pMain.add(bRegister);
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