package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.Posts;

import java.util.List;

public class PostsDataDto {
    Long id;
    String title;
    String author;
    String contents;
    List<Comments> comments;

    public PostsDataDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.contents = posts.getContents();
        this.comments = posts.getComments();
    }
}
