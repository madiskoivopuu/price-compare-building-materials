package priceCompare.backend.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class HttpClientService {
    private HttpClient client;

    public HttpClientService() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // concurrent requests in 1 connection
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }
    
    public JSONObject GetAndReturnJson(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.printf(Level.WARN, "Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            log.printf(Level.WARN, "Error fetching data from URL: %s, exception: %s %n", e.getMessage(), url);
            return null;
        }
    }

    public HttpResponse<byte[]> GetAndReturnRawBytes(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200) {
                log.printf(Level.WARN, "Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return response;
        } catch (IOException | InterruptedException e) {
            log.printf(Level.WARN, "Error fetching data from URL: %s, exception: %s %n", e.getMessage(), url);
            return null;
        }
    }

    public String GetAndReturnString(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.printf(Level.WARN, "Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            log.printf(Level.WARN, "Error fetching data from URL: %s, exception: %s %n", e.getMessage(), url);
            return null;
        }
    }

    public CompletableFuture<HttpResponse<String>> GetAsyncAndReturnCompletableFutureHttpResponse(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public CompletableFuture<HttpResponse<String>> PostWithBodyAsyncAndReturnCompletableFutureHttpResponse(URI url, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public JSONObject PostWithBodyAndReturnJson(URI url, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json;charset=UTF-8")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                log.printf(Level.WARN, "Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            log.printf(Level.WARN, "Error fetching data from URL: %s, exception: %s %n", e.getMessage(), url);
            return null;
        }
    }
}
