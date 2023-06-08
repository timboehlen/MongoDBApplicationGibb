package ch.gibb.m165.backend.repositories;

import ch.gibb.m165.backend.models.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<GroceryItem, String> {

}
