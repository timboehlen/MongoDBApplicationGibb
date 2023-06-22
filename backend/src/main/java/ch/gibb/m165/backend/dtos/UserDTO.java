package ch.gibb.m165.backend.dtos;

import ch.gibb.m165.backend.models.Comment;

import java.util.List;

public record UserDTO(String name, String email, List<Comment> comments) {
}
