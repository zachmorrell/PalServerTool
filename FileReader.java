import java.util.Map;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class FileReader {
    private File file;

    public FileReader(String file_path) {
        this.file = new File(file_path);
    }
    
    // Reads a File and Stores it's content into a HasMap.
    public Map<String, String> read_file_lines() {
        Map<String, String> lines = new LinkedHashMap<String, String>();
        try {
            // Creates and runs the Scanner to start reading by line.
            Scanner file_scanner = new Scanner(file);
            while (file_scanner.hasNextLine()) {

                String data = file_scanner.nextLine();

                // The read data is added to an array and split of commas.
                String[] line_array = data.split(",");

                // Loop through each item in line_array.
                for(String item :  line_array) {
                    // For formatting check for "="
                    if(item.contains("=")) {
                        // Split on "=" and add to the HashMap
                        String[] split_item = item.split("=" , 2);
                        lines.put(split_item[0], split_item[1]);
                    } else {
                        // If it doesn't contain an "=" then assign it's value as empty.
                        lines.put(item, "empty");
                    }
                }
            }
            // Close scanner.
            file_scanner.close();
        } catch(Exception e) {
            System.out.println("Issue Reading File: "+e);
        }
        return lines;
    }
}
