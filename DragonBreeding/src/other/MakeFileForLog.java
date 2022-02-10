package other;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class MakeFileForLog {

   public static void makeFile(String file_name, ArrayList<String> log_list) {
      // °æ·Î
      String path = System.getProperty("user.dir");
      try {
         FileWriter log_file = new FileWriter(path + file_name, true);
         BufferedWriter buffer_writer = new BufferedWriter(log_file);

         String temp_log;

         for (int i = 0; i < log_list.size(); ++i) {
            temp_log = log_list.get(i);

            buffer_writer.write(temp_log);
            buffer_writer.newLine();

         }
         buffer_writer.flush();
         buffer_writer.close();
         log_file.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}