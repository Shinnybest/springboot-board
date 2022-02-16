package com.sparta.miniblog.controller;

import com.sparta.miniblog.domain.Posts;
import com.sparta.miniblog.domain.PostsRepository;
import com.sparta.miniblog.models.PostsDataDto;
import com.sparta.miniblog.models.PostsRequestDto;
import com.sparta.miniblog.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostsRestController {

    private final PostsRepository postsRepository;
    private final PostsService postsService;

    @GetMapping("/api/posts")
    public List<Posts> getPosts() { return postsRepository.findAll(); }

    @PostMapping("/api/posts")
    public Posts createPosts(@RequestBody PostsRequestDto requestDto) {
        Posts posts = new Posts(requestDto);
        postsRepository.save(posts);
        return posts;
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePosts(@PathVariable Long id) {
        postsRepository.deleteById(id);
        return id;
    }

//    @GetMapping("/api/posts/{id}")
//    public PostsDataDto getComments(@PathVariable Long id) {
//        Posts posts = postsRepository.findById(id).orElseThrow(NullPointerException::new);
//        PostsDataDto dataDto = new PostsDataDto(posts);
//        return dataDto;
//    }

    @GetMapping("/api/posts/{id}")
    public Optional<Posts> getPostandComments(@PathVariable Long id) {
        return postsService.getPostandComments(id);
    }

}
