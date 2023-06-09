package nl.bookfinder.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import nl.bookfinder.dto.BookDTO;
import nl.bookfinder.form.BookSearchForm;
import nl.bookfinder.service.BookService;
import nl.bookfinder.util.DateUtils;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/books/search")
public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public String showSearchForm(Model model) {
		model.addAttribute("bookSearchForm", new BookSearchForm());
		model.addAttribute("books", null);
		return "search";
	}

	@PostMapping
	public String searchBooksByTitle(@Valid BookSearchForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
	        return "search";
	    }
		
		String title = form.getTitle();
		String language = form.getLanguage();
		
		Flux<BookDTO> books = bookService.searchBooksByTitleAndLanguage(title, language);

		List<BookDTO> formattedBooks = books
				.map(book -> {
					book.setPublicationDate(DateUtils.formatPublicationDate(book.getPublicationDate()));
					return book;
				}).collectList().block();

		model.addAttribute("books", formattedBooks);

		return "search";
	}
}
