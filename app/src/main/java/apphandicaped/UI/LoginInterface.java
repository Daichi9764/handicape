package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginInterface {
   public static void SimpleInterface() {
      JFrame f = new JFrame();// creating instance of JFrame
                              //
      JTextField mailTextField = new JTextField();
      mailTextField.setBounds(50, 50, 150, 20);
      JTextField passwordTextField = new JTextField();
      passwordTextField.setBounds(50, 100, 150, 20);

      JButton loginButton = new JButton("Login");// creating instance of JButton
      loginButton.setBounds(130, 150, 150, 40);// x axis, y axis, width, height

      f.add(mailTextField);// adding button in JFrame
      f.add(passwordTextField);// adding button in JFrame
      f.add(loginButton);// adding button in JFrame

      loginButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            System.out.println(mailTextField.getText());
            System.out.println(passwordTextField.getText());
         }
      });

      f.setSize(400, 500);// 400 width and 500 height
      f.setLayout(null);// using no layout managers
      f.setVisible(true);// making the frame visible
   }

   public static void main(String[] args) {
      SimpleInterface();
   }
}
