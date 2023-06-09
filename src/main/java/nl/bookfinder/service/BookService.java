package nl.bookfinder.service;

import org.springframework.stereotype.Service;

import nl.bookfinder.dto.BookDTO;
import nl.bookfinder.service.external.GoogleBooksApiClient;
import reactor.core.publisher.Flux;

@Service
public class BookService {
    private final GoogleBooksApiClient googleBooksApiClient;

    public BookService(GoogleBooksApiClient googleBooksApiClient) {
        this.googleBooksApiClient = googleBooksApiClient;
    }

    public Flux<BookDTO> searchBooksByTitleAndLanguage(String title, String language) {
        return googleBooksApiClient.searchBooksByTitleAndLanguage(title, language);
    }
}
