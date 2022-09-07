package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StorePage;

public final class SearchTest extends BaseTest {

    private SearchTest() {

    }

    @Test
    public void searchWithPartialMatch() {

        StorePage storePage = new StorePage(getDriver()).load()
                .search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");

    }

}
