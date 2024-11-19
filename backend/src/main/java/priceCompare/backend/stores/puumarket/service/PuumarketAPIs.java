package priceCompare.backend.stores.puumarket.service;

import priceCompare.backend.HttpClient.HttpClientService;

public class PuumarketAPIs {
    private final HttpClientService httpClientService;

    public PuumarketAPIs(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }
}
