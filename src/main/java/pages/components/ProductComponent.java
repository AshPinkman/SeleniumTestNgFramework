package pages.components;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class ProductComponent extends BasePage {

    private final By viewCartLink = By.xpath("//a[@title='View cart']");

    public ProductComponent(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtn(String productName) {
        return By.xpath("//a[@aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductComponent clickAddToCartBtn(String productName) {
        By addToCartBtn = getAddToCartBtn(productName);
        click(addToCartBtn, WaitStrategy.CLICKABLE);
        return this;
    }

    public CartPage clickViewCart() {
        click(viewCartLink, WaitStrategy.CLICKABLE);
        return new CartPage(driver);
    }

}
