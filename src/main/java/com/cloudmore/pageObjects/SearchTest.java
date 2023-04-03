package com.cloudmore.pageObjects;

import com.cloudmore.utility.Assertions;
import com.cloudmore.utility.DriverManager;
import com.cloudmore.utility.ScreenshotUtility;
import com.cloudmore.utility.TestProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchTest extends DriverManager {

    public SearchTest(){
        PageFactory.initElements(getWebDriverInstance(), this);
    }

    public final WebDriverWait wait = new WebDriverWait(getWebDriverInstance(), Duration.ofSeconds(100));

    @FindBy(xpath = "//div[@id='header_search']/a")
    public WebElement searchIcon;

    @FindBy(xpath = "//input[@id='searchInput']")
    public WebElement searchTextBox;

    @FindBy(xpath = "//button[@type='submit' and @aria-label='Search']")
    public WebElement findIcon;

    @FindAll({@FindBy(xpath = "//div[@class='site-search__result']")})
    public List<WebElement> searchResultList;

    public void clickSearchIconOnMainPage(){
        wait.until(ExpectedConditions.visibilityOf(searchIcon));
        searchIcon.click();
        Assertions.shouldBeTrue("Clicked on Search Icon");
    }

    public void enterTextAndSearch(){
        wait.until(ExpectedConditions.visibilityOf(searchTextBox));
        searchTextBox.clear();
        searchTextBox.sendKeys(TestProperties.getProperty("searchText"));
        wait.until(ExpectedConditions.visibilityOf(findIcon));
        findIcon.click();
        getWebDriverInstance().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void verifyCountOfSearchedResults() throws IOException {
        int countOfSearchedResult = searchResultList.size();
        if(countOfSearchedResult>=3)
            Assertions.shouldBeTrue("Count of searched result is "+countOfSearchedResult);
        else{
            Assertions.shouldBeTrue(false, "Verify if the count of searched result is less than 3",
                    "The expected count should be more than or equal to 3", "The count is less than 3");
        }
        ScreenshotUtility.takeScreenshotForDesktopSize();
        ScreenshotUtility.takeScreenshotForMobileSize();
        closeDriver();
    }
}