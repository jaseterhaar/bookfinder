package nl.bookfinder.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleBooksApiConfig {
	@Value("${google.books.api.key}")
	private String apiKey;

	public String getApiKey() {
		return apiKey;
	}
}
