package pages;

import base.BasePage;
import enums.WaitStrategy;
import factories.ExplicitWaitFactory;
import objects.BillingAddress;
import objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage {

    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    private final By showLogin = By.xpath("//a[@class='showlogin']");
    private final By username = By.xpath("//input[@id='username']");
    private final By password = By.xpath("//input[@id='password']");
    private final By loginBtn = By.xpath("//button[@name='login']");
    private final By productName = By.cssSelector("td[class = 'product-name']");

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage load() {
        load("/checkout/");
        return this;
    }

    public CheckoutPage enterFirstName(String firstName) {
        clear(firstNameFld, WaitStrategy.PRESENCE);
        sendKeys(firstNameFld, firstName, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        clear(lastNameFld, WaitStrategy.PRESENCE);
        sendKeys(lastNameFld, lastName, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage enterAddressLine(String addressLine) {
        clear(addressLineFld, WaitStrategy.PRESENCE);
        sendKeys(addressLineFld, addressLine, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity) {
        clear(billingCityFld, WaitStrategy.PRESENCE);
        sendKeys(billingCityFld, billingCity, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage enterBillingPostCode(String billingPostCode) {
        clear(billingPostCodeFld, WaitStrategy.PRESENCE);
        sendKeys(billingPostCodeFld, billingPostCode, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail) {
        clear(billingEmailFld, WaitStrategy.PRESENCE);
        sendKeys(billingEmailFld, billingEmail, WaitStrategy.PRESENCE);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .enterAddressLine(billingAddress.getAddressLineOne())
                .enterBillingCity(billingAddress.getCity())
                .enterBillingPostCode(billingAddress.getPostalCode())
                .enterBillingEmail(billingAddress.getEmail());
    }

    public CheckoutPage clickPlaceOrder() {
        List<WebElement> overlays = getElements(overlay);
        if (overlays.size() > 0) {
            ExplicitWaitFactory.performExplicitWait(WaitStrategy.INVISIBLE, overlay, driver);
        }
        click(placeOrderBtn, WaitStrategy.CLICKABLE);
        return this;
    }

    public String getNotice() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, successNotice, driver);
        return driver.findElement(successNotice).getText();
    }

    public CheckoutPage clickShowLogin() {
        click(showLogin, WaitStrategy.CLICKABLE);
        return this;
    }

    public CheckoutPage enterUsername(String userName) {
        sendKeys(username, userName, WaitStrategy.CLICKABLE);
        return this;
    }

    public CheckoutPage enterPassword(String passWord) {
        sendKeys(password, passWord, WaitStrategy.CLICKABLE);
        return this;
    }

    public CheckoutPage clickLoginBtn() {
        click(loginBtn, WaitStrategy.CLICKABLE);
        return this;
    }

    public CheckoutPage login(User user) {
        enterUsername(user.getUsername());
        enterPassword(user.getPassword());
        clickLoginBtn();
        return this;
    }

    public String getProductName() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, productName, driver);
        return driver.findElement(productName).getText();
    }
}
