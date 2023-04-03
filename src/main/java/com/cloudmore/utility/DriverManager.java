package com.cloudmore.utility;

import com.cloudmore.exceptions.TestException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import static com.cloudmore.utility.Constants.CHROME_DRIVER_PATH_VARIABLE;

public class DriverManager {
    static ChromeOptions options = new ChromeOptions();
    static WebDriver driver;

    @BeforeSuite
    public static WebDriver getWebDriver() {
        if (StringUtils.isEmpty(System.getProperty(CHROME_DRIVER_PATH_VARIABLE)))
            System.setProperty(CHROME_DRIVER_PATH_VARIABLE, getPathToDriver(Constants.GOOGLE_CHROME, "chromedriver"));

        WebDriverManager.chromedriver().setup();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
        setDimension(driver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static String getPathToDriver(String driversFolder, String driverName) {
        String pathToDriver = String.join(File.separator, getPathToDrivers(), getOsDir(), driversFolder, getArchDir(),
                driverName);
        if (getOsDir().equals(Constants.WINDOWS))
            pathToDriver = pathToDriver.concat(".exe");
        return pathToDriver;
    }
    private static String getArchDir() {
        String osArch = System.getProperty("os.arch");
        return osArch.contains("64") ? "64bit" : "32bit";
    }

    private static String getPathToDrivers() {
        return String.join(File.separator, System.getProperty(Constants.USER_DIR), "..", "selenium_standalone_binaries");
    }

    private static String getOsDir() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains(Constants.WINDOWS))
            return Constants.WINDOWS;

        if (os.contains("linux"))
            return "linux";

        if (os.contains("mac"))
            return "osx";

        throw new TestException("Unknown OS: " + os);
    }

    private static void setDimension(WebDriver driver) {
        int width = (int) (long) ((JavascriptExecutor) driver).executeScript("return screen.width;");
        int height = (int) (long) ((JavascriptExecutor) driver).executeScript("return screen.height;");
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public static WebDriver getWebDriverInstance() {
        if (driver != null)
            return driver;
        return getWebDriver();
    }

    @AfterSuite
    public static void closeDriver(){
        driver.quit();
    }
}