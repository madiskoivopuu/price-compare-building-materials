package priceCompare.backend.stores.krauta.service;

import com.google.common.collect.Lists;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;
import priceCompare.backend.stores.dto.ProductParseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Service
public class LocationStockInformationFetcherKrauta {

    private static final int LOCATION_FETCH_BATCH_SIZE = 20;
    final KRautaAPIs apis;

    public LocationStockInformationFetcherKrauta(KRautaAPIs apis) {
        this.apis = apis;
    }

    public List<ProductParseDto> fetchLocationStockInfo(List<ProductParseDto> products){
        List<ProductParseDto> newProducts = new ArrayList<>();
        List<List<ProductParseDto>> batches = Lists.partition(products, LOCATION_FETCH_BATCH_SIZE);

        for(List<ProductParseDto> batch : batches) {
            List<CompletableFuture<Document>> futures = new ArrayList<>();
            for(ProductParseDto productParseInfo : batch) {
                String productUrl = productParseInfo.getLinkToProduct();
                futures.add(
                        apis.fetchLocationInfoForProduct(productUrl)
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
                    System.err.printf("EhituseAbc products service: Error fetching data from URL: %s, Exception: %s\n", productParseInfo.getProduct().getLinkToProduct(), e.getMessage());
                } finally {
                    newProducts.add(productParseInfo);
                }
            }
        }
        return newProducts;
    }

    public StockInLocationsDto parseResponse(Document document, String unit) {
        List<StockDto> stockDtoList = new ArrayList<>();

        Elements cityBlocks = document.select("div.city-block.clearfix");

        for (Element cityBlock : cityBlocks) {
            stockDtoList.addAll(parseCityBlock(cityBlock));
        }

        stockDtoList = addLocationsWithNoStock(stockDtoList);
        return StockInLocationsDto.builder().locations(stockDtoList).build();
    }

    public List<StockDto> parseCityBlock(Element cityBlock) {
        List<StockDto> stockDtos = new ArrayList<>();
        Elements storeRows = cityBlock.select("table.stock-stores-widths tr");

        for (Element storeRow : storeRows) {
            StockDto stockDto = parseStoreRow(storeRow);
            if (stockDto != null) {
                stockDtos.add(stockDto);
            }
        }
        return stockDtos;
    }

    public StockDto parseStoreRow(Element storeRow) {
        String storeDetails = storeRow.selectFirst("td.stock-first-td a").text();
        LocationDto locationDto = KrautaStoreLocation.locationFromSourceName(storeDetails);
        if (locationDto == null) {
            return null;
        }

        Integer quantity = parseStockQuantityForRow(storeRow);
        boolean infoUnavailable = (quantity == null);
        String additionalNotes = infoUnavailable ? "Stock information unavailable" : "";

        return StockDto.builder()
                .location(locationDto)
                .quantityText(String.format("%s tk", quantity))
                .additionalNotes(additionalNotes)
                .build();
    }

    private Integer parseStockQuantityForRow(Element storeRow) {
        String stockQuantityText = storeRow.select("td").get(3).text();
        return parseQuantityFromText(stockQuantityText);
    }

    public Integer parseQuantityFromText(String stockQuantityText) {
        try {
            return Integer.parseInt(stockQuantityText.replace("tk", "").trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<StockDto> addLocationsWithNoStock(List<StockDto> stockDtoList) {
        List<StockDto> stockDtoListWithLocationsWithNoStock = new ArrayList<>(stockDtoList);

        Set<LocationDto> existingLocations = stockDtoList.stream()
                .map(StockDto::getLocation)
                .collect(Collectors.toSet());

        for (KrautaStoreLocation krautaStoreLocation : KrautaStoreLocation.values()) {
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
