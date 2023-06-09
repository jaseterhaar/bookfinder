package nl.bookfinder.service.external.model;

import java.util.List;

public class GoogleBookResponse {
    private List<GoogleBookItem> items;

    public List<GoogleBookItem> getItems() {
        return items;
    }

    public void setItems(List<GoogleBookItem> items) {
        this.items = items;
    }
}