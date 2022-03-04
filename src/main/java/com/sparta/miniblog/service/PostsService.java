package com.sparta.miniblog.service;

import com.sparta.miniblog.domain.Comments;
import com.sparta.miniblog.domain.CommentsRepository;
import com.sparta.miniblog.domain.Posts;
import com.sparta.miniblog.domain.PostsRepository;
import com.sparta.miniblog.models.CommentsResponseDto;
import com.sparta.miniblog.models.PostResponseDto;
import com.sparta.miniblog.models.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        posts.update(requestDto);
        return id;
    }

//    public Optional<Posts> getPostandComments(Long id) {
//        return postsRepository.findById(id);
//    }

    public PostResponseDto getPostandComments(Long id) {
        Optional<Posts> findPost = Optional.ofNullable(postsRepository.findById(id)).orElseThrow(NullPointerException::new);
        List<CommentsResponseDto> commentsResponseDtoList = new ArrayList<>();
        List<Comments> commentList = commentsRepository.findByPosts_Id(id);
        for (Comments comment : commentList) {
            CommentsResponseDto commentsResponseDto = new CommentsResponseDto(comment);
            commentsResponseDtoList.add(commentsResponseDto);
        }
        PostResponseDto postResponseDto = new PostResponseDto(findPost.get(), commentsResponseDtoList);
        return postResponseDto;
    }

    public Posts findPostbyIdx(Long idx) {
        Posts posts = postsRepository.findById(idx).orElseThrow(
                () -> new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        return posts;
    }
}
