package priceCompare.backend.stores.bauhof.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;
import priceCompare.backend.dto.LocationDto;
import priceCompare.backend.dto.StockDto;
import priceCompare.backend.dto.StockInLocationsDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationStockInformationFetcherBauhof {
    private static final String BAUHOF_STOCK_API_URL = "https://www.bauhof.ee/api/magento/customQuery?locale=et";
    private final HttpClientService httpClientService;

    public LocationStockInformationFetcherBauhof(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public StockInLocationsDto getLocationAndStockInformation(String sku) {
        String requestBody = buildRequestPayload(sku);
        JSONObject response = httpClientService.PostWithBody(URI.create(BAUHOF_STOCK_API_URL), requestBody);
        return parseResponse(response);
    }

    public String buildRequestPayload(String sku) {
        return String.format(
                "[{\"query\":\"#graphql\\n  #graphql\\n#graphql\\nfragment ProductShortProj on ProductInterface {\\n    id\\n    uid\\n    sku\\n    name\\n    stock_status\\n    only_x_left_in_stock\\n    transport\\n    unit_id\\n    brand_name\\n    product_brand\\n    thumbnail {\\n        url\\n        position\\n        disabled\\n        label\\n    }\\n    url_key\\n    url_rewrites {\\n        url\\n    }\\n    deliveryInformation {\\n        name\\n        min_price\\n        from\\n    }\\n    price_range {\\n        maximum_price {\\n            final_price {\\n                currency\\n                value\\n            }\\n            regular_price {\\n                currency\\n                value\\n            }\\n        }\\n        minimum_price {\\n            final_price {\\n                currency\\n                value\\n            }\\n            regular_price {\\n                currency\\n                value\\n            }\\n        }\\n    }\\n    available_price_tiers {\\n        discount {\\n            amount_off\\n            percent_off\\n        }\\n        final_price {\\n            currency\\n            value\\n        }\\n        quantity\\n        price_group_type\\n        price_valid_until\\n        cust_group\\n    }\\n    price_customer {\\n        discount {\\n            amount_off\\n            percent_off\\n        }\\n        final_price {\\n            value\\n            currency\\n        }\\n    }\\n    advanced_inventory {\\n        is_qty_decimal\\n        qty_incrments\\n        min_sale_qty\\n        max_sale_qty\\n    }\\n    __typename\\n}\\n\\nfragment ProductDetailsSensitiveDataProj on ProductInterface {\\n    ...ProductShortProj\\n    transport\\n    foreign_assortment\\n    availability_in_stores {\\n        qty\\n        source_name\\n        source_code\\n        status\\n    }\\n}\\n\\n  query productDetails(\\n      $search: String = \\\"\\\",\\n      $filter: ProductAttributeFilterInput,\\n      $pageSize: Int = 10,\\n      $currentPage: Int = 1,\\n      $sort: ProductAttributeSortInput\\n  )\\n  {\\n      products(search: $search, filter: $filter, sort: $sort, pageSize: $pageSize, currentPage: $currentPage) {\\n          items {\\n              ...ProductDetailsSensitiveDataProj\\n          }\\n      }\\n  }\\n  \",\"queryVariables\":{\"queryType\":\"DETAIL\",\"filter\":{\"sku\":{\"eq\":\"%s\"}}},\"fetchPolicy\":\"no-cache\"}]",
                sku
        );
    }

    public StockInLocationsDto parseResponse(JSONObject response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<StockDto> stockList = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(response.toString());
            JsonNode itemsNode = rootNode.path("data").path("products").path("items");

            for (JsonNode itemNode : itemsNode) {
                JsonNode availabilityNode = itemNode.path("availability_in_stores");
                for (JsonNode storeNode : availabilityNode) {
                    String quantity = storeNode.path("qty").asText();
                    String sourceName = storeNode.path("source_name").asText();

                    LocationDto location = mapToBauhofStoreLocation(sourceName);
                    if (location==null) continue;

                    StockDto stock = StockDto.builder()
                            .location(location)
                            .quantityText(String.format("%s tk", quantity))
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
