package apphandicaped.UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

import apphandicaped.controller.ChangePanelController;


public class MainPanel {

    public MainPanel() {


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

        pMain.add(bLogin);
        pMain.add(bRegister);

        
    }
}