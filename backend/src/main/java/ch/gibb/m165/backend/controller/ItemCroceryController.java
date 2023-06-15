package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.models.GroceryItem;
import ch.gibb.m165.backend.repositories.ItemRepository;
import dtos.GroceryItemDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController()
@RequestMapping("/items")
public class ItemCroceryController {
    private final ItemRepository itemRepository;

    public ItemCroceryController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @GetMapping()
    List<GroceryItem> all() {
        return itemRepository.findAll();
    }
    @PostMapping()
    GroceryItem newItem(@RequestBody GroceryItemDTO item) {
        GroceryItem groceryItem = new GroceryItem(UUID.randomUUID().toString(), item.name(), item.quantity(), item.category());
        return itemRepository.save(groceryItem);
    }
}
