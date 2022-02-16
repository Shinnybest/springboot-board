package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.Posts;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentsDataDto {
    Long id;
    String author;
    String comment;
    Posts posts;

    public CommentsDataDto(Comments comments) {
        this.id = comments.getId();
        this.comment = comments.getComment();
        this.author = comments.getAuthor();
        this.posts = comments.getPosts();
    }
}
