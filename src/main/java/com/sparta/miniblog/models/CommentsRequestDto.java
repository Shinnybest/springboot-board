package com.sparta.miniblog.models;

import lombok.Getter;

@Getter
public class CommentsRequestDto {
    private String author;
    private String comment;
}
