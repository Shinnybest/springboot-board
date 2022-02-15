package com.sparta.miniblog.models;

import lombok.Getter;

@Getter
public class PostsRequestDto {
    private String title;
    private String author;
    private String contents;
}
