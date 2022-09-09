package api;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import objects.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;

    public Cookies getCookies() {
        return cookies;
    }

    private String fetchRegisterNonceValueUsingJsoup() {
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }

    private Response getAccount() {

        Cookies cookies1 = new Cookies();

        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                cookies(cookies1).
                log().all().
                when().
                get("/account").
                then().
                log().all().
                extract().
                response();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account" + response.getStatusCode());
        }

        return response;
    }

    public Response register(User user) {

        Cookies cookies1 = new Cookies();
        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        Response response = given().
                baseUri(ConfigLoader.getInstance().getBaseUrl()).
                headers(headers).
                formParams(formParams).
                cookies(cookies1).
                log().all().
                when().
                post("/account").
                then().
                log().all().
                extract().
                response();

        if (response.getStatusCode() != 302) {
            throw new RuntimeException("Failed to register the account " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;

    }

}
