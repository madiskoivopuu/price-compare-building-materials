package priceCompare.backend.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class FindProductsServiceImplTest {

    @Mock
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Mock
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @InjectMocks
    private FindProductsServiceImpl findProductsService;

    @Test
    public void testFindProducts_WhenBothServicesReturnProducts() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductDto product2 = ProductDto.builder().name("Product 2").price(20.0).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Arrays.asList(product2)).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat" ,Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
        assertTrue(result.getProducts().contains(product2));
    }

    @Test
    public void testFindProducts_WhenOneServiceReturnsEmpty() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat",Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(1, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
    }

    @Test
    public void testFindProducts_WhenBothServicesReturnEmpty() {
        ProductsDto emptyProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }

    @Test
    public void testFindProducts_WhenServiceReturnsNull() {
        ProductsDto bauhofProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(null);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}
