package com.sparta.miniblog.controller;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.CommentsRepository;
import com.sparta.miniblog.domain.Posts;
import com.sparta.miniblog.domain.PostsRepository;
import com.sparta.miniblog.models.CommentsRequestDto;
import com.sparta.miniblog.service.CommentsService;
import com.sparta.miniblog.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class CommentsRestController {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;

//    @GetMapping("/api/{postsId}/comments")
//    public List<Comments> getComments(@PathVariable Long postsId) {
//        return commentsRepository.findByPostsId(postsId);
//    }

    @PostMapping("/api/comments/{postsId}")
    public Comments createComments(@PathVariable Long postsId, @RequestBody CommentsRequestDto requestDto) {
        Comments comments = commentsService.getComments(postsId, requestDto);
        commentsRepository.save(comments);
        return comments;
    }

    @PutMapping("/api/comments/{id}")
    public Long updateComments(@PathVariable Long id, @RequestBody CommentsRequestDto requestDto) {
        return commentsService.update(id, requestDto);
    }


    @DeleteMapping("/api/comments/{id}")
    public Long deleteComments(@PathVariable Long id) {
        commentsRepository.deleteById(id);
        return id;
    }




//    @GetMapping("/api/posts/{id}")
//    public Posts getPost(@PathVariable Long id) {
//        Optional<Posts> optional = postsRepository.findById(id);
//        return optional.get();
//    }
//
//    @GetMapping("/api/{post_id}/comments")
//    public List<Comments> getComments(@PathVariable Long post_id) {
//        Optional<Comments> optional = commentsRepository.findById(post_id);
//        return optional.get();
//    }
//
//    @PostMapping("/api/{post_id}/comments/{id}")
//    public Comments createComments(@RequestBody CommentsRequestDto requestDto) {
//        Comments comments = new Comments(requestDto);
//        comments.setPosts();
//        commentsRepository.save(comments);
//        return comments;
//
//    }
//    @GetMapping("/api/comments/{page_id}")
//    public List<Comments> getComments() { return commentsRepository.find }
//    여기서 그 commentsRepository에서 page id의 값을 가진 것들을 다 가져온다.





}
