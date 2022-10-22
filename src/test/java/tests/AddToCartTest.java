package tests;

import annotations.FrameworkAnnotation;
import base.BaseTest;
import dataproviders.MyDataProvider;
import driver.Driver;
import driver.DriverManager;
import objects.Product;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.StorePage;

import java.io.IOException;

public final class AddToCartTest extends BaseTest {

    private AddToCartTest() {

    }
    @FrameworkAnnotation(author = "Ashfaq",category = "Smoke")
    @Test
    public void addToCartFromStorePage() throws IOException {

        Product product = new Product(1215);
        CartPage cartPage = new StorePage(DriverManager.getDriver())
                .load().getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }

    //@Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProducts(Product product) {

        CartPage cartPage = new HomePage(DriverManager.getDriver())
                .load().getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }


}
