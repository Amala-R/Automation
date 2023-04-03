package com.cloudmore.pageObjects;

import com.cloudmore.utility.Assertions;
import com.cloudmore.utility.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CloudMoreLogo extends DriverManager {

    public final WebDriverWait wait = new WebDriverWait(getWebDriverInstance(), Duration.ofSeconds(100));

    @FindBy(xpath = "//a[@href='//cloudmore.com' and @id='hs-link-static_header_logo_hs_logo_widget']/img[contains(@title,'cloudmore - logo - full colour')]")
    public WebElement logo;

    public CloudMoreLogo(){
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public void verifyCloudMoreLogo(){
        wait.until(ExpectedConditions.visibilityOf(logo));
        boolean isLogoFound = logo.isDisplayed();
        Assertions.shouldBeTrue(isLogoFound,
                "Verification of Logo in cloudmore application",
                "Logo is not displayed in the cloudmore application"+isLogoFound,
                "Logo should be displayed in cloudmore application");
    }

}