package driver;

import enums.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

public class Driver {

    private static WebDriver driver;

    public static void initializeDriver(String browser) {

        String localBrowser = System.getProperty("browser", browser);
        switch (DriverType.valueOf(localBrowser)) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name");
        }
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();

    }

    public static void quitDriver() {
        DriverManager.getDriver().quit();
    }
}
