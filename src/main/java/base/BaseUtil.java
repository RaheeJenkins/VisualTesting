package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.Auth;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseUtil {

    protected RemoteWebDriver driver;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    @Parameters({"environment", "browser", "URL"})
    public void setUp(String environment, String browser, String URL, @Optional Method method) {

        if (browser.equalsIgnoreCase("Chrome")) {
            getChromeBrowser(environment, method.getName());
        } else if (browser.equalsIgnoreCase("Firefox")) {
            getFirefoxBrowser(environment, method.getName());
        } else if (browser.equalsIgnoreCase("IE")) {
            getIEBrowser(environment, method.getName());
        } else if (browser.equalsIgnoreCase("Safari")) {
            getSafariBrowser(environment, method.getName());
        }

        driver.manage().window().maximize();
        driver.navigate().to(URL);

        in.Visual.setAccessKey(Auth.VISUAL_ACCESS_KEY);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void getChromeBrowser(String environment, String testName) {
        if (environment.equalsIgnoreCase("Local")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rahee\\IdeaProjects\\VisualTesting\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (environment.equalsIgnoreCase("Grid")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            generalCapabilities(testName);
        }
    }

    private void getFirefoxBrowser(String environment, String testName) {
        if (environment.equalsIgnoreCase("Local")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Rahee\\IdeaProjects\\VisualTesting\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (environment.equalsIgnoreCase("Grid")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
            generalCapabilities(testName);
        }
    }

    private void getIEBrowser(String environment, String testName) {
        if (environment.equalsIgnoreCase("Local")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else if (environment.equalsIgnoreCase("Grid")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.IE);
            generalCapabilities(testName);
        }
    }

    private void getSafariBrowser(String environment, String testName) {
        if (environment.equalsIgnoreCase("Local")) {
            System.setProperty("", System.getProperty("user.dir") + "/driver/");
            driver = new SafariDriver();
        } else if (environment.equalsIgnoreCase("Grid")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
            generalCapabilities(testName);
        }
    }

    private void generalCapabilities(String testName) {
        dc.setCapability(CapabilityType.VERSION, "Any");
        dc.setCapability(CapabilityType.PLATFORM, Platform.ANY);
        dc.setCapability("accessKey", Auth.CLOUD_ACCESS_KEY);
        dc.setCapability("testName", testName);
        try {
            driver = new RemoteWebDriver(new URL(Auth.CLOUD_URL), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
