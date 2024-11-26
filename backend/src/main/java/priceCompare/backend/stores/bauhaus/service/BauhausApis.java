package priceCompare.backend.stores.bauhaus.service;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import priceCompare.backend.HttpClient.HttpClientService;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class BauhausApis {

    private final HttpClientService httpClientService;
    public BauhausApis(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    static String buildRequestBodyWithKeyword(String keyword, String apiKey, int searchApiPageSize) {
        return String.format(
                """
                {
                    "context": {
                        "apiKeys": ["%s"]
                    },
                    "recordQueries": [
                        {
                            "id": "productList",
                            "typeOfRequest": "SEARCH",
                            "settings": {
                                "query": {
                                    "term": "%s"
                                },
                                "typeOfRecords": ["KLEVU_PRODUCT"],
                                "limit": %d,
                                "priceFieldSuffix": "EUR-Default",
                                "filters": {
                                    "filtersToReturn": {
                                        "enabled": true,
                                        "rangeFilterSettings": [
                                            {
                                                "key": "klevu_price",
                                                "minMax": "true"
                                            }
                                        ]
                                    }
                                }
                            }
                        }
                    ]
                }
                """,
                apiKey, keyword, searchApiPageSize, searchApiPageSize
        );
    }

    static String buildRequestBodyWithKeywordAndCategory(String keyword, List<String> categoryValues, String apiKey, int searchApiPageSize) {
        String categoriesJson = categoryValues.stream()
                .map(value -> String.format("\"%s\"", value))
                .collect(Collectors.joining(", "));
        return String.format(
                """
                        {
                            "context": {
                                "apiKeys": ["%s"]
                            },
                            "recordQueries": [
                                {
                                    "id": "productList",
                                    "typeOfRequest": "SEARCH",
                                    "settings": {
                                        "query": {
                                            "term": "%s"
                                        },
                                        "typeOfRecords": ["KLEVU_PRODUCT"],
                                        "limit": %d,
                                        "typeOfSearch": "AND",
                                        "sort": "PRICE_ASC",
                                        "priceFieldSuffix": "EUR-Default",
                                        "fallbackQueryId": "productListFallback",
                                        "personalisation": {
                                          "enablePersonalisation": true
                                            }
                                        },
                                        "filters": {
                                            "filtersToReturn": {
                                                "enabled": true,
                                                "rangeFilterSettings": [
                                                    {
                                                        "key": "klevu_price",
                                                        "minMax": "true"
                                                    }
                                                ]
                                            },
                                            "applyFilters": {
                                                "filters": [
                                                    {
                                                        "key": "category",
                                                        "values": [%s],
                                                        "settings": {
                                                            "singleSelect": "false"
                                                        }
                                                    }
                                                ]
                                            }
                                        }
                                    }
                            ]
                        }
                        """,
                apiKey, keyword, searchApiPageSize, categoriesJson
        );
    }

    static String buildRequestBodyWithCategory(String categoryPath, String apiKey, int searchApiPageSize) {
        return String.format(
                """
                {
                    "context": {
                        "apiKeys": ["%s"]
                    },
                    "recordQueries": [
                        {
                            "id": "productList",
                            "typeOfRequest": "CATNAV",
                            "settings": {
                                "query": {
                                    "term": "*",
                                    "categoryPath": "%s"
                                },
                                "typeOfRecords": ["KLEVU_PRODUCT"],
                                "limit": %d,
                                "sort": "PRICE_ASC",
                                "priceFieldSuffix": "EUR-Default",
                                "personalisation": {
                                    "enablePersonalisation": true
                                }
                            },
                            "filters": {
                                "filtersToReturn": {
                                    "enabled": true,
                                    "rangeFilterSettings": [
                                        {
                                            "key": "klevu_price",
                                            "minMax": "true"
                                        }
                                    ]
                                }
                            }
                        }
                    ]
                }
                """,
                apiKey, categoryPath, searchApiPageSize
        );
    }

    public CompletableFuture<JSONObject> fetchLocationInfoForProduct(String sku) {
        URI locationUri = URI.create("https://www.bauhaus.ee/rest/V1/bauhaus/scenarios/product");
        String body = String.format("{\"sku\":\"%s\",\"storeId\":\"5\"}", sku);
        return httpClientService.PostStringAsync(locationUri, body)
                .thenApply(HttpResponse::body)
                .thenApply(JSONObject::new);
    }

}
