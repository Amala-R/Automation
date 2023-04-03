package com.cloudmore.pageObjects;

import com.cloudmore.utility.Assertions;
import com.cloudmore.utility.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CloudMoreMenuItems extends DriverManager {
    static List<WebElement> menuItemWebElementList = new ArrayList<>();
    static List<String> menuItemTextList = new ArrayList<>();
    @FindBy(xpath = "//a[@href='https://cloudmore.com/product/']")
    public WebElement platform;

    @FindBy(xpath = "//a[@href='https://cloudmore.com/content-hub']")
    public WebElement blog;

    @FindBy(xpath = "//a[@href='https://cloudmore.com/case-studies/']")
    public WebElement caseStudies;

    @FindBy(xpath = "//a[@href='https://cloudmore.com/about-us/']")
    public WebElement aboutUs;
    public CloudMoreMenuItems(){
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public void verifyMenuItem() {
        addWebElementsToList();
        addTextsToCompare();

        for (WebElement menuItem : menuItemWebElementList) {
            boolean isMenuItemDisplayed = menuItem.isDisplayed();
            String expectedMenuItemText = menuItemTextList.get(menuItemWebElementList.indexOf(menuItem));
            if (isMenuItemDisplayed) {
                String actualMenuItemText = menuItem.getText();
                Assertions.shouldBeTrue(actualMenuItemText.equals(expectedMenuItemText), String.format("Verification of %s menu item", expectedMenuItemText),
                        String.format("%s menu item is displayed correctly", expectedMenuItemText), String.format("%s menu item is not displayed", actualMenuItemText));
            } else {
                Assertions.shouldBeTrue(false, String.format("Verification of %s menu item", expectedMenuItemText),
                        String.format("%s menu item is displayed correctly", expectedMenuItemText), "Menu item is not displayed");
            }
        }
    }

    public void addWebElementsToList() {
        menuItemWebElementList.add(platform);
        menuItemWebElementList.add(blog);
        menuItemWebElementList.add(caseStudies);
        menuItemWebElementList.add(aboutUs);
    }

    public void addTextsToCompare() {
        menuItemTextList.add("Platform");
        menuItemTextList.add("Blog");
        menuItemTextList.add("Case Studies");
        menuItemTextList.add("About Us");
    }
}