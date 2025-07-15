package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping
	public List<Post> getAll() {
		return postService.findAll();
	}
	
    // POST: 등록 처리
    @PostMapping
    public String create(Post post) {
        postService.save(post);
        return "redirect:/posts"; // 저장 후 목록 페이지로 이동
    }
	
	@GetMapping("/{id}")
	public Post getOne(@PathVariable Long id) {
		return postService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		postService.delete(id);
	}

}
