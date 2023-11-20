package apphandicaped.UI;
import javax.swing.*;

public class Main {
        public static void main(String[] args) {
            // Schedule a job for the event-dispatching thread:
            // creating and showing this application's GUI.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MainInterface.createAndShowMainGUI();
                }
            });
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AWTInterface awt = new AWTInterface();

                }
            });
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    LoginInterface.SimpleInterface();

                }
            });
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    MainFrame frame = new MainFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(new RegisterPanel());
                    frame.pack();
                    frame.setVisible(true);

                }
            });
        }
}
