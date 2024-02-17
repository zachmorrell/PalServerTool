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
import java.awt.Component;
import java.awt.GridLayout;

public class WorldPalEditor extends JPanel {

    private JLabel[] labels = new JLabel[64];
    private Component[] values = new Component[64];
    private JPanel settings_panel;

    private final String DIRECTORY = System.getProperty("user.dir");
    private final String FILE_NAME = "/PalWorldSettings.ini";
    
    // Controls the order of application operation.
    public WorldPalEditor() {
        // Creates and sets the panel.
        set_panel_layout();
        // Sets the read location to the current directory + ini file name.
        Map<String, String> file_data = read_file(DIRECTORY + FILE_NAME);
        // Populates the panel based on the loaded data.
        populate_panel(file_data);
    }

    // Creates and sets the layout of the panel.
    private void set_panel_layout() {
        settings_panel = new JPanel(new GridLayout(0, 2));

        JScrollPane scrollPane = new JScrollPane(settings_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new GridLayout());
        add(scrollPane);

    }

    // Reads the contents of the ini file.
    private Map<String, String> read_file(String file) {    
        FileReader fileReader = new FileReader(file);
        return new LinkedHashMap<>(fileReader.read_file_lines());    
    }

    // Populates the panel with it's requried swing components.
    private void populate_panel(Map<String, String> file_data) {
        try {
            // Index tracking to ease development.
            int index = 0;

            // Loop through hashmap of data.
            for (Map.Entry<String, String> entry : file_data.entrySet()) {

                // Console print of data loaded from ini file.
                System.out.println(index + ": " + entry.getKey() + ": " + entry.getValue());

                // Adds the label or key setting to a label array to display on the GUI.
                labels[index] = new JLabel(entry.getKey());

                // Checks the data type to either assign a checkbox or textfield.
                if(isBoolean(entry.getValue())) {
                    values[index] = new JCheckBox("", Boolean.parseBoolean(entry.getValue()));
                } else {
                    values[index] = new JTextField(entry.getValue());
                }

                // Adds the labels and values arrays to the settings panel.
                settings_panel.add(labels[index]);
                settings_panel.add(values[index]);
                // Increments the index by 1.
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // Returns true if a message contains either true or false, else false.
    private Boolean isBoolean(String msg) {
        return (msg.contains("True") || msg.contains("False")) ? true: false;
    }
}