package factories;

import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public final class ActionsFactory {

    private ActionsFactory(){

    }

    public static void performDoubleClick(WebDriver driver, By by, WaitStrategy waitStrategy){
        ExplicitWaitFactory.performExplicitWait(waitStrategy, by, driver);
        getAction(driver).doubleClick(driver.findElement(by)).perform();
    }

    public static void performRightClick(WebDriver driver, By by, WaitStrategy waitStrategy){
        ExplicitWaitFactory.performExplicitWait(waitStrategy, by, driver);
        getAction(driver).contextClick(driver.findElement(by)).perform();
    }

    public static void performClick(WebDriver driver, By by, WaitStrategy waitStrategy){
        ExplicitWaitFactory.performExplicitWait(waitStrategy, by, driver);
        getAction(driver).click(driver.findElement(by)).perform();
    }

    private static Actions getAction(WebDriver driver){
        return new Actions(driver);
    }


}
