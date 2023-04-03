package com.cloudmore.utility;

import com.cloudmore.exceptions.TestException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.cloudmore.utility.Constants.PROPERTY_FILE_PATH;

public class TestProperties {
    private static final Properties props = new Properties();

    /**
     * Load all the properties.
     */
    public static void loadAllProperties() {
        try {
            try (FileInputStream locator = new FileInputStream(PROPERTY_FILE_PATH)) {
                props.load(locator);
            }
        } catch (IOException e) {
            throw new TestException("Could not load properties : " + e.getMessage());
        }
    }

    /**
     * Gets and returns the property value based on the key passed
     *
     * @param key : is the key
     * @return : is the property
     */
    public static String getProperty(String key) {
        loadAllProperties();
        return props.getProperty(key);
    }
}