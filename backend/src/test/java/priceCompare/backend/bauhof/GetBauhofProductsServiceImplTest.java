package priceCompare.backend.bauhof;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;


@SpringBootTest
public class GetBauhofProductsServiceImplTest {

    @Autowired
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Test
    public void testGetBauhofProducts() {
        String keyword = "kipsplaat";

        ProductsDto products = getBauhofProductsService.getBauhofProducts(keyword, null, null);

        System.out.println(products.getProducts());

        assertNotNull(products, "ProductsDto should not be null");
        assertNotNull(products.getProducts(), "Product list should not be null");
        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");

        assertNotNull(products.getProducts().getFirst().getName(), "Product name should not be null");
        assertNotNull(products.getProducts().getFirst().getPrice(), "Product price should not be null");
    }
}
