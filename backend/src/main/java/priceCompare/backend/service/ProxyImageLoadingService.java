package priceCompare.backend.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;
import priceCompare.backend.HttpClient.HttpClientService;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ProxyImageLoadingService {
    private final List<String> ALLOWED_URLS = List.of(
            "https://cdn.puumarket.ee/"
    );

    private final HttpClientService httpClientService;
    public ProxyImageLoadingService(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    public boolean isUrlAllowed(String url) {
        for(String allowedUrl : ALLOWED_URLS)
            if(url.startsWith(allowedUrl))
                return true;

        return false;
    }

    public ResponseEntity<InputStreamResource> loadImage(String url) {
        if(!isUrlAllowed(url))
            return ResponseEntity.status(403).build();

        HttpResponse<byte[]> resp = httpClientService.GetRawBytes(URI.create(url));
        if(resp == null) {
            return ResponseEntity.internalServerError().build();
        }

        byte[] rawData = resp.body();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(rawData);

        org.springframework.http.HttpHeaders springHeaders = new org.springframework.http.HttpHeaders();
        springHeaders.put("Content-Type", resp.headers().map().get("Content-Type"));

        return ResponseEntity.status(200).headers(springHeaders).contentLength(rawData.length).body(new InputStreamResource(inputStream));
    }
}
