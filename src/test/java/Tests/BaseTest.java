package Tests;

import Utilities.AllureReports;
import Utilities.Base;
import Utilities.PageFactoryUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Date;

public class BaseTest extends Base {

    @BeforeTest
    @Parameters({"platform", "url"})
    void setProperty(String platform, String url) {
        String testingEnvironment = platform;
        AllureReports.logToReport("Start testing with platform : " + testingEnvironment);
        setDriver(testingEnvironment);
        PageFactoryUtilities.buildPageFactory();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //actions = new Actions(driver);
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    void finishTests() {
        driver.quit();
    }

    void setDriver(String type) {
        switch (type) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
    }
}