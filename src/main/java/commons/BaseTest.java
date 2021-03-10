package commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private AppiumDriver<MobileElement> driver;
    protected Logger log;

    protected BaseTest() {
        log = Logger.getLogger(getClass());
    }

    protected AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    protected AppiumDriver<MobileElement> initializeDriver(String url, String platformName) {
        try {
            if (platformName.equalsIgnoreCase("android")) {
                driver = new AndroidDriver<>(new URL(url), getAndroidCapabilities(platformName));
            } else if (platformName.equalsIgnoreCase("ios")) {
                driver = new IOSDriver<>(new URL(url), getIOSCapabilities(platformName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    private DesiredCapabilities getAndroidCapabilities(String platformName) throws IOException {
        FileInputStream file = new FileInputStream(GlobalConstants.CAPABILITIES_DIR + "androidCapabilities.properties");
        Properties prop = new Properties();
        prop.load(file);
//        File app = new File(GlobalConstants.APP_DIR, (String) prop.get("appName"));
        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        cap.setCapability(MobileCapabilityType.UDID, (String) prop.get("udId"));
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, (String) prop.get("platformVersion"));
        cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, (String) prop.get("appPackage"));
        cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, (String) prop.get("appActivity"));
//        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        cap.setCapability(MobileCapabilityType.BROWSER_NAME, (String) prop.get("browserName"));
//        ChromeOptions ops = new ChromeOptions();
//        ops.addArguments("--disable-translate");
//        ops.setExperimentalOption("--disable-fre");
//        cap.setCapability(ChromeOptions.CAPABILITY, ops);
        return cap;
    }

    private DesiredCapabilities getIOSCapabilities(String platformName) throws IOException {
        FileInputStream file = new FileInputStream(GlobalConstants.CAPABILITIES_DIR + "iosCapabilities.properties");
        Properties prop = new Properties();
        prop.load(file);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, (String) prop.get("deviceName"));
        cap.setCapability(MobileCapabilityType.UDID, (String) prop.get("udId"));
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, (String) prop.get("platformVersion"));
        return cap;
    }

    protected void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            if (condition) {
                log.info(" ----------------------- VERIFY PASSED ----------------------- ");
            } else {
                log.info(" ----------------------- VERIFY FAILED ----------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            if (!condition) {
                log.info(" ----------------------- VERIFY PASSED ----------------------- ");
            } else {
                log.info(" ----------------------- VERIFY FAILED ----------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" ----------------------- VERIFY PASSED ----------------------- ");
        } catch (Throwable e) {
            status = false;
            log.info(" ----------------------- VERIFY FAILED ----------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected String getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int date = localDateTime.getDayOfMonth();
        return date + "";
    }

    protected String getCurrentMonthName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.getMonth();
        return month.getDisplayName(TextStyle.FULL, Locale.US);
    }

    protected String getCurrentMonthNumber() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.getMonth();
        int monthNumber = month.getValue();
        if (monthNumber < 10) {
            return "0" + monthNumber;
        }
        return monthNumber + "";
    }

    protected String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss a");  //yyyy/MMMM/dd
        return dtf.format(localDateTime);
    }

    protected String getCurrentYear() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getYear() + "";
    }

    protected String dateTimeCustomFormat() {
        return getCurrentMonthName() + " " + getCurrentDate() + ", " + getCurrentYear();
    }

}
