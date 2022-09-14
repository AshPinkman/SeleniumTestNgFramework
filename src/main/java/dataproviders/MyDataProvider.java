package dataproviders;

import objects.Product;
import org.testng.annotations.DataProvider;
import utils.JacksonUtils;

import java.io.IOException;

public class MyDataProvider {

    @DataProvider(name="getFeaturedProducts",parallel = false)
    public Object[] getFeaturedProducts() throws IOException {
        return  JacksonUtils.deserializeJson("products.json", Product[].class);
    }

}
