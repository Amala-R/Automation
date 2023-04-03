package com.cloudmore.pageObjects;
import com.cloudmore.utility.DriverManager;
import com.cloudmore.utility.TestProperties;

public class CloudMoreMainPage extends DriverManager {

    public static void launchCloudMorApplication(){
        getWebDriverInstance().get(TestProperties.getProperty("url"));
    }

}