package priceCompare.backend.stores.ehomer.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import priceCompare.backend.BackendApplication;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.*;
import priceCompare.backend.enums.LocationName;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.dto.ProductParseDto;
import priceCompare.backend.stores.puumarket.service.LocationStockInformationFetcherPuumarket;
import priceCompare.backend.stores.puumarket.service.PuumarketAPIs;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static priceCompare.backend.enums.LocationName.EPOOD;
import static priceCompare.backend.stores.ehituseabc.service.EhituseAbcApis.*;
import static priceCompare.backend.stores.ehomer.service.EhomerApis.*;
import static priceCompare.backend.stores.ehomer.service.EmaterjalToEhomerCategoryMapping.categoryMap;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

@Service
public class GetEhomerProductsServiceImpl implements GetStoreProductsService {

    private final HttpClientService httpClientService;

    public GetEhomerProductsServiceImpl(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public ProductsDto searchForProducts(String keyword, Subcategory subcategory) {
        List<ProductDto> products = new ArrayList<>();

        if (subcategory != null) {
            if (keyword == null || keyword.isEmpty()) {
                products.addAll(getProductsByCategory(subcategory, keyword));
            } else {
                products.addAll(getProductsByKeywordAndCategory(keyword, subcategory));
            }
        } else if (keyword != null) {
            products.addAll(getProductsByKeyword(keyword));
        }

        return ProductsDto.builder()
                .products(products)
                .build();
    }

    public List<ProductDto> getProductsByCategory(Subcategory subcategory, String keyword) {
        List<ProductDto> products = new ArrayList<>();
        for (String categoryName : categoryMap.get(subcategory)) {
            JSONObject response = httpClientService.GetJson(getByCategoryUri(categoryName));
            products.addAll(parseResponse(response, keyword));
        }
        return products;
    }

    public List<ProductDto> getProductsByKeyword(String keyword) {
        JSONObject response = httpClientService.GetJson(getByKeywordUri(keyword));
        return new ArrayList<>(parseResponse(response, keyword));
    }

    public List<ProductDto> getProductsByKeywordAndCategory(String keyword, Subcategory subcategory) {
        List<ProductDto> products = new ArrayList<>();
        for (String categoryName : categoryMap.get(subcategory)) {
            JSONObject response = httpClientService.GetJson(getByCategoryAndKeywordUri(keyword, categoryName));
            products.addAll(parseResponse(response, keyword));
        }
        return products;
    }

    private List<ProductDto> parseResponse(JSONObject response, String keyword) {
        JSONArray productsArr = response.getJSONArray("result");

        List<ProductDto> productList = new ArrayList<>();

        LocationDto locationInfo = LocationDto.builder()
                .locationName(EPOOD)
                .address("Ainult epoes")
                .build();
        StockDto stockDto = StockDto.builder()
                .location(locationInfo)
                .build();
        StockInLocationsDto stockInfo = StockInLocationsDto.builder()
                .locations(List.of(stockDto))
                .build();

        for(int i = 0; i < productsArr.length(); i++) {
            JSONObject productNode = productsArr.getJSONObject(i);

            String productName = productNode.getString("name");
            if (keyword!=null && !keyword.isEmpty() && !checkProductNameCorrespondsToSearch(productName, keyword)) continue;

            try {
                ProductDto product = ProductDto.builder()
                        .store(Store.EHOMER)
                        .name(productName)
                        .price(productNode.getDouble("salePrice"))
                        .unit(Unit.TK)
                        .linkToProduct(productNode.getString("url"))
                        .linkToPicture(productNode.getString("image").replace("needtochange/",""))
                        .stock(stockInfo)
                        .build();
                productList.add(product);
            } catch(IllegalArgumentException e) {
                System.err.println("Ehomer products service: " + e.getMessage());
            }
        }
        return productList;
    }
}
