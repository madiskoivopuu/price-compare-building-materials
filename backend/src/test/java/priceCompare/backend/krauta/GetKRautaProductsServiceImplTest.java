package priceCompare.backend.krauta;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;


@SpringBootTest
public class GetKRautaProductsServiceImplTest {

    @Autowired
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Test
    public void testRegularKrautaSearch() {
        String keyword = "kipsplaat";

        ProductsDto products = getKRautaProductsService.getKRautaProducts(keyword, null, null);

        assertNotNull(products, "ProductsDto should not be null");
        assertNotNull(products.getProducts(), "Product list should not be null");
        assertNotNull(products.getProducts().getFirst().getName(), "Product name should not be null");
        assertNotNull(products.getProducts().getFirst().getPrice(), "Product price should not be null");
        assertNotNull(products.getProducts().getFirst().getLinkToProduct(), "Product URL should not be null");
        assertNotNull(products.getProducts().getFirst().getUnit(), "Product unit type should not be null");
        assertFalse(products.getProducts().isEmpty(), "Product list should not be empty");

        assertTrue(products.getProducts().getFirst().getLinkToProduct().contains("https://www.k-rauta.ee/p/"), "Product URL should point to the k-rauta.ee domain");
    }
}
