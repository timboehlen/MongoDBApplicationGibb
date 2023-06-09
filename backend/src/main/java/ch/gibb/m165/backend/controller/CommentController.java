package ch.gibb.m165.backend.controller;

import ch.gibb.m165.backend.dtos.CommentDTO;
import ch.gibb.m165.backend.models.Comment;
import ch.gibb.m165.backend.models.Rating;
import ch.gibb.m165.backend.repositories.CommentRepository;
import ch.gibb.m165.backend.repositories.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    private final RatingRepository ratingRepository;

    public CommentController(CommentRepository commentRepository,
                             RatingRepository ratingRepository) {
        this.commentRepository = commentRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping()
    List<Comment> list() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    Comment get(@PathVariable String id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    Comment newUser(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment(UUID.randomUUID().toString(), commentDTO.content(), new Date().getTime(), new Rating(UUID.randomUUID().toString()));
        ratingRepository.save(comment.getRating());
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    Comment updateUser(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        comment.setContent(commentDTO.content());
        return commentRepository.save(comment);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        commentRepository.delete(comment);
    }
}
