package opengltut.util;

import opengltut.logger.Logger;
import opengltut.logger.LoggerFactory;
import opengltut.logger.Tag;

import java.io.*;

public class FileUtil {

    private static final Tag tag = new Tag("FileUtil");

    private static Logger logger = LoggerFactory.getConsoleLogger();

    public static String readFile(String path) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = new FileInputStream(path);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = null;
            line = buf.readLine();

            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException e) {
            logger.error(tag, "File not found: " + path);
        }

        return sb.toString();
    }
}
