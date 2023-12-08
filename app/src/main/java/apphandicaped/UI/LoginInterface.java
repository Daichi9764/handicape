package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import apphandicaped.controller.*;

import javax.swing.*;

public class LoginInterface extends JPanel {
   static LoginInterface instance;

   public static synchronized LoginInterface getInstance() {
      if (instance == null) {
         instance = new LoginInterface();
      }
      return instance;
   }

   public LoginInterface() {

      //
      JTextField firstnameTextField = new JTextField();
      firstnameTextField.setBounds(50, 50, 150, 20);
      JTextField lastnameTextField = new JTextField();
      lastnameTextField.setBounds(50, 100, 150, 20);
      JPasswordField passwordTextField = new JPasswordField();
      passwordTextField.setBounds(50, 150, 150, 20);

      JButton loginButton = new JButton("Login");// creating instance of JButton
      loginButton.setBounds(130, 200, 150, 40);// x axis, y axis, width, height

      this.add(firstnameTextField);
      this.add(lastnameTextField);
      this.add(passwordTextField);
      this.add(loginButton);

      try {
         Controller controller = Controller.getInstance();

         loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
               LoginInterface interface1 = LoginInterface.getInstance();
               MainInterfaceController MIC = MainInterfaceController.getInstance();
               String firstnameText = firstnameTextField.getText();
               String lastnameText = lastnameTextField.getText();
               char[] pwdText = passwordTextField.getPassword();
               try {
                  Controller.loginResult result = controller.CheckTokens(firstnameText, lastnameText, pwdText);
                  switch (result) {
                     case NotExist:
                        JOptionPane.showMessageDialog(interface1, "User does not exist");
                        break;
                     case Connected:
                        JOptionPane.showMessageDialog(interface1, "Connection Successful");
                        MIC.changeLowerPanel("missionInterface");

                        break;
                     case BadPassword:
                        JOptionPane.showMessageDialog(interface1, "Wrong Password");
                        break;

                     default:
                        break;
                  }
               } catch (Exception e) {
                  JOptionPane.showMessageDialog(interface1, "Problem connecting to the database, please restart the app");
                  e.printStackTrace();
               }

            }
         });

      } catch (Exception e) {
         JOptionPane.showMessageDialog(this, "Problem connecting to the database, please restart the app");
      }

   }

   public static void main(String[] args) {
      JFrame frame = new JFrame("Login Interface");
      JPanel pLogin;

      pLogin = new LoginInterface();
      pLogin.setLayout(null);

      frame.getContentPane().add(pLogin);
      frame.setSize(400, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);

      // Using a layout manager (FlowLayout) instead of null layout
   }
}
