package priceCompare.backend.stores.ehomer.service;

import static priceCompare.backend.enums.LocationName.EPOOD;
import static priceCompare.backend.stores.ehomer.service.EhomerApis.*;
import static priceCompare.backend.stores.ehomer.service.EmaterjalToEhomerCategoryMapping.categoryMap;
import static priceCompare.backend.utils.CategoryKeywordChecker.checkContainsRequiredKeyword;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.httpclient.HttpClientService;
import priceCompare.backend.dto.*;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GetEhomerProductsService implements GetStoreProductsService {

    private final HttpClientService httpClientService;

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
            JSONObject response = httpClientService.GetAndReturnJson(getByCategoryUri(categoryName));
            products.addAll(parseResponse(response, keyword, subcategory));
        }
        return products;
    }

    public List<ProductDto> getProductsByKeyword(String keyword) {
        JSONObject response = httpClientService.GetAndReturnJson(getByKeywordUri(keyword));
        return new ArrayList<>(parseResponse(response, keyword, null));
    }

    public List<ProductDto> getProductsByKeywordAndCategory(String keyword, Subcategory subcategory) {
        List<ProductDto> products = new ArrayList<>();
        for (String categoryName : categoryMap.get(subcategory)) {
            JSONObject response = httpClientService.GetAndReturnJson(getByCategoryAndKeywordUri(keyword, categoryName));
            products.addAll(parseResponse(response, keyword, subcategory));
        }
        return products;
    }

    private List<ProductDto> parseResponse(JSONObject response, String keyword, Subcategory subcategory) {
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
            if ((keyword == null || keyword.isEmpty()) && !checkContainsRequiredKeyword(productName, subcategory)) continue;

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
                log.printf(Level.WARN, "Ehomer products service: ", e.getMessage());
            }
        }
        return productList;
    }
}
