package pageObject;

import commons.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageUI.DemoPageUI;

public class DemoPageObject extends BasePage {

    private AppiumDriver<MobileElement> driver;

    public DemoPageObject(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void sendKeysToSearchBar(String value) {
        waitForElementVisible(driver, DemoPageUI.URL_BAR);
        sendKeysToElement(driver, DemoPageUI.URL_BAR, value);
    }

    public void clickToSearchBar() {
        waitForElementClickable(driver, DemoPageUI.SEARCH_BAR);
        clickToElement(driver, DemoPageUI.SEARCH_BAR);
    }

    public void pressToEnterKey() {
        androidPressToEnterKey(driver);
    }

    public void clickToAccept() {
        waitForElementClickable(driver, DemoPageUI.ACCEPT_BUTTON);
        clickToElement(driver, DemoPageUI.ACCEPT_BUTTON);
    }

    public void clickToGotItButton() {
        waitForElementClickable(driver, DemoPageUI.GOT_IT_BUTTON);
        clickToElement(driver, DemoPageUI.GOT_IT_BUTTON);
    }

    public void clickToVoteButton(String text) {
        scrollIntoViewForAndroid(driver, text);
        waitForElementClickable(driver, DemoPageUI.VOTE_BUTTON);
        clickToElement(driver, DemoPageUI.VOTE_BUTTON);
    }

    public void clickToDemosView() {
        waitForElementClickable(driver, DemoPageUI.DEMOS_VIEWS);
        clickToElement(driver, DemoPageUI.DEMOS_VIEWS);
    }

    public void clickToWebViews(String text) {
        scrollIntoViewForAndroid(driver, text);
        waitForElementClickable(driver, DemoPageUI.DEMO_WEB_VIEWS);
        clickToElement(driver, DemoPageUI.DEMO_WEB_VIEWS);
    }

    public void scrollDown(Direction direction, long duration, int numOfScroll) {
        scroll(driver, direction, duration, numOfScroll);
    }

    public void photoClickDynamic(String text) {
        waitForElementClickable(driver, DemoPageUI.PHOTO_DYNAMIC, text);
        clickToElement(driver, DemoPageUI.PHOTO_DYNAMIC, text);
    }

    public void photoClickToImage() {
        waitForElementClickable(driver, DemoPageUI.PHOTO_IMAGE);
        clickToElement(driver, DemoPageUI.PHOTO_IMAGE);
    }

    public void zoomOut(Direction direction) {
        zoom(driver, direction);
    }

    public void clickToMapAccept() {
        waitForElementClickable(driver, DemoPageUI.MAP_ACCEPT);
        clickToElement(driver, DemoPageUI.MAP_ACCEPT);
    }

    public void zoomIn(Direction direction) {
        zoom(driver, direction);
    }

    public void clickToStandardUser(){
        scrollIntoViewForAndroid(driver, "standard_user");
        waitForElementClickable(driver, DemoPageUI.SWAG_USER);
        clickToElement(driver, DemoPageUI.SWAG_USER);
    }

    public void clickToLogin(){
        waitForElementClickable(driver, DemoPageUI.SWAG_LOGIN);
        clickToElement(driver, DemoPageUI.SWAG_LOGIN);
    }

    public void clickToBackpack(){
        waitForElementClickable(driver, DemoPageUI.SWAG_BAG);
        clickToElement(driver, DemoPageUI.SWAG_BAG);
    }
}
