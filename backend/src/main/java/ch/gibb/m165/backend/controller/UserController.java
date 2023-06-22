package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.dtos.UserDTO;
import ch.gibb.m165.backend.models.Comment;
import ch.gibb.m165.backend.models.User;
import ch.gibb.m165.backend.repositories.CommentRepository;
import ch.gibb.m165.backend.repositories.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public UserController(UserRepository userRepository,
                          CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping()
    List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User get(@PathVariable String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    User newUser(@RequestBody UserDTO userDTO) {
        List<Comment> comments = getItemsFromIds(commentRepository, userDTO.comments());
        User user = new User(UUID.randomUUID().toString(), userDTO.name(), userDTO.email(), comments);
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Comment> comments = getItemsFromIds(commentRepository, userDTO.comments());
        user.setName(userDTO.name());
        user.setComments(comments);
        user.setEmail(userDTO.email());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

    static <T> List<T> getItemsFromIds(MongoRepository<T, String> repo, List<String> ids) {
        return ids.stream()
                .map(itemID -> repo.findById(itemID).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }
}
