package priceCompare.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
	public static final String IMAGE_LOADING_PROXY_URL = "http://16.16.186.149:8080/request/proxy-img-req";

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
