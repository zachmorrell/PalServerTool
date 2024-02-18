/*
 *  Creates a GUI to load and update the WorldPalSettings.ini
 */

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPalEditor extends JPanel {

    private JPanel settings_panel = new JPanel();
    private JLabel[] labels = new JLabel[64];
    private Component[] values = new Component[64];
    private Button save;

    int grid_row = 0;

    private final String DIRECTORY = System.getProperty("user.dir");
    private final String FILE_NAME = "/PalWorldSettings.ini";

    GridBagConstraints gbc = new GridBagConstraints();
    JScrollPane scrollPane;
    
    // Controls the order of application operation.
    public WorldPalEditor() {
        // Creates and sets the panel.
        set_panel_layout();
        // Sets the read location to the current directory + ini file name.
        Map<String, String> file_data = read_file(DIRECTORY + FILE_NAME);
        // Populates the panel based on the loaded data.
        populate_panel(file_data);
        add_scroll_pane();
    }

    // Creates and sets the layout of the panel.
    private void set_panel_layout() {
        // Sets the panel layout to GridBagLayout.
        settings_panel.setLayout(new GridBagLayout());
    }

    private void add_scroll_pane() {
        // Places the settings panel into the scrollPane
        scrollPane = new JScrollPane(settings_panel);
    
        // Setting the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(400, 300)); // Adjust size as needed
    
        // Setting the scroll policy
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
        // Adding scroll pane to the JFrame.
        setLayout(new BorderLayout()); // Assuming your WorldPalEditor extends JPanel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Formats the layout based on position and width, then adds component to settings panel.
    private void apply_gbc(Component component, int gbc_x, int gbc_y, int gbc_width) {
        gbc.gridx = gbc_x;
        gbc.gridy = gbc_y;
        gbc.gridwidth = gbc_width;
        gbc.anchor = GridBagConstraints.WEST;
        if(component == save) {
            gbc.anchor = GridBagConstraints.CENTER;
        }
        settings_panel.add(component, gbc);
    }

    // Reads the contents of the ini file.
    private Map<String, String> read_file(String file) {    
        FileReader fileReader = new FileReader(file);
        return new LinkedHashMap<>(fileReader.read_file_lines());    
    }

    // Populates the panel with it's requried swing components.
    private void populate_panel(Map<String, String> file_data) {
        try {

            // Loop through the .ini file data and add it to the settings panel.
            for (Map.Entry<String, String> entry : file_data.entrySet()) {

                // Console print of data loaded from ini file.
                System.out.println(grid_row + ": " + entry.getKey() + ": " + entry.getValue());

                // Adds the label or key setting to a label array to display on the GUI.
                labels[grid_row] = new JLabel(entry.getKey());

                // Checks the data type to either assign a checkbox or textfield.
                if(isBoolean(entry.getValue())) {
                    values[grid_row] = new JCheckBox("", Boolean.parseBoolean(entry.getValue()));
                } else {
                    values[grid_row] = new JTextField(entry.getValue());
                    values[grid_row].setPreferredSize(new Dimension(130, 19));
                }

                // Adds the labels and values arrays to the settings panel.
                if(grid_row == 0 || grid_row == 63) {                    
                    apply_gbc(labels[grid_row], 0, grid_row, 2);
                } else {
                    apply_gbc(labels[grid_row], 0, grid_row, 1);
                    apply_gbc(values[grid_row], 1, grid_row, 1);
                }
                // Increments the index by 1. 
                grid_row++;
            }
            // Add the save button.
            save = new Button("Save");
            apply_gbc(save, 0, grid_row, 2);
            ButtonListener buttonListener = new ButtonListener();
            save.addActionListener(buttonListener);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Returns true if a message contains either true or false, else false.
    private Boolean isBoolean(String msg) {
        return (msg.contains("True") || msg.contains("False")) ? true: false;
    }
}
class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        try {                
            System.out.println("button clicked");
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}