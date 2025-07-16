package com.example.demo.service;

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
		return postRepository.save(post);
	}
	
	public Post findById(Long id) {
		return postRepository.findById(id).orElseThrow();
	}
	
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
}
