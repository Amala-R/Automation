import com.cloudmore.pageObjects.CloudMoreLogo;
import com.cloudmore.pageObjects.CloudMoreMainPage;
import com.cloudmore.pageObjects.CloudMoreMenuItems;
import com.cloudmore.pageObjects.SearchTest;
import com.cloudmore.utility.Assertions;
import org.testng.annotations.Test;

import java.io.IOException;

public class CloudmoreTestSuite{
    static CloudMoreLogo cloudMoreLogo = new CloudMoreLogo();
    static CloudMoreMenuItems cloudMoreMenuItems = new CloudMoreMenuItems();
    static SearchTest searchTest = new SearchTest();

    @Test
    public static void verifyCloudMoreLogo(){
        CloudMoreMainPage.launchCloudMorApplication();
        cloudMoreLogo.verifyCloudMoreLogo();
        Assertions.SOFT_ASSERT.assertAll();
    }

    @Test
    public static void verifyCloudMoreMenuItems(){
        cloudMoreMenuItems.verifyMenuItem();
        Assertions.SOFT_ASSERT.assertAll();
    }

    @Test
    public static void searchTextAndVerifyResultCountWithScreenshot() throws IOException {
        searchTest.clickSearchIconOnMainPage();
        searchTest.enterTextAndSearch();
        searchTest.verifyCountOfSearchedResults();
        Assertions.SOFT_ASSERT.assertAll();
    }
}