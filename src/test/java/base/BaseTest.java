package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Method;

public class BaseTest {

    // Thread-safe WebDriver for parallel tests
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Get driver instance for test classes
    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setup(Method method) {

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // maximize
        options.addArguments("--disable-notifications"); // disable popups
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*"); // needed for newer ChromeDriver
        driver.set(new ChromeDriver(options));

        // Open the URL
        getDriver().get("https://bstackdemo.com/");

        // Create ExtentTest for this method
        ExtentTest extentTest = extent.createTest(method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Capture test result
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().fail(result.getThrowable());

            // Take screenshot on failure
            try {
                File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".png";
                Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/"));
                Files.copy(src.toPath(), Paths.get(screenshotPath));
                test.get().addScreenCaptureFromPath(screenshotPath, "Failed Screenshot");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().pass("Test Passed");
        } else {
            test.get().skip("Test Skipped");
        }

        // Quit driver
        getDriver().quit();
        driver.remove();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}