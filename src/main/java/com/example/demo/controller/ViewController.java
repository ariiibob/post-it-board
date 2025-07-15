package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {
	
	private final PostService postService;
	
	@GetMapping("/form")
	public String postForm() {
		return "postForm";
	}
	
    @PostMapping("/form")
    public String create(Post post) {
        postService.save(post);
        return "redirect:/form"; // 성공 후 다른 페이지로 이동 가능
    }
    
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> postList = postService.findAll(); // PostService에서 글 목록 조회
        model.addAttribute("postList", postList);
        return "list"; // templates/list.html 로 렌더링
    }

}
