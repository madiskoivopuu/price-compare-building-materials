package priceCompare.backend.stores.krauta.service;

import static priceCompare.backend.utils.CategoryKeywordChecker.checkContainsRequiredKeyword;
import static priceCompare.backend.utils.CategorySearchKeywordMapping.categoryKeywordMap;
import static priceCompare.backend.utils.CategoryNameChecker.checkIsCorrectProductCategory;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetKRautaProductsService implements GetStoreProductsService {
    public static final int FETCH_MAX_NUM_PRODUCTS = 256;
    private final KRautaAPIs apis;

    private final KrautaStockFetcher krautaStockFetcher;

    @Override
    public ProductsDto searchForProducts(String keyword, Subcategory subcategory) {
        if (subcategory == null && (keyword == null || keyword.isEmpty())) {
            return ProductsDto.builder().build();
        }

        boolean isCategoryOnlySearch = false;

        if (keyword == null || keyword.isEmpty()) {
            isCategoryOnlySearch = true;
            keyword = categoryKeywordMap.get(subcategory);
        }

        return fetchProductsListFromKRauta(keyword, subcategory, isCategoryOnlySearch);
    }

    private ProductsDto fetchProductsListFromKRauta(String query, Subcategory subcategory, boolean isCategoryOnlySearch) {
        int offset = 0;
        int numProducts = 0;
        List<ProductParseDto> products = new ArrayList<>();

        do {
            JSONObject productsJson = apis.fetchPageFromSearchAPI(query, subcategory, offset);
            if (productsJson == null && numProducts == 0) {
                System.err.println("K-rauta products service: very first search API request failed, cannot continue fetching products");
                break;
            }
            if (productsJson == null) { // we should still continue with the rest of the request even if part of products are missing
                offset += KRautaAPIs.SEARCH_API_PAGE_SIZE;
                continue;
            }

            JSONArray docs = productsJson.getJSONArray("docs");
            numProducts = Math.min(productsJson.getInt("doc_count"), FETCH_MAX_NUM_PRODUCTS); // to avoid very time-consuming product fetches, we set the max number to some arbitrary value
            offset += KRautaAPIs.SEARCH_API_PAGE_SIZE;

            for (int i = 0; i < docs.length(); i++) {
                JSONObject singleProductJson = docs.getJSONObject(i);

                if (!singleProductJson.getBoolean("inStock")) continue;

                try {
                    String productName = singleProductJson.getString("title");
                    if (!checkProductNameCorrespondsToSearch(productName, query)) continue;

                    if (isCategoryOnlySearch && !checkContainsRequiredKeyword(productName, subcategory)) continue;

                    String categoryName = singleProductJson.getJSONArray("categories5").toString();
                    if (!checkIsCorrectProductCategory(categoryName, subcategory,
                            EmaterjalToKrautaCategoryMapping.subcatMap)) continue;

                    String linkToProduct = String.format("https://k-rauta.ee%s", singleProductJson.getString("url"));

                    ProductDto product = ProductDto.builder()
                            .store(Store.KRAUTA)
                            .linkToProduct(linkToProduct)
                            .linkToPicture(singleProductJson.getString("image"))
                            .unit(Unit.fromDisplayName(singleProductJson.getString("measurementUnit")))
                            .name(productName)
                            .price(singleProductJson.getDouble("priceDefault"))
                            .build();

                    ProductParseDto productIntermediateInfo = ProductParseDto.builder()
                            .product(product)
                            .linkToProduct(linkToProduct)
                            .build();
                    products.add(productIntermediateInfo);

                } catch (org.json.JSONException e) {
                    System.err.println("K-rauta products service: Error getting certain values from JSON for product: " + e.getMessage());
                    System.err.println(singleProductJson);
                } catch (IllegalArgumentException e) {
                    System.err.println("K-rauta products service: " + e.getMessage());
                    System.err.println(singleProductJson.getString("url"));
                }
            }
        } while (offset < numProducts);

        products = krautaStockFetcher.fetchLocationStockInfo(products);

        return ProductsDto.builder()
                .products(products.stream().map(ProductParseDto::getProduct).toList())
                .build();
    }
}