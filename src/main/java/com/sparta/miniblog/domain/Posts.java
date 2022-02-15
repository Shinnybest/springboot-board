package com.sparta.miniblog.domain;


import com.sparta.miniblog.models.PostsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends Timestamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.ALL}, orphanRemoval=true)
    private List<Comments> comments = new ArrayList<Comments>();
//    private List<String> comments = new ArrayList<String>();

//    public void setComments(comments){
//        this.comments = List<Comments> comments;
//    }

    public Posts(PostsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
    }

    public void update(PostsRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
    }

    public void addComments(Comments comments) {
        comments.setPosts(this);
    }

//    public List<Comments> addthisComment(Comments comments) {
//        this.comments =
//    }


}
