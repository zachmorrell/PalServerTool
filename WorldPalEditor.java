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
    
    private Map<String, String> file_data;
    public WorldPalEditor() {
        // Sets the read location to the current directory + ini file name.
        read_file(System.getProperty("user.dir") + "/PalWorldSettings.ini");
    }

    // Reads the contents of the ini file.
    private void read_file(String file) {
        JPanel world_panel = new JPanel(new GridLayout(0, 2));
    
        FileReader fileReader = new FileReader(file);
        try {
            file_data = new LinkedHashMap<>(fileReader.read_file_lines());
            int index = 0;
            for (Map.Entry<String, String> entry : file_data.entrySet()) {
                data_to_gui(index, entry);
                labels[index] = new JLabel(entry.getKey());
                if(isBoolean(entry.getValue())) {
                    values[index] = new JCheckBox("", Boolean.parseBoolean(entry.getValue()));
                } else {
                    values[index] = new JTextField(entry.getValue());
                }
                world_panel.add(labels[index]);
                world_panel.add(values[index]);
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    
        JScrollPane scrollPane = new JScrollPane(world_panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new GridLayout());
        add(scrollPane);
    }

    private Boolean isBoolean(String msg) {
        return (msg.contains("True") || msg.contains("False")) ? true: false;
    }

    private void data_to_gui(int index, Map.Entry<String, String> entrySet) {
        switch (index) {
        
            default:
                System.out.println(index + ": " + entrySet.getKey() + ": " + entrySet.getValue());
                break;
        }
    }
}