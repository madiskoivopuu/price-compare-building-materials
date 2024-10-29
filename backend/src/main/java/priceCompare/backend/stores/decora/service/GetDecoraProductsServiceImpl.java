package priceCompare.backend.stores.decora.service;

import org.json.JSONArray;
import org.json.JSONObject;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Category;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.stores.GetStoreProductsService;
import priceCompare.backend.stores.krauta.service.KRautaAPIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetDecoraProductsServiceImpl implements GetStoreProductsService {

    private final int FETCH_MAX_NUM_PRODUCTS = 1024;
    private final DecoraAPIs apis;

    public GetDecoraProductsServiceImpl(DecoraAPIs apis) {
        this.apis = apis;
    }
    /**
     * Fetches the products matching the query & subcategory using decora.ee search API
     * @param query Query to search products with
     * @param category Ematerjal.ee main category
     * @param subcategory Ematerjal.ee subcategory
     * @param productSKUs A map that connects each ProductDto with an SKU that the search API gave for it
     * @return Returns a list of ProductDto, with every field except for LocationDto filled
     */
    private List<ProductDto> performDecoraSearch(String query, Category category, Subcategory subcategory, HashMap<ProductDto, String> productSKUs) {
        int offset = 0;
        int numProducts = 0;
        List<ProductDto> products = new ArrayList<>();

        do {
            JSONObject responseJson = apis.fetchPageFromSearchAPI(query, subcategory, offset);
            if (responseJson == null && numProducts == 0) {
                System.err.println("Decora products service: very first search API request failed, cannot continue fetching products");
                break;
            }
            if (responseJson == null) { // we should still continue with the rest of the request even if part of products are missing
                offset += DecoraAPIs.SEARCH_API_PAGE_SIZE;
                continue;
            }

            JSONObject meta = responseJson.getJSONArray("queryResults").getJSONObject(0).getJSONObject("meta");
            numProducts = Math.min(meta.getInt("totalResultsFound"), FETCH_MAX_NUM_PRODUCTS);
        } while(offset < numProducts);
    }

    @Override
    public ProductsDto searchForProducts(String query, Category category, Subcategory subcategory) {

        return null;
    }
}
