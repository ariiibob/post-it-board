package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Post;

import jakarta.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByIdDesc(); // id 기준 내림차순
    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.createdAt < :threshold")
    void deletePostsOlderThan(@Param("threshold") LocalDateTime threshold);
}
