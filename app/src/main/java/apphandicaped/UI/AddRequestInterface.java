package apphandicaped.UI;

import java.awt.*;
import javax.swing.*;

public class AddRequestInterface extends JPanel {
    protected JLabel requestLabel;
    protected JTextField requestTextField;
    protected JButton okButton;
    protected AddRequestButtonHandler addRequestButtonHandler;


    public AddRequestInterface () {
        requestLabel = new JLabel("Enter your request below");
        requestTextField = new JTextField(20);
        okButton = new JButton ("OK");

        addRequestButtonHandler = new AddRequestButtonHandler(okButton, this);

        setPreferredSize(new Dimension(450, 602));
        setLayout(new BorderLayout());

      add(requestLabel,BorderLayout.PAGE_START);
      add(requestTextField,BorderLayout.CENTER);
      add(okButton,BorderLayout.PAGE_END);



    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new AddRequestInterface());
        frame.pack();
        frame.setVisible(true);
     }
    
}
