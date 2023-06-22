package ch.gibb.m165.backend.dtos;

import java.util.List;

public record UserDTO(String name, String email, List<String> comments) {
}
