package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.StorePage;

public final class NavigationTest extends BaseTest {

    private NavigationTest() {

    }

    @Test
    public void navigateFromHomeToStoreUsingMainMenu() {

        StorePage storePage = new HomePage(getDriver())
                .load()
                .clickStoreMenuLink();
        //Assert.assertEquals(storePage.getTitle(), "Store");

    }

}
