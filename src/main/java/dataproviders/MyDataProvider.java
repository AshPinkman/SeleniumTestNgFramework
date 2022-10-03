package dataproviders;

import objects.Product;
import org.testng.annotations.DataProvider;
import utils.ExcelUtils;
import utils.JacksonUtils;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name = "getFeaturedProducts")
    public Object[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson("products.json", Product[].class);
    }

    @DataProvider(name = "getLoginData")
    public Object[][] getLoginData() throws IOException {
        return ExcelUtils.getData();
    }

}
