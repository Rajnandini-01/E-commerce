package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();

    static {

        try {

            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if(input == null){
                throw new RuntimeException(
                        "config.properties file not found inside resources folder"
                );
            }

            prop.load(input);

        } catch (Exception e){
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key){

        String value = prop.getProperty(key);

        if(value == null){
            throw new RuntimeException(
                    "Property not found in config.properties : " + key
            );
        }

        return value.trim();
    }
}