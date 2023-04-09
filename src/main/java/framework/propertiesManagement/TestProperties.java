package framework.propertiesManagement;

import framework.exceptions.AutomationException;
import framework.globalConstants.PathConfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class TestProperties {

    private static final TestProperties TEST_PROPERTIES = new TestProperties();
    private static Properties properties;

    public static TestProperties init() throws AutomationException {
        TestProperties testProp = TEST_PROPERTIES;
        testProp.getInstance();
        return testProp;
    }

    private void getInstance() throws AutomationException {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream inputStream = new FileInputStream(PathConfig.getPropertiesTest());
                properties.load(inputStream);
            } catch (IOException e) {
                throw new AutomationException(e.getMessage());
            }
        }
    }

    public String getProperty(String propertyName) throws AutomationException {
        Object value = properties.get(propertyName);
        if (value != null) {
            return properties.get(propertyName).toString();
        } else {
            String errorLog = "Error occurred while getting Property. This could be due to no such property available in properties file.";
            throw new AutomationException(errorLog);
        }
    }

}
