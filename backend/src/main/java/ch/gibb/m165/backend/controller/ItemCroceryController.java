package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.models.GroceryItem;
import ch.gibb.m165.backend.repositories.ItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCroceryController {
    private final ItemRepository itemRepository;

    public ItemCroceryController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @GetMapping("/items")
    List<GroceryItem> all() {
        return itemRepository.findAll();
    }
}
