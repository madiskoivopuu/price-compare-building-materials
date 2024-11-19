package priceCompare.backend.stores.puumarket.service;

import jdk.jshell.spi.ExecutionControl;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.krauta.service.KRautaAPIs;
import priceCompare.backend.stores.krauta.service.LocationStockInformationFetcherKrauta;

public class GetPuumaterjalProductsServiceImpl implements GetStoreProductsService {
    private final PuumarketAPIs apis;
    private final LocationStockInformationFetcherPuumarket stockFetcher;

    public GetPuumaterjalProductsServiceImpl(PuumarketAPIs apis, LocationStockInformationFetcherPuumarket stockFetcher) {
        this.apis = apis;
        this.stockFetcher = stockFetcher;
    }

    private ProductsDto performPuumaterjalSearch(String query, Subcategory subcategory) {
        return null;
    }

    @Override
    public ProductsDto searchForProducts(String query, Subcategory subcategory) {
        return null;
    }
}
