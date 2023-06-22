package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.dtos.RatingDTO;
import ch.gibb.m165.backend.models.Rating;
import ch.gibb.m165.backend.repositories.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/ratings")
public class RatingController {
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @GetMapping()
    List<Rating> list() {
        return ratingRepository.findAll();
    }

    @GetMapping("/{id}")
    Rating get(@PathVariable String id) {
        return ratingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    Rating newUser(@RequestBody RatingDTO ratingDTO) {
        Rating rating = new Rating(UUID.randomUUID().toString(), ratingDTO.likes(), ratingDTO.dislikes());
        return ratingRepository.save(rating);
    }

    @PutMapping("/{id}")
    Rating updateUser(@PathVariable String id, @RequestBody RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        rating.setLikes(ratingDTO.likes());
        rating.setDislikes(ratingDTO.dislikes());
        return ratingRepository.save(rating);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ratingRepository.delete(rating);
    }
}
