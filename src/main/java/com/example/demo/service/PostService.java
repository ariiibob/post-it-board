package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	
	public List<Post> findAll() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public Post save(Post post) {
	    Post savedPost = postRepository.save(post); // 저장 먼저

	    LocalDateTime threshold = LocalDateTime.now().minusDays(30);
	    postRepository.deletePostsOlderThan(threshold); // 오래된 글 삭제

	    return savedPost;
	}
	
	public Post findById(Long id) {
		return postRepository.findById(id).orElseThrow();
	}
	
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
