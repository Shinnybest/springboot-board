package com.sparta.miniblog.service;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.CommentsRepository;
import com.sparta.miniblog.domain.Posts;
import com.sparta.miniblog.domain.PostsRepository;
import com.sparta.miniblog.models.CommentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Comments getComments(Long postId, CommentsRequestDto requestDto) {
        Optional<Posts> byId = Optional.ofNullable(postsRepository.findById(postId))
                .orElseThrow(NullPointerException::new);
        Comments comments = new Comments(requestDto);
        byId.get().addComments(comments);
        return comments;
    }

    @Transactional
    public Long update(Long id, CommentsRequestDto requestDto) {
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 댓글이 존재하지 않습니다."));
        comments.update(requestDto);
        return id;
    }
}
