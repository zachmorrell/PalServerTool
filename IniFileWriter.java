import java.io.File;
import java.io.FileWriter;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IniFileWriter {

    private File file;
    private String data = "";
    private JLabel[] keys = new JLabel[64];
    private Component[] values = new Component[64];

    // Class constructor.
    public IniFileWriter(String file_path, JLabel[] keys, Component[] values) {
        this.file = new File(file_path);
        this.keys = keys;
        this.values = values;
    }

    // Method writes the data back to the ini file.
    public void write_to_file() {
        try {
            arrays_to_data();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // Method converts and concats the arrays back into the original formatting.
    private void arrays_to_data() {
        // Loops through keys array.
        for(int i = 0; i < keys.length; i++) {
            // Fill accumulator string with text form the index of i.
            data += keys[i].getText();
            // values data of 0 and 63 are null, \n or new line.
            if(i == 0 || i == 63) {
                data+="\n";
            } else {
                // If it is a checkbox True for true and False for false.
                if(values[i] instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) values[i];
                    String status = (checkBox.isSelected()) ? "True":"False";
                    data += "=" + status;
                } else {
                    // Else concat the text from the textfield to the data accumulator.
                    JTextField textField = (JTextField) values[i];
                    data += "=" + textField.getText();
                }
                // concat a comma is i, index, is not 62. Else \n or new line.
                data += (i != 62) ? ",":"\n";
            }
        }
    }
}
