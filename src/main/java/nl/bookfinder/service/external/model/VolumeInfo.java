package nl.bookfinder.service.external.model;

import java.util.List;

public class VolumeInfo {
	private String title;
	private String[] authors;
	private String publishedDate;
	private List<IndustryIdentifier> industryIdentifiers;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String[] getAuthors() {
		return authors;
	}
	
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public String getPublishedDate() {
		return publishedDate;
	}
	
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<IndustryIdentifier> getIndustryIdentifiers() {
		return industryIdentifiers;
	}

	public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
		this.industryIdentifiers = industryIdentifiers;
	}	
}
