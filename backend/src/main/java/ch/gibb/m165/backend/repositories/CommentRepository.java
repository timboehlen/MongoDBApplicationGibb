package ch.gibb.m165.backend.repositories;

import ch.gibb.m165.backend.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface CommentRepository extends MongoRepository<Comment, String> {
}
