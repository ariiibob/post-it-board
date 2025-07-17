package com.example.demo.domain;
import java.time.LocalDateTime;
import java.util.Random;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String content;
		private String author;
		
		@Lob
		private byte[] image;
		private String imageType;
		
		private String color;
		private String borderColor; // 테두리색
		
	    public void assignRandomColor() {
	        if (this.color == null || this.color.isEmpty()) {
	            Random random = new Random();
	            int r = 200 + random.nextInt(56); // 200~255
	            int g = 200 + random.nextInt(56);
	            int b = 200 + random.nextInt(56);
	            this.color = String.format("#%02x%02x%02x", r, g, b);
	            
	            // ✅ 테두리 색: RGB를 각각 0.85배 어둡게
	            r = (int)(r * 0.85);
	            g = (int)(g * 0.85);
	            b = (int)(b * 0.85);
	            this.borderColor = String.format("#%02x%02x%02x", r, g, b);
	        }
	    }
	    
	    @CreationTimestamp // Hibernate가 자동으로 생성 시간 기록
	    private LocalDateTime createdAt;

}
