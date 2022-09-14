package tests;

import base.BaseTest;
import dataproviders.MyDataProvider;
import objects.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.StorePage;

import java.io.IOException;

public final class AddToCartTest extends BaseTest {

    private AddToCartTest() {

    }

    @Test
    public void addToCartFromStorePage() throws IOException {

        Product product = new Product(1215);
        CartPage cartPage  = new StorePage(getDriver())
                .load().getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());

    }

    @Test(dataProvider = "getFeaturedProducts",dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProducts(Product product){

        CartPage cartPage = new HomePage(getDriver())
                .load().getProductComponent()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }


}
