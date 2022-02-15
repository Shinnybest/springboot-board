package com.sparta.miniblog.domain;

import com.sparta.miniblog.models.PostsRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByPostsId(Long id);

}
