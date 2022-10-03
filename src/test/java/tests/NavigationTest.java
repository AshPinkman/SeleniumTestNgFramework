package tests;

import base.BaseTest;
import driver.Driver;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StorePage;

public final class NavigationTest extends BaseTest {

    private NavigationTest() {

    }

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {

        StorePage storePage = new HomePage(DriverManager.getDriver())
                .load().getHeaderComponent()
                .clickStoreMenuLink();
        //Assert.assertEquals(storePage.getTitle(), "Store");

    }

}
