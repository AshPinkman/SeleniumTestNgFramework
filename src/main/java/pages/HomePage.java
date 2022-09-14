package pages;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.components.HeaderComponent;
import pages.components.ProductComponent;

public class HomePage extends BasePage {

    private HeaderComponent headerComponent;
    private ProductComponent productComponent;

    public HomePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
        productComponent = new ProductComponent(driver);
    }

    public HomePage load() {
        load("/");
        return this;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public ProductComponent getProductComponent() {
        return productComponent;
    }

}
