package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.Posts;
import lombok.Getter;

import java.util.List;

@Getter
public class PageDataDto {
    String title;
    String author;
    String contents;
    List<Comments> comments;

    public PageDataDto(Posts posts) {
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.contents = posts.getContents();
        this.comments = posts.getComments();
    }
}
