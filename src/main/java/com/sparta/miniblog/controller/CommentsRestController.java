package com.sparta.miniblog.controller;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.CommentsRepository;
import com.sparta.miniblog.models.CommentsRequestDto;
import com.sparta.miniblog.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentsRestController {
    private final CommentsRepository commentsRepository;
    private final CommentsService commentsService;

    @PostMapping("/api/comments/{postsId}")
    public void createComments(@PathVariable Long postsId, @RequestBody CommentsRequestDto requestDto) {
        Comments comments = commentsService.getComments(postsId, requestDto);
        commentsRepository.save(comments);
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
}
