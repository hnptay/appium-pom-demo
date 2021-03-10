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
    //@Test
    public void TC_01_demo() {
        log.info("Open APP");
        demoPage.clickToDemosView();
        demoPage.clickToWebViews("WebView");
        demoPage.clickToAccept();
        demoPage.clickToGotItButton();
        demoPage.clickToGotItButton();
        demoPage.clickToSearchBar();
        demoPage.sendKeysToSearchBar("https://demo.nopcommerce.com/");
        demoPage.pressToEnterKey();
        demoPage.scrollDown(BasePage.Direction.DOWN, 10, 4);
        sleepInSecond(3);
    }

    //@Test
    public void TC_02_zoom(){
        log.info("OPEN APP");
        demoPage.photoClickDynamic("ALLOW");
        demoPage.photoClickDynamic("GET STARTED");
        demoPage.photoClickDynamic("NO THANKS");
        demoPage.photoClickToImage();
        demoPage.zoomOut(BasePage.Direction.ZOOM_OUT);
        sleepInSecond(5);
    }

    @Test
    public void TC_03_zoom(){
        log.info("OPEN APP");
        demoPage.clickToMapAccept();
        sleepInSecond(3);
        demoPage.zoomOut(BasePage.Direction.ZOOM_OUT);
        sleepInSecond(3);
        demoPage.zoomIn(BasePage.Direction.ZOOM_IN);
        sleepInSecond(3);
    }

    //@Test
    public void TC_04_swagLabs(){
        log.info("OPEN APP");
        demoPage.clickToStandardUser();
        demoPage.clickToLogin();
        demoPage.clickToBackpack();
        demoPage.scrollDown(BasePage.Direction.DOWN, 1000, 1);
        sleepInSecond(3);
        demoPage.zoomOut(BasePage.Direction.ZOOM_OUT);
        sleepInSecond(3);
    }

    private DemoPageObject demoPage;
}
