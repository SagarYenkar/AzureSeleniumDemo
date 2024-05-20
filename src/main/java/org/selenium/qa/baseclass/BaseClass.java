package org.selenium.qa.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    // Logger
    public static Logger log = LogManager.getLogger("BaseClass");

    // Read the Properties file
    public BaseClass() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java"+ "/org/selenium/qa/properties/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Explicit Wait
    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        String isHeadless = "ture";
        String hubURL = prop.getProperty("hubURL");

        if (hubURL != null && !hubURL.isEmpty()) {
            // Initialize WebDriver for Selenium Grid
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                if (browserName.equalsIgnoreCase("chrome")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    if (isHeadless.equalsIgnoreCase("true")) {
                        options.addArguments("headless");
                    }
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    FirefoxOptions options = new FirefoxOptions();
                    if (isHeadless.equalsIgnoreCase("true")) {
                        options.addArguments("-headless");
                    }
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
                }
                driver = new RemoteWebDriver(new URL(hubURL), capabilities);
                log.info("Launching remote " + browserName + " browser");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                log.error("Hub URL is malformed", e);
            }
        } else {
            // Initialize local WebDriver
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                if (isHeadless.equalsIgnoreCase("true")) {
                    options.addArguments("headless");
                }
                driver = new ChromeDriver(options);
                log.info("Launching local Chrome browser");

            } else if (browserName.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                if (isHeadless.equalsIgnoreCase("true")) {
                    options.addArguments("-headless");
                }
                driver = new FirefoxDriver(options);
                log.info("Launching local Firefox browser");
            }
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
    }
}
