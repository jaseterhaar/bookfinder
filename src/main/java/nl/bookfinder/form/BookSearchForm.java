package nl.bookfinder.form;

import jakarta.validation.constraints.NotBlank;

public class BookSearchForm {
	@NotBlank(message = "Titel is verplicht")
	private String title;
	private String language;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
