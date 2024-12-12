package priceCompare.backend.stores.bauhof.service;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import priceCompare.backend.httpclient.HttpClientService;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class BauhofApis {

    private static final String BAUHOF_API_URL = "https://www.bauhof.ee/api/klevu/search";
    private static final String API_KEY = "---protected---";

    private final HttpClientService httpClientService;

    public URI buildUrl(String keyword){
        return URI.create(String.format("%s?query=%s", BAUHOF_API_URL, URLEncoder.encode(keyword, StandardCharsets.UTF_8)));
    }

    public String buildRequestPayload(String sku) {
        return String.format(
                "[{\"query\":\"#graphql\\n  #graphql\\n#graphql\\nfragment ProductShortProj on ProductInterface {\\n    id\\n    uid\\n    sku\\n    name\\n    stock_status\\n    only_x_left_in_stock\\n    transport\\n    unit_id\\n    brand_name\\n    product_brand\\n    thumbnail {\\n        url\\n        position\\n        disabled\\n        label\\n    }\\n    url_key\\n    url_rewrites {\\n        url\\n    }\\n    deliveryInformation {\\n        name\\n        min_price\\n        from\\n    }\\n    price_range {\\n        maximum_price {\\n            final_price {\\n                currency\\n                value\\n            }\\n            regular_price {\\n                currency\\n                value\\n            }\\n        }\\n        minimum_price {\\n            final_price {\\n                currency\\n                value\\n            }\\n            regular_price {\\n                currency\\n                value\\n            }\\n        }\\n    }\\n    available_price_tiers {\\n        discount {\\n            amount_off\\n            percent_off\\n        }\\n        final_price {\\n            currency\\n            value\\n        }\\n        quantity\\n        price_group_type\\n        price_valid_until\\n        cust_group\\n    }\\n    price_customer {\\n        discount {\\n            amount_off\\n            percent_off\\n        }\\n        final_price {\\n            value\\n            currency\\n        }\\n    }\\n    advanced_inventory {\\n        is_qty_decimal\\n        qty_incrments\\n        min_sale_qty\\n        max_sale_qty\\n    }\\n    __typename\\n}\\n\\nfragment ProductDetailsSensitiveDataProj on ProductInterface {\\n    ...ProductShortProj\\n    transport\\n    foreign_assortment\\n    availability_in_stores {\\n        qty\\n        source_name\\n        source_code\\n        status\\n    }\\n}\\n\\n  query productDetails(\\n      $search: String = \\\"\\\",\\n      $filter: ProductAttributeFilterInput,\\n      $pageSize: Int = 10,\\n      $currentPage: Int = 1,\\n      $sort: ProductAttributeSortInput\\n  )\\n  {\\n      products(search: $search, filter: $filter, sort: $sort, pageSize: $pageSize, currentPage: $currentPage) {\\n          items {\\n              ...ProductDetailsSensitiveDataProj\\n          }\\n      }\\n  }\\n  \",\"queryVariables\":{\"queryType\":\"DETAIL\",\"filter\":{\"sku\":{\"eq\":\"%s\"}}},\"fetchPolicy\":\"no-cache\"}]",
                sku
        );
    }

    public String buildRequestBody(String keyword, int offset, int searchApiPageSize) {
        return String.format(
                "{\"context\":{\"apiKeys\":[\"%s\"]},\"recordQueries\":[{\"id\":\"search\",\"typeOfRequest\":\"SEARCH\",\"settings\":{\"query\":{\"term\":\"%s\"},\"id\":\"search\",\"limit\":%d,\"typeOfRecords\":[\"KLEVU_PRODUCT\"],\"offset\":%d,\"sort\":\"RELEVANCE\"}}]}",
                API_KEY, keyword, searchApiPageSize, offset);
    }

    public CompletableFuture<JSONObject> fetchLocationInfoForProduct(String sku) {
        URI locationUri = URI.create("https://www.bauhof.ee/api/magento/customQuery?locale=et");
        String body = buildRequestPayload(sku);
        return httpClientService.PostWithBodyAsyncAndReturnCompletableFutureHttpResponse(locationUri, body)
                .thenApply(HttpResponse::body)
                .thenApply(JSONObject::new);
    }
}
