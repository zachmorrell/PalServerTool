import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        // Create the JFrame.
        JFrame window = new JFrame("Pal Helper");
        // End program and process when closed.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Change the size of the JFrame.
        window.setPreferredSize(new Dimension(350,450));

        // Add the pane to the JFrame.
        window.getContentPane().add(new WorldPalEditor());
        
        // Builds the changes to the application.
        window.pack();
        // Make the frame visible.
        window.setVisible(true);
    }
}