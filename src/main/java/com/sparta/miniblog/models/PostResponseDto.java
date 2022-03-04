package com.sparta.miniblog.models;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.Posts;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    Long id;
    String title;
    String author;
    String contents;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    List<CommentsResponseDto> comments;


    public PostResponseDto(Posts posts, List<CommentsResponseDto> commentsResponseDtoList) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.author = posts.getAuthor();
        this.contents = posts.getContents();
        this.createdAt = posts.getCreatedAt();
        this.modifiedAt = posts.getModifiedAt();
        this.comments = commentsResponseDtoList;
    }
}
