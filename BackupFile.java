/*
 * This class is responsible for backing up the file, to a zip, before editing it.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BackupFile {
    private String file;
    private String backup;

    public BackupFile(String file) {
        this.file = file;
        this.backup = file + ".zip";
    }

    public void backup_file() {
        try {
            FileOutputStream file_output = new FileOutputStream(backup);
            ZipOutputStream zip_output = new ZipOutputStream(file_output);
            File file_to_zip = new File(file);

            FileInputStream file_input = new FileInputStream(file_to_zip);
            ZipEntry zip_entry = new ZipEntry(file_to_zip.getName());
            zip_output.putNextEntry(zip_entry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = file_input.read(bytes)) >= 0) {
                zip_output.write(bytes, 0, length);
            }
            zip_output.close();
            file_input.close();
            file_output.close();
            System.out.println("Backup Successful.");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
