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
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class FindProductsServiceImplTest {

    @Mock
    private GetBauhofProductsServiceImpl getBauhofProductsService;

    @Mock
    private GetKRautaProductsServiceImpl getKRautaProductsService;

    @Mock
    private GetEspakProductsServiceImpl getEspakProductsService;

    @InjectMocks
    private FindProductsServiceImpl findProductsService;

    @Test
    public void testFindProducts_WhenBothServicesReturnProducts() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductDto product2 = ProductDto.builder().name("Product 2").price(20.0).build();
        ProductDto product3 = ProductDto.builder().name("Product 3").price(0.9).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Arrays.asList(product2)).build();
        ProductsDto espakProducts = ProductsDto.builder().products(Arrays.asList(product3)).build();


        when(getBauhofProductsService.getBauhofProducts("kipsplaat" ,Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(espakProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(3, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
        assertTrue(result.getProducts().contains(product2));
    }

    @Test
    public void testFindProducts_WhenOneServiceReturnsEmpty() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductDto product2 = ProductDto.builder().name("Product 2").price(11.9).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto espakProducts = ProductsDto.builder().products(Arrays.asList(product2)).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat",Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(espakProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(2, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
    }

    @Test
    public void testFindProducts_WhenAllServicesReturnEmpty() {
        ProductsDto emptyProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }

    @Test
    public void testFindProducts_WhenServiceReturnsNull() {
        ProductsDto bauhofProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto emptyProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.getBauhofProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.getKRautaProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(null);
        when(getEspakProductsService.searchForProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Category.EHITUSPLAADID, Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}
