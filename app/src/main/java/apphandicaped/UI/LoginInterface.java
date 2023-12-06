package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginInterface {
   public static JPanel SimpleInterface() {
      JPanel f = new JPanel();// creating instance of JFrame
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
            String userText = mailTextField.getText();
            String pwdText = passwordTextField.getText();
            if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(f, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(f, "Invalid Username or Password");
            }
         }
      });
      return f;

   }

   public static void main(String[] args) {
      JFrame frame = new JFrame("Login Interface");
        JPanel pLogin = SimpleInterface();

        // Using a layout manager (FlowLayout) instead of null layout
        pLogin.setLayout(null);

        frame.getContentPane().add(pLogin);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
   }
}
