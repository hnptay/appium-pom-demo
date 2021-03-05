package commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    // Screenshot attachments for Allure
    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(AppiumDriver<MobileElement> driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "Text attachment of {0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        AppiumDriver<MobileElement> driver = ((BaseTest) testClass).getDriver();
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
        saveScreenshotPNG(driver);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("---------- " + iTestContext.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("---------- " + iTestResult.getName() + " SKIPPED test ----------");

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("---------- " + iTestResult.getName() + " FAILED WITH SUCCESS PERCENTAGE test ----------");
    }

    @Override
    public void onFinish(ITestContext arg0) {
        System.out.println("---------- " + arg0.getName() + " FINISHED test ----------");
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println("---------- " + arg0.getName() + " STARTED test ----------");
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("---------- " + arg0.getName() + " SUCCESS test ----------");
    }

}
