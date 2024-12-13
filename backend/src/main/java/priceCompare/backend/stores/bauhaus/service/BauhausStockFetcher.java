package priceCompare.backend.stores.bauhaus.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.dto.ProductParseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
@AllArgsConstructor
@Log4j2
public class BauhausStockFetcher {

    private static final int LOCATION_FETCH_BATCH_SIZE = 20;
    final BauhausApis apis;

    public List<ProductParseDto> fetchLocationInfo(List<ProductParseDto> products) {
        List<ProductParseDto> newProducts = new ArrayList<>();
        List<List<ProductParseDto>> batches = Lists.partition(products, LOCATION_FETCH_BATCH_SIZE);

        for(List<ProductParseDto> batch : batches) {
            List<CompletableFuture<JSONObject>> futures = new ArrayList<>();
            for(ProductParseDto productParseInfo : batch) {
                futures.add(
                        apis.fetchLocationInfoForProduct(productParseInfo.getSku())
                );
            }

            for(int i = 0; i < futures.size(); i++) {
                CompletableFuture<JSONObject> future = futures.get(i);
                ProductParseDto productParseInfo = batch.get(i);

                try {
                    ProductDto product = productParseInfo.getProduct();
                    product = product.toBuilder()
                            .stock(parseResponse(future.join()))
                            .build();
                    productParseInfo.setProduct(product);
                } catch(CompletionException e) {
                    log.printf(Level.WARN, "Bauhaus products service: Error fetching data from URL: %s, Exception: %s\n", productParseInfo.getProduct().getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(productParseInfo);
                }
            }
        }

        return newProducts;
    }

    public StockInLocationsDto parseResponse(JSONObject jsonObject) {
        List<StockDto> stockList = new ArrayList<>();

        try {
            if (jsonObject.has("store_stocks")) {
                JSONArray storeStocks = jsonObject.getJSONArray("store_stocks");

                for (int i = 0; i < storeStocks.length(); i++) {
                    JSONObject storeStock = storeStocks.getJSONObject(i);

                    String name = storeStock.getString("name");
                    JSONArray valueArray = storeStock.optJSONArray("value");
                    String value = (valueArray.length() == 1) ? valueArray.getString(0) : valueArray.getString(1);

                    LocationDto location = mapToBauhausStoreLocation(name);

                    stockList.add(
                            StockDto.builder()
                                    .location(location)
                                    .quantityText(value)
                                    .build()
                    );
                }
            }
        } catch (Exception e) {
            log.printf(Level.WARN, "Failed to parse stock information: %s%n", e);
        }
        return StockInLocationsDto.builder()
                .locations(stockList)
                .build();
    }

    private LocationDto mapToBauhausStoreLocation(String name) {
        return BauhausStoreLocation.locationFromSourceName(name);
    }
    
}
