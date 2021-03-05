package commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    //Mobile element
    protected By byXpath(String locator) {
        return By.xpath(locator);
    }

    protected MobileElement findElementByXpath(AppiumDriver<MobileElement> driver, String locator) {
        for (int i = 0; i < 10; i++) {
            try {
                element = driver.findElement(byXpath(formatLocator(locator)));
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        }
        return element;
    }

    protected List<MobileElement> findElementsByXpath(AppiumDriver<MobileElement> driver, String locator) {
        List<MobileElement> elements = null;
        for (int i = 0; i < 10; i++) {
            try {
                elements = driver.findElements(byXpath(formatLocator(locator)));
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
        }
        return elements;
    }

    public void scrollIntoViewForAndroid(AppiumDriver<MobileElement> driver, String text) {
        ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));");
    }

    protected String formatLocator(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    protected void clickToElement(AppiumDriver<MobileElement> driver, String locator) {
        findElementByXpath(driver, locator).click();
    }

    protected void sendKeysToElement(AppiumDriver<MobileElement> driver, String locator, String value) {
        element = findElementByXpath(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getElementText(AppiumDriver<MobileElement> driver, String locator) {
        return findElementByXpath(driver, locator).getText();
    }

    protected String getElementAttribute(AppiumDriver<MobileElement> driver, String locator, String attributeName) {
        return findElementByXpath(driver, locator).getAttribute(attributeName);
    }

    protected Point getElementLocation(AppiumDriver<MobileElement> driver, String locator) {
        return findElementByXpath(driver, locator).getLocation();
    }

    //Explicit Wait
    protected void waitForElementVisible(AppiumDriver<MobileElement> driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
    }

    protected void waitForElementVisible(AppiumDriver<MobileElement> driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(formatLocator(locator, values))));
    }

    protected void waitForAllElementVisible(AppiumDriver<MobileElement> driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
    }

    protected void waitForAllElementVisible(AppiumDriver<MobileElement> driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(formatLocator(locator, values))));
    }

    protected void waitForElementClickable(AppiumDriver<MobileElement> driver, String locator) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
    }

    protected void waitForElementClickable(AppiumDriver<MobileElement> driver, String locator, String... values) {
        wait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(byXpath(formatLocator(locator, values))));
    }

    //Touch action
    protected void scroll(AppiumDriver<MobileElement> driver, Direction direction, long duration, int numOfScroll) {
        Dimension size = driver.manage().window().getSize();
        TouchAction t = new TouchAction<>(driver);

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction) {
            case RIGHT:
                startY = (size.height / 2);
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                while (numOfScroll > 0) {
                    t.longPress(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    numOfScroll--;
                }
                break;

            case LEFT:
                startY = (size.height / 2);
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                while (numOfScroll > 0) {
                    t.longPress(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                            .moveTo(PointOption.point(endX, startY))
                            .release()
                            .perform();
                    numOfScroll--;
                }
                break;

            case UP:
                endY = (int) (size.height * 0.70);
                startY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                while (numOfScroll > 0) {
                    t.longPress(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();
                    numOfScroll--;
                }
                break;

            case DOWN:
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                while (numOfScroll > 0) {
                    t.longPress(PointOption.point(startX, startY))
                            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                            .moveTo(PointOption.point(startX, endY))
                            .release()
                            .perform();
                    numOfScroll--;
                }
                break;
        }
    }

    public enum Direction {
        DOWN, UP, LEFT, RIGHT;
    }

    //Android keyboard
    protected void androidPressToEnterKey(AppiumDriver<MobileElement> driver) {
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    protected boolean isElementDisplayed(AppiumDriver<MobileElement> driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isElementSelected(AppiumDriver<MobileElement> driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isSelected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isElementEnable(AppiumDriver<MobileElement> driver, String locator) {
        try {
            return findElementByXpath(driver, locator).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private MobileElement element;
    private WebDriverWait wait;
    private JavascriptExecutor js;
}
