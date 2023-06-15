package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.models.GroceryItem;
import ch.gibb.m165.backend.repositories.ItemRepository;
import dtos.GroceryItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/items")
public class ItemGroceryController {
    private final ItemRepository itemRepository;

    public ItemGroceryController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping()
    List<GroceryItem> list() {
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    GroceryItem get(@PathVariable String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    GroceryItem newItem(@RequestBody GroceryItemDTO item) {
        GroceryItem groceryItem = new GroceryItem(UUID.randomUUID().toString(), item.name(), item.quantity(), item.category());
        return itemRepository.save(groceryItem);
    }

    @PutMapping("/{id}")
    GroceryItem updateItem(@PathVariable String id, @RequestBody GroceryItemDTO item) {
        GroceryItem groceryItem = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        groceryItem.setName(item.name());
        groceryItem.setCategory(item.category());
        groceryItem.setQuantity(item.quantity());
        return itemRepository.save(groceryItem);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        GroceryItem groceryItem = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        itemRepository.delete(groceryItem);
    }
}
