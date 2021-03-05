package testcases;

import commons.AllureListener;
import commons.BasePage;
import commons.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.*;
import org.testng.annotations.*;
import pageObject.DemoPageObject;

@Epic("Regression Tests")
@Feature("Demo Tests")
//@Listeners({AllureListener.class})
public class Demo extends BaseTest {

    private AppiumDriver<MobileElement> driver;

    @Parameters({"url", "platformName"})
    @BeforeClass
    public void setupTest(String url, String platformName) {
        driver = initializeDriver(url, platformName);
        demoPage = new DemoPageObject(driver);
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        closeDriver();
    }

    @Description("Demo_01")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void TC_01_demo() {
        log.info("Open Demo APP");
        demoPage.clickToDemosView();
//        demoPage.clickToWebViews("WebView");
//        demoPage.clickToAccept();
//        demoPage.clickToGotItButton();
//        demoPage.clickToGotItButton();
//        demoPage.clickToSearchBar();
//        demoPage.sendKeysToSearchBar("https://demo.nopcommerce.com/");
//        demoPage.pressToEnterKey();
//        sleepInSecond(5);
//        demoPage.clickToVoteButton();
        demoPage.scrollDown(BasePage.Direction.DOWN, 50, 3);
        sleepInSecond(3);
    }

    private DemoPageObject demoPage;
}
