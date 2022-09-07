package pages;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']//a[@class='menu-link'][normalize-space()='Store']");

    public HomePage load() {
        load("/");
        return this;
    }

    public StorePage clickStoreMenuLink() {
        click(storeMenuLink, WaitStrategy.CLICKABLE);
        return new StorePage(driver);
    }
}
