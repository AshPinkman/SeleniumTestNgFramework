package pages.components;

import base.BasePage;
import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.StorePage;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']//a[@class='menu-link'][normalize-space()='Store']");

    public StorePage clickStoreMenuLink() {
        click(storeMenuLink, WaitStrategy.CLICKABLE);
        return new StorePage(driver);
    }
}
