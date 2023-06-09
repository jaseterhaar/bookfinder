package nl.bookfinder.service.external;

import nl.bookfinder.dto.BookDTO;
import nl.bookfinder.service.external.model.GoogleBookItem;
import nl.bookfinder.service.external.model.GoogleBookResponse;
import nl.bookfinder.service.external.model.IndustryIdentifier;
import nl.bookfinder.service.external.model.VolumeInfo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Flux;


@Service
public class GoogleBooksApiClient {
    private final WebClient webClient;
    private static final int MAX_RESULTS = 10;

    public GoogleBooksApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/books/v1").build();
    }

    public Flux<BookDTO> searchBooksByTitleAndLanguage(String title, String language) {
        String uri = UriComponentsBuilder.fromUriString("https://www.googleapis.com/books/v1/volumes")
                .queryParam("q", "intitle:" + title)
                .queryParam("langRestrict", language)
                .queryParam("orderBy", "newest")
                .queryParam("maxResults", MAX_RESULTS)
                .queryParam("fields", "items(volumeInfo/title,volumeInfo/authors,volumeInfo/publishedDate,volumeInfo/industryIdentifiers)")
                .build()
                .toString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(GoogleBookResponse.class)
                .flatMapIterable(GoogleBookResponse::getItems)
                .map(this::mapToBookDTO);
                
    }

	private BookDTO mapToBookDTO(GoogleBookItem item) {
		BookDTO bookDTO = new BookDTO();
		VolumeInfo volumeInfo = item.getVolumeInfo();

		bookDTO.setTitle(volumeInfo.getTitle());
		if(volumeInfo.getAuthors() != null) {
			bookDTO.setAuthor(String.join(", ", volumeInfo.getAuthors()));
		}
		bookDTO.setPublicationDate(volumeInfo.getPublishedDate());
		bookDTO.setIsbn(extractIsbn(volumeInfo.getIndustryIdentifiers()));

		return bookDTO;
	}
    
    private String extractIsbn(List<IndustryIdentifier> industryIdentifiers) {
        if (industryIdentifiers != null && !industryIdentifiers.isEmpty()) {
            for (IndustryIdentifier identifier : industryIdentifiers) {
                if ("ISBN_13".equals(identifier.getType())) {
                    return identifier.getIdentifier();
                }
            }
        }
        return null;
    }
    
}
