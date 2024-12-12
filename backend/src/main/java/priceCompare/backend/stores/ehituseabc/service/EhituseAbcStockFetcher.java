package priceCompare.backend.stores.ehituseabc.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.dto.ProductParseDto;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class EhituseAbcStockFetcher {
    private static final int LOCATION_FETCH_BATCH_SIZE = 20;
    final EhituseAbcApis apis;

    public List<ProductParseDto> fetchLocationInfo(List<ProductParseDto> products) {
        List<ProductParseDto> newProducts = new ArrayList<>();
        List<List<ProductParseDto>> batches = Lists.partition(products, LOCATION_FETCH_BATCH_SIZE);

        for(List<ProductParseDto> batch : batches) {
            List<CompletableFuture<Document>> futures = new ArrayList<>();
            for(ProductParseDto productParseInfo : batch) {
                JSONObject productJson = new JSONObject(productParseInfo.getSearchApiProductInfo());
                futures.add(
                        apis.fetchLocationInfoForProduct(productJson.getString("id").substring(1))
                );
            }

            for(int i = 0; i < futures.size(); i++) {
                CompletableFuture<Document> future = futures.get(i);
                ProductParseDto productParseInfo = batch.get(i);

                try {
                    Document locationsHtml = future.join();
                    ProductDto product = productParseInfo.getProduct();
                    product = product.toBuilder()
                            .stock(parseResponse(locationsHtml, product.getUnit().getDisplayName()))
                            .build();
                    productParseInfo.setProduct(product);
                } catch(CompletionException e) {
                    log.printf(Level.WARN, "EhituseAbc products service: Error fetching data from URL: %s, Exception: %s\n", productParseInfo.getProduct().getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(productParseInfo);
                }
            }
        }

        return newProducts;
    }

    public StockInLocationsDto parseResponse(Document doc, String unit) {
        List<StockDto> stockList = new ArrayList<>();

        try {
            Elements rows = doc.select("table.table tbody tr");

            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() < 2) continue;

                String storeName = columns.get(0).text();
                String quantityText = columns.get(1).text();

                LocationDto location = mapToEhituseAbcStoreLocation(storeName);
                if (location == null) continue;

                stockList.add(
                        StockDto.builder()
                                .location(location)
                                .quantityText(quantityText + " " + unit)
                                .build()
                );
            }
        } catch (Exception e) {
            log.printf(Level.WARN, "Failed to parse stock information: %s%n", e.getMessage());
        }

        stockList = addLocationsWithNoStock(stockList);
        return StockInLocationsDto.builder().locations(stockList).build();
    }


    private LocationDto mapToEhituseAbcStoreLocation(String sourceName) {
        return EhituseAbcStoreLocation.locationFromSourceName(sourceName);
    }

    public List<StockDto> addLocationsWithNoStock(List<StockDto> stockDtoList) {
        List<StockDto> stockDtoListWithLocationsWithNoStock = new ArrayList<>(stockDtoList);

        Set<LocationDto> existingLocations = stockDtoList.stream()
                .map(StockDto::getLocation)
                .collect(Collectors.toSet());

        for (EhituseAbcStoreLocation krautaStoreLocation : EhituseAbcStoreLocation.values()) {
            LocationDto location = krautaStoreLocation.getLocation();
            if (!existingLocations.contains(location)) {
                stockDtoListWithLocationsWithNoStock.add(createEmptyStockDto(location));
            }
        }
        return stockDtoListWithLocationsWithNoStock;
    }

    private static StockDto createEmptyStockDto(LocationDto location) {
        return StockDto.builder()
                .location(location)
                .quantityText("0 tk")
                .build();
    }
}
