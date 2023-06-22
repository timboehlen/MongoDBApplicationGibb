package ch.gibb.m165.backend.repositories;

import ch.gibb.m165.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
