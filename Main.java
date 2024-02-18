import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {
    static JFrame window;
    static int app_width = 550;
    static int app_height = 550;
    public static void main(String[] args) {
        
        // Handles the creation of the window.
        setup_window();

        // Adds the contentpane to the window.
        window.getContentPane().add(new WorldPalEditor());


        // Make the frame visible.
        window.setVisible(true);
    }

    private static void setup_window() {

        // Create the JFrame.
        window = new JFrame("Pal Helper");

        // End program and process when closed.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the size of the JFrame.
        window.setPreferredSize(new Dimension(app_width,app_height));

        // Centers the application's location on opening.
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(screen.width / 2 - window.getSize().width/2 - (app_width / 2), screen.height / 2 - window.getSize().height / 2 - (app_height / 2));
        
        // Builds the changes to the application.
        window.pack();
    }
}