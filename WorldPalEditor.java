/*
 *  Creates a GUI to load and update the WorldPalSettings.ini
 */

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorldPalEditor extends JPanel {
    private JLabel status;
    private List<String> file_data;
    public WorldPalEditor() {
        //setLayout(null);
        status = new JLabel("Awaiting attemp.");
        add(status);
        read_file("E:\\Programming\\PalHelper\\PalWorldSettings.ini");
    }

    // Reads the contents of the ini file.
    private void read_file(String file){
        FileReader fileReader = new FileReader(file);
        try {
            file_data = fileReader.read_file_lines();
            int index = 0;
            for (String data_index : file_data) {
                System.out.println("index "  + index + ": " +data_index);
                index++;
            }
            status.setText(file_data.get(0));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
