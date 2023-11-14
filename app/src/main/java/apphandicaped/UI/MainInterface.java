package apphandicaped.UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 


public class MainInterface {

    public static void changePanel(JFrame f, JPanel destPanel){
        f.getContentPane().removeAll();
        f.add(destPanel);
        //reloaod frame
        f.invalidate();
        f.repaint();
    }

    private static void createAndShowMainGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("main");

        //est ce que je met des pane dans le layeredPane ou c'est juste un seul pane ?
        JLayeredPane layeredPane = new JLayeredPane();

        JPanel pMain = new JPanel(new GridLayout(2,1));
        JPanel pLogin = new JPanel(new BorderLayout());
        JPanel pRegister = new JPanel(new BorderLayout());

        JButton bLogin=new JButton("Login");
        JButton bRegister=new JButton("Register");



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        //basic panel for login and register
        //affichage des panels login et register marche mal, il faut maximizer la fenetre pour voir les labels
        //changePanel fonctionne correctement, et les labels sont corrects
        JLabel loginLabel = new JLabel("Logging in");
        JLabel registerLabel = new JLabel("Register proceding");
        pLogin.add(loginLabel,BorderLayout.CENTER);
        pRegister.add(registerLabel,BorderLayout.CENTER);

        bLogin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    //switch to Login panel
                    changePanel(frame,pLogin);

                    }  
                });  

        bRegister.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    //switch to Register panel
                    changePanel(frame,pRegister);
                }  
            }); 




        frame.add(layeredPane);
        frame.add(pMain);
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