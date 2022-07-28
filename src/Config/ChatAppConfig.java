package Config;


import java.io.FileReader;
import java.util.Properties;

public class ChatAppConfig {
    public static ChatAppConfig configInstance;

    public Properties properties;

    protected ChatAppConfig() {
        try (FileReader reader = new FileReader("config")) {
            properties = new Properties();
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStringProperty(String key) {
        String value = this.properties.getProperty(key);
        if (value == null) {
            return "";
        }
        return value;
    }

    public int getIntegerProperty(String key) {
        String valueString = this.properties.getProperty(key);
        if (valueString == null) {
            return -1;
        }
        return Integer.parseInt(valueString);
    }

    public static ChatAppConfig getConfigInstance(){
        if (configInstance == null) {
            configInstance = new ChatAppConfig();
        }
        return configInstance;
    }

}
