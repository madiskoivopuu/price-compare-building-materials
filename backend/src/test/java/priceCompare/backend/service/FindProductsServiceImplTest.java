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
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.bauhof.service.GetBauhofProductsServiceImpl;
import priceCompare.backend.stores.decora.service.GetDecoraProductsServiceImpl;
import priceCompare.backend.stores.ehituseabc.service.GetEhituseAbcProductsServiceImpl;
import priceCompare.backend.stores.espak.service.GetEspakProductsServiceImpl;
import priceCompare.backend.stores.krauta.service.GetKRautaProductsServiceImpl;
import priceCompare.backend.stores.puumarket.service.GetPuumarketProductsServiceImpl;

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

    @Mock
    private GetDecoraProductsServiceImpl getDecoraProductsService;

    @Mock
    private GetPuumarketProductsServiceImpl getPuumarketProductsService;

    @Mock
    private GetEhituseAbcProductsServiceImpl getEhituseAbcProductsService;

    @InjectMocks
    private FindProductsServiceImpl findProductsService;

    @Test
    public void testFindProducts_WhenBothServicesReturnProducts() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductDto product2 = ProductDto.builder().name("Product 2").price(20.0).build();
        ProductDto product3 = ProductDto.builder().name("Product 3").price(0.9).build();
        ProductDto product4 = ProductDto.builder().name("Product 4").price(1.9).build();
        ProductDto product5 = ProductDto.builder().name("Product 5").price(1.9).build();
        ProductDto product6 = ProductDto.builder().name("Product 6").price(1.9).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Arrays.asList(product2)).build();
        ProductsDto espakProducts = ProductsDto.builder().products(Arrays.asList(product3)).build();
        ProductsDto decoraProducts = ProductsDto.builder().products(Arrays.asList(product4)).build();
        ProductsDto puumarketProducts = ProductsDto.builder().products(Arrays.asList(product5)).build();
        ProductsDto ehituseAbcProducts = ProductsDto.builder().products(Arrays.asList(product6)).build();


        when(getBauhofProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(espakProducts);
        when(getDecoraProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(decoraProducts);
        when(getPuumarketProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(puumarketProducts);
        when(getEhituseAbcProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(ehituseAbcProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(6, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
        assertTrue(result.getProducts().contains(product2));
        assertTrue(result.getProducts().contains(product3));
        assertTrue(result.getProducts().contains(product4));
        assertTrue(result.getProducts().contains(product5));
        assertTrue(result.getProducts().contains(product6));
    }

    @Test
    public void testFindProducts_WhenOneServiceReturnsEmpty() {
        ProductDto product1 = ProductDto.builder().name("Product 1").price(10.0).build();
        ProductDto product2 = ProductDto.builder().name("Product 2").price(20.0).build();
        ProductDto product3 = ProductDto.builder().name("Product 3").price(0.9).build();
        ProductDto product4 = ProductDto.builder().name("Product 4").price(1.9).build();
        ProductDto product5 = ProductDto.builder().name("Product 5").price(1.9).build();
        ProductDto product6 = ProductDto.builder().name("Product 6").price(1.9).build();
        ProductsDto bauhofProducts = ProductsDto.builder().products(Arrays.asList(product1)).build();
        ProductsDto kRautaProducts = ProductsDto.builder().products(Arrays.asList(product2)).build();
        ProductsDto espakProducts = ProductsDto.builder().products(Arrays.asList(product3)).build();
        ProductsDto decoraProducts = ProductsDto.builder().products(Arrays.asList(product4)).build();
        ProductsDto puumarketProducts = ProductsDto.builder().products(Arrays.asList(product5)).build();
        ProductsDto ehituseAbcProducts = ProductsDto.builder().products(Arrays.asList(product6)).build();

        when(getBauhofProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(kRautaProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(espakProducts);
        when(getDecoraProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(decoraProducts);
        when(getPuumarketProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(puumarketProducts);
        when(getEhituseAbcProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(ehituseAbcProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertEquals(6, result.getProducts().size());
        assertTrue(result.getProducts().contains(product1));
    }

    @Test
    public void testFindProducts_WhenAllServicesReturnEmpty() {
        ProductsDto emptyProducts = ProductsDto.builder().products(Collections.emptyList()).build();

        when(getBauhofProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getKRautaProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getEspakProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getDecoraProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getPuumarketProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getEhituseAbcProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }

    @Test
    public void testFindProducts_WhenServiceReturnsNull() {
        ProductsDto bauhofProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto emptyProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto decoraProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto puumarketProducts = ProductsDto.builder().products(Collections.emptyList()).build();
        ProductsDto ehituseAbcProducts = ProductsDto.builder().products(Collections.emptyList()).build();


        when(getBauhofProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(bauhofProducts);
        when(getKRautaProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(null);
        when(getEspakProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(emptyProducts);
        when(getDecoraProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(decoraProducts);
        when(getPuumarketProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(puumarketProducts);
        when(getEhituseAbcProductsService.searchForProducts("kipsplaat", Subcategory.KIPSPLAAT)).thenReturn(ehituseAbcProducts);

        ProductsDto result = findProductsService.findProducts("kipsplaat", Subcategory.KIPSPLAAT);

        assertNotNull(result);
        assertTrue(result.getProducts().isEmpty());
    }
}
