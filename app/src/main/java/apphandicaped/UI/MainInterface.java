package apphandicaped.UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 


public class MainInterface {

    public static void createAndShowMainGUI() {
        // Create and set up the window.
        MainFrame mframe = new MainFrame();

        JPanel pMain = new JPanel(new GridLayout(2,1));
        JPanel pLogin = new JPanel(new BorderLayout());
        JPanel pRegister = new JPanel(new BorderLayout());

        JButton bLogin=new JButton("Login");
        JButton bRegister=new JButton("Register");

        //basic panel for login and register
        JLabel loginLabel = new JLabel("Logging in");
        JLabel registerLabel = new JLabel("Register proceding");
        pLogin.add(loginLabel,BorderLayout.CENTER);
        pRegister.add(registerLabel,BorderLayout.CENTER);

        bLogin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    //switch to Login panel
                    mframe.changePanel(pLogin);

                    }  
                });  

        bRegister.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    //switch to Register panel
                    mframe.changePanel(pRegister);
                }  
            }); 

;
        mframe.add(pMain);
        pMain.add(bLogin);
        pMain.add(bRegister);
        mframe.setVisible(true);

        
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