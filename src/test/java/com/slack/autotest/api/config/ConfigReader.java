package com.slack.autotest.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties PROPERTIES = null;

    /**
     * This method is used to load the properties file
     */
    private static void loadPropertyFile() {
        String FILE_PATH = "autotestConfig.properties";
        PROPERTIES = new Properties();
        InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream(FILE_PATH);
        if (stream == null) {
            System.err.println("WARNING: Properties file for api tests does not exist: " + FILE_PATH + '.');
            return;
        }

        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            System.err.println("ERROR: Failure while reading properties file for api tests: " + FILE_PATH + ':');
            e.printStackTrace(System.err);
        }
    }

    /**
     * This method is used to get a specific property from autotestConfig.properties
     *
     * @param key specific property
     * @return value associated with the specific property
     */
    public static String getProperty(String key) {
        if (PROPERTIES == null) {
            loadPropertyFile();
        }
        return PROPERTIES.getProperty(key);
    }

}