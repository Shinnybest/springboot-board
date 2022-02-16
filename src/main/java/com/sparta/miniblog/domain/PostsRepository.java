package com.sparta.miniblog.domain;

import com.sparta.miniblog.models.PostsDataDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findById(Long postId);
}
