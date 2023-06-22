package ch.gibb.m165.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Rating {
    @Id
    @MongoId
    private String id;
    private int likes;
    private int dislikes;
    public Rating() {
        this.likes = 0;
        this.dislikes = 0;
    }

    public Rating(String id) {
        this.id = id;
        this.likes = 0;
        this.dislikes = 0;
    }
    public Rating(String id, int likes, int dislikes) {
        this.id = id;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
