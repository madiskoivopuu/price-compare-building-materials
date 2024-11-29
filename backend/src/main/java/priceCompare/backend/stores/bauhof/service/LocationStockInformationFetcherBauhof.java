package priceCompare.backend.stores.bauhof.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Service
public class LocationStockInformationFetcherBauhof {

    private static final int LOCATION_FETCH_BATCH_SIZE = 20;
    final BauhofApis apis;

    public LocationStockInformationFetcherBauhof(BauhofApis apis) {
        this.apis = apis;}

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
                    System.err.printf("Bauhof products service: Error fetching data from URL: %s, Exception: %s\n", productParseInfo.getProduct().getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(productParseInfo);
                }
            }
        }
        return newProducts;
    }

    public StockInLocationsDto parseResponse(JSONObject response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockDto> stockList = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(response.toString());
            JsonNode itemsNode = rootNode.path("data").path("products").path("items");

            for (JsonNode itemNode : itemsNode) {
                JsonNode availabilityNode = itemNode.path("availability_in_stores");
                String unit = itemNode.path("unit_id").asText();
                for (JsonNode storeNode : availabilityNode) {
                    String quantity = storeNode.path("qty").asText();
                    String sourceName = storeNode.path("source_name").asText();

                    LocationDto location = mapToBauhofStoreLocation(sourceName);
                    if (location==null) continue;

                    StockDto stock = StockDto.builder()
                            .location(location)
                            .quantityText(String.format("%s %s", quantity, unit))
                            .build();

                    stockList.add(stock);
                }
            }
        } catch (Exception e) {
            System.err.printf("Failed to parse stock information: %s%n", e);
        }
        stockList = addLocationsWithNoStock(stockList);
        return StockInLocationsDto.builder().locations(stockList).build();
    }

    private LocationDto mapToBauhofStoreLocation(String sourceName) {
        return BauhofStoreLocation.locationFromSourceName(sourceName);
    }

    public List<StockDto> addLocationsWithNoStock(List<StockDto> stockDtoList) {
        List<StockDto> stockDtoListWithLocationsWithNoStock = new ArrayList<>(stockDtoList);

        Set<LocationDto> existingLocations = stockDtoList.stream()
                .map(StockDto::getLocation)
                .collect(Collectors.toSet());

        for (BauhofStoreLocation krautaStoreLocation : BauhofStoreLocation.values()) {
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
