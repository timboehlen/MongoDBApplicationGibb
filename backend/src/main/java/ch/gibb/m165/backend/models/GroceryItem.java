package ch.gibb.m165.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GroceryItem {

    @Id
    private Integer id;

    private String name;
    private int quantity;
    private String category;

    public GroceryItem(Integer id, String name, int quantity, String category) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }
}
