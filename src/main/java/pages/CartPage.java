package pages;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By productName = By.xpath("//a[normalize-space()='Blue Shoes']");
    private final By checkoutBtn = By.xpath("//a[@class='checkout-button button alt wc-forward']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public CheckoutPage clickCheckoutBtn() {
        click(checkoutBtn, WaitStrategy.CLICKABLE);
        return new CheckoutPage(driver);
    }
}
