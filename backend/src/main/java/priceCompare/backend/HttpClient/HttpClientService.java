package priceCompare.backend.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class HttpClientService {

    private HttpClient client;

    public HttpClientService() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // concurrent requests in 1 connection
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }
    
    public JSONObject GetJson(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.printf("Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            System.err.printf("Error fetching data from URL: %s, Exception: %s%n", url, e.getMessage());
            return null;
        }
    }

    public String GetString(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.printf("Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return response.body();
        } catch (IOException | InterruptedException e) {
            System.err.printf("Error fetching data from URL: %s, Exception: %s%n", url, e.getMessage());
            return null;
        }
    }

    public CompletableFuture<HttpResponse<String>> GetStringAsync(URI url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public JSONObject PostWithBody(URI url, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.err.printf("Failed to fetch data from URL: %s, Status code: %d%n", url, response.statusCode());
                return null;
            }
            return new JSONObject(response.body());
        } catch (IOException | InterruptedException e) {
            System.err.printf("Error fetching data from URL: %s, Exception: %s%n", url, e.getMessage());
            return null;
        }
    }
}
