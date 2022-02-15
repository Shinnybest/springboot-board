package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Posts;
import lombok.Getter;

@Getter
public class CommentsRequestDto {
    private String author;
    private String comment;
}
