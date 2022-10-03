package base;

import driver.Driver;
import driver.DriverManager;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CookieUtils;

import java.util.List;

public class BaseTest {
    protected BaseTest() {

    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void initDriver(String browser) {
        Driver.initializeDriver(browser);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public synchronized void quitDriver() {
        Driver.quitDriver();
    }

    public void injectCookiesToBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            DriverManager.getDriver().manage().addCookie(cookie);
        }
    }
}
