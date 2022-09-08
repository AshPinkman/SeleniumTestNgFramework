package tests;

import base.BaseTest;
import objects.BillingAddress;
import objects.Product;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.StorePage;
import utils.ConfigLoader;
import utils.JacksonUtils;

import java.io.IOException;

public final class MyFirstTest extends BaseTest {

    private MyFirstTest() {

    }

    @Test
    public void guestCheckOutUsingDirectBankTransfer() throws IOException {


        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver()).load().clickStoreMenuLink();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        CartPage cartPage = storePage.clickAddToCartBtn(product.getName()).clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn().setBillingAddress(billingAddress).clickPlaceOrder();


        Assert.assertEquals(checkoutPage.getNotice(),
                "Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckOutUsingDirectBankTransfer() throws IOException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());

        StorePage storePage = new HomePage(getDriver()).load().clickStoreMenuLink();
        storePage.search("Blue");
        Assert.assertEquals(storePage.getTitle(), "Search results: “Blue”");
        CartPage cartPage = storePage.clickAddToCartBtn(product.getName()).clickViewCart();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutBtn();
        checkoutPage.clickShowLogin().login(user).
                setBillingAddress(billingAddress).clickPlaceOrder();


        Assert.assertEquals(checkoutPage.getNotice(),
                "Thank you. Your order has been received.");

    }


}
