/*
 *  Creates a GUI to load and update the WorldPalSettings.ini
 */

import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorldPalEditor extends JPanel {
    private JLabel status;
    private Map<String, String> file_data;
    public WorldPalEditor() {
        //setLayout(null);
        status = new JLabel("Awaiting attemp.");
        add(status);
        read_file("E:\\Programming\\PalServerTool\\PalWorldSettings.ini");
    }

    // Reads the contents of the ini file.
    private void read_file(String file){
        FileReader fileReader = new FileReader(file);
        try {
            file_data = new LinkedHashMap<>(fileReader.read_file_lines());
            int index = 0;
            for (Map.Entry<String, String> entry : file_data.entrySet()) {
                data_to_gui(index, entry);
                index++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void data_to_gui(int index, Map.Entry<String, String> entrySet) {
        switch (index) {
            case 0:
                status.setText(entrySet.getKey());
                break;
        
            default:
                System.out.println(index + ": " + entrySet.getKey() + ": " + entrySet.getValue());
                break;
        }
    }
}