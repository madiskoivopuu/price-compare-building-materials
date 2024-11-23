package priceCompare.backend.stores.krauta.service;

import static priceCompare.backend.utils.CategoryNameChecker.checkIsCorrectProductCategory;
import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetKRautaProductsServiceImpl implements GetStoreProductsService {
    public static final int FETCH_MAX_NUM_PRODUCTS = 128;
    private final KRautaAPIs apis;

    private final LocationStockInformationFetcherKrauta locationStockInformationFetcherKrauta;

    public GetKRautaProductsServiceImpl(KRautaAPIs apis, LocationStockInformationFetcherKrauta locationStockInformationFetcherKrauta) {
        this.apis = apis;
        this.locationStockInformationFetcherKrauta = locationStockInformationFetcherKrauta;
    }

    @Override
    public ProductsDto searchForProducts(String query, Subcategory subcategory) {
        if (subcategory == null && (query == null || query.isEmpty())) {
            return ProductsDto.builder().build();
        }

        if(subcategory != null && query.isEmpty())
            return null;

        return fetchProductsListFromKRauta(query, subcategory);
    }

    private ProductsDto fetchProductsListFromKRauta(String query, Subcategory subcategory) {
        int offset = 0;
        int numProducts = 0;
        List<ProductDto> products = new ArrayList<>();

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

                    String categoryName = singleProductJson.getJSONArray("categories5").toString();
                    if (!checkIsCorrectProductCategory(categoryName, subcategory,
                            EmaterjalToKrautaCategoryMapping.subcatMap)) continue;

                    String linkToProduct = String.format("https://k-rauta.ee%s", singleProductJson.getString("url"));
                    StockInLocationsDto location = locationStockInformationFetcherKrauta.fetchLocationStockInfo(linkToProduct);

                    ProductDto productDto = ProductDto.builder()
                            .store(Store.KRAUTA)
                            .linkToProduct(linkToProduct)
                            .linkToPicture(singleProductJson.getString("image"))
                            .unit(Unit.fromDisplayName(singleProductJson.getString("measurementUnit")))
                            .name(productName)
                            .price(singleProductJson.getDouble("priceDefault"))
                            .stock(location)
                            .build();

                    products.add(productDto);

                } catch (org.json.JSONException e) {
                    System.err.println("K-rauta products service: Error getting certain values from JSON for product: " + e.getMessage());
                    System.err.println(singleProductJson);
                } catch (IllegalArgumentException e) {
                    System.err.println("K-rauta products service: " + e.getMessage());
                    System.err.println(singleProductJson.getString("url"));
                } catch (IOException e) {
                    System.err.println("K-rauta products service: " + e.getMessage());
                }
            }
        } while (offset < numProducts);

        return ProductsDto.builder()
                .products(products)
                .build();
    }
}