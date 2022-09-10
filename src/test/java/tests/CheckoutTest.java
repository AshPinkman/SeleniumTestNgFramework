package tests;

import api.CartApi;
import api.SignUpApi;
import base.BaseTest;
import objects.BillingAddress;
import objects.Product;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import utils.FakerUtils;
import utils.JacksonUtils;

import java.io.IOException;

public final class CheckoutTest extends BaseTest {

    private CheckoutTest() {

    }

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215, 1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkoutPage.load().
                setBillingAddress(billingAddress).
                clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(),
                "Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("billingAddress.json", BillingAddress.class);
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        Product product = new Product(1215);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load().
                setBillingAddress(billingAddress).
                clickPlaceOrder();

        Assert.assertEquals(checkoutPage.getNotice(),
                "Thank you. Your order has been received.");
    }


}
