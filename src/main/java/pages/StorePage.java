package pages;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.components.ProductComponent;

import java.time.Duration;

public class StorePage extends BasePage {

    private final By searchFld = By.xpath("//input[@id='woocommerce-product-search-field-0']");
    private final By searchBtn = By.xpath("//button[@value='Search']");
    private final By title = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");

    private ProductComponent productComponent;

    public StorePage(WebDriver driver) {
        super(driver);
        productComponent = new ProductComponent(driver);
    }

    public StorePage load() {
        load("/store");
        return this;
    }

    private StorePage enterTextInSearchFld(String txt) {
        sendKeys(searchFld, txt, WaitStrategy.PRESENCE);
        return this;
    }

    private StorePage clickSearchBtn() {
        click(searchBtn, WaitStrategy.CLICKABLE);
        return this;
    }

    public StorePage search(String txt) {
        enterTextInSearchFld(txt).clickSearchBtn();
        return this;
    }

    public String getTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.textToBe(title, "Search results: “Blue”"));
        return getElement(title).getText();
    }

    public ProductComponent getProductComponent() {
        return productComponent;
    }

}


