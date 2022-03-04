package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    Long id;
    String author;
    String comment;

    public CommentsResponseDto(Comments comments) {
        this.id = comments.getId();
        this.comment = comments.getComment();
        this.author = comments.getAuthor();
    }
}
