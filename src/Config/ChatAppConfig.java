package Config;

import java.io.FileReader;
import java.util.Properties;

public class ChatAppConfig {
    public Properties properties;

    public ChatAppConfig() {
        try (FileReader reader = new FileReader("config")) {
            properties = new Properties();
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
