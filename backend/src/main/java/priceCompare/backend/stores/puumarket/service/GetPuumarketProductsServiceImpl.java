package priceCompare.backend.stores.puumarket.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import priceCompare.backend.BackendApplication;
import priceCompare.backend.dto.ProductDto;
import priceCompare.backend.dto.ProductsDto;
import priceCompare.backend.enums.Store;
import priceCompare.backend.enums.Subcategory;
import priceCompare.backend.enums.Unit;
import priceCompare.backend.stores.GetStoreProductsService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static priceCompare.backend.utils.ProductNameChecker.checkProductNameCorrespondsToSearch;

@Service
public class GetPuumarketProductsServiceImpl implements GetStoreProductsService {
    private final PuumarketAPIs apis;
    private final LocationStockInformationFetcherPuumarket stockFetcher;

    public GetPuumarketProductsServiceImpl(PuumarketAPIs apis, LocationStockInformationFetcherPuumarket stockFetcher) {
        this.apis = apis;
        this.stockFetcher = stockFetcher;
    }

    private ProductsDto performPuumaterjalSearch(String query, Subcategory subcategory) {
        List<ProductDto> products = new ArrayList<>();

        for(Document searchResponse : apis.performSearchOrCategoryPageFetch(query, subcategory)) {
            for(Element productEl : searchResponse.select(".grid > div.item")) {
                // filter out non-products
                String productUrl = productEl.select(".product-title > a").attr("href");
                if(!productUrl.contains("/toode/")) continue;

                String productName = productEl.select(".product-title > a").text();
                if (!checkProductNameCorrespondsToSearch(productName, query)) continue;

                try {
                    // we need to proxy the image load due to CORS crap
                    String realImageLink = productEl.select("img").attr("src");
                    String proxiedImageLink = String.format("%s?link=%s", BackendApplication.IMAGE_LOADING_PROXY_URL, URLEncoder.encode(realImageLink, StandardCharsets.UTF_8));

                    ProductDto product = ProductDto.builder()
                            .store(Store.PUUMARKET)
                            .name(productName)
                            .price(Double.parseDouble(productEl.select(".text-primary > .amount > bdi").text().replace("€", "")))
                            .unit(Unit.fromDisplayName(productEl.select(".product-price > .text-primary > .badge").text()))
                            .stock(stockFetcher.parseStockInfo(productEl))
                            .linkToProduct(productUrl)
                            .linkToPicture(proxiedImageLink)
                            .build();
                    products.add(product);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    System.err.println("Puumarket products service: " + e.getMessage());
                    System.err.println(searchResponse.select(".product-title > a").attr("href"));
                }
            }
        }

        return ProductsDto.builder()
                .products(products)
                .build();
    }

    @Override
    public ProductsDto searchForProducts(String query, Subcategory subcategory) {
        if((query == null || query.length() < 2) && subcategory == null)
            return ProductsDto.builder().build();

        return performPuumaterjalSearch(query, subcategory);
    }
}
