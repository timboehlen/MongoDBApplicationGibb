package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.models.User;
import ch.gibb.m165.backend.repositories.UserRepository;
import ch.gibb.m165.backend.dtos.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        User user = new User(UUID.randomUUID().toString(), userDTO.name(), userDTO.email(), userDTO.comments());
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    User updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setName(userDTO.name());
        user.setComments(userDTO.comments());
        user.setEmail(userDTO.email());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
