package com.example.demo.controller;

import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Post;
import com.example.demo.service.PostService;

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
    public String create(@ModelAttribute Post post,
            @RequestParam("imageFile") MultipartFile imageFile) {
    	if (!imageFile.isEmpty()) {
    		try {
    			post.setImage(imageFile.getBytes());
    			post.setImageType(imageFile.getContentType());
    		} catch (IOException e) {
    			e.printStackTrace(); // 실제 운영에서는 로깅 처리
    		}
    	}
    	
        // ✅ 새 글이면 색상 부여
        post.assignRandomColor();
    	
    	postService.save(post);
    	return "redirect:/posts";
    	
    }
    
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> postList = postService.findAll(); // PostService에서 글 목록 조회
//        
//        // 각 post에 랜덤 색상 부여
//        for (Post post : postList) {
//            post.assignRandomColor(); // Post 객체에 정의한 메서드 (아래에 따로 설명)
//        }
//        
        model.addAttribute("postList", postList);
        return "list"; // templates/list.html 로 렌더링
    }
    
    @GetMapping("/posts/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
    	Post post = postService.findById(id);
    	if (post.getImage() != null) {
    		return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(post.getImageType()))
                    .body(post.getImage());
    	} else {
    		return ResponseEntity.notFound().build();
    	} 
    }
    
    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }


}
