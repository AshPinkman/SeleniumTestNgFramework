package factories;

import enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;

import java.time.Duration;

public final class ExplicitWaitFactory {

    private ExplicitWaitFactory() {

    }

    public static void performExplicitWait(WaitStrategy waitStrategy, By by, WebDriver driver) {


        if (waitStrategy == WaitStrategy.CLICKABLE) {
            new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getInstance().getTimeout()))
                    .until(ExpectedConditions.elementToBeClickable(by));
        } else if (waitStrategy == WaitStrategy.PRESENCE) {
            new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getInstance().getTimeout()))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitStrategy == WaitStrategy.VISIBLE) {
            new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getInstance().getTimeout()))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (waitStrategy == WaitStrategy.INVISIBLE) {
            new WebDriverWait(driver, Duration.ofSeconds(ConfigLoader.getInstance().getTimeout()))
                    .until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(by)));
        } else {
            System.out.println("No Wait Strategy");
        }

    }

}
