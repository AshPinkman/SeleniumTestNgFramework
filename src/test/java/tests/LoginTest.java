package tests;

import api.CartApi;
import api.SignUpApi;
import base.BaseTest;
import driver.Driver;
import driver.DriverManager;
import objects.Product;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import utils.FakerUtils;

import java.io.IOException;

public final class LoginTest extends BaseTest {

    private LoginTest() {

    }

    @Test
    public void loginDuringCheckout() throws IOException {

        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        Product product = new Product(1215);

        CartApi cartApi = new CartApi();
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver()).load();

        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load();

        checkoutPage.clickShowLogin().login(user);

        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));


    }

}
