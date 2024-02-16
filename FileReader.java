import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    private File file;

    public FileReader(String file_path) {
        this.file = new File(file_path);
    }

    public List<String> read_file_lines() {
        List<String> lines = new ArrayList<>();
        try {
            Scanner file_scanner = new Scanner(file);
            while (file_scanner.hasNextLine()) {
                String data = file_scanner.nextLine();
                String[] line_array = data.split(",");
                for(String item :  line_array) {
                    lines.add(item);
                }
            }
            file_scanner.close();
        } catch(Exception e) {
            System.out.println("Issue Reading File.");
        }
        return lines;
    }
}
