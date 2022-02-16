package com.sparta.miniblog.service;

import com.sparta.miniblog.domain.Posts;
import com.sparta.miniblog.domain.PostsRepository;
import com.sparta.miniblog.models.PostsDataDto;
import com.sparta.miniblog.models.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        posts.update(requestDto);
        return id;
    }

    public Optional<Posts> getPostandComments(Long id) {
        return postsRepository.findById(id);
    }



}
