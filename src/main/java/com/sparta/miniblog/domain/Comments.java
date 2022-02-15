package com.sparta.miniblog.domain;

import com.sparta.miniblog.models.CommentsRequestDto;
import com.sparta.miniblog.models.PostsRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

//@Builder(builderMethodName = "CommentsBuilder")
@Getter
//@Setter
@NoArgsConstructor
@Entity
public class Comments extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String comment;

//    @Column(nullable = false)
//    private Long post_id;
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




//    public void setPosts(Posts posts) {
//        this.posts = posts;
//    }


}
