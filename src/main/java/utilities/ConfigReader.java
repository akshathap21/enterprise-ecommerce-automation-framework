package utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    /**
     * Initializes and loads key-value metadata vectors from the target properties file.
     * This decouples your test scripts from hardcoded environments, URLs, and wait timeouts.
     * 
     * @return Properties object mapping the configuration parameters.
     */
    public Properties initProperties() {
        prop = new Properties();
        try {
            // Establishing a synchronous byte stream to locate the static resource file
            FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
            
            // Parsing the key-value structure into runtime system collections
            prop.load(ip);
            
        } catch (FileNotFoundException e) {
            System.err.println("Critical Error: The config.properties file could not be located at the mapped path.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Critical Error: Failed to read data stream from config.properties file.");
            e.printStackTrace();
        }
        
        return prop;
    }
}
