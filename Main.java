
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            Controller window = new Controller();
            window.frame.setVisible(true);
            window.frame.setResizable(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
