package com.sparta.miniblog.domain;

import com.sparta.miniblog.models.CommentsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", nullable = false)
    private Posts posts;

    public void setPosts(Posts posts){
        this.posts = posts;
    }

    public Comments(CommentsRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.comment = requestDto.getComment();
    }

    public void update(CommentsRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.comment = requestDto.getComment();
    }

}
