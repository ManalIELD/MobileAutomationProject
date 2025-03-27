package org.app.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private final Properties properties;
    private static final String PROPERTIES_PATH = "./src/main/resources/environment.properties";


    public PropertyReader() {
        properties = new Properties();
        try (InputStream input = new FileInputStream(PROPERTIES_PATH)) {
            properties.load(input); //loads the property file and puts it in the cache
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getProperty(String key) {

        return properties.getProperty(key);
    }
}

