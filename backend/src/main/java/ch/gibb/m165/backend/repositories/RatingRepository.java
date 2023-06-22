package ch.gibb.m165.backend.repositories;

import ch.gibb.m165.backend.models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RatingRepository extends MongoRepository<Rating, String> {
}
