package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tbl_music")
public class Music {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;
	
	@Column(length = 50, nullable = false)
	String title;
	
	@Column(length = 50, nullable = false)
	String artist;
	
	@Column(length = 20, nullable = false)
	String genre;
	//장르
	//엔티티를 넣고 싶은데 좀 무리
		
//	@Column(columnDefinition = "TEXT")
//	String lyrics;
//	//가사
	
	@Column(length = 50)
	String albumName;
	
	@Column(columnDefinition = "TEXT")
	String content;
	
    // 양방향 매핑을 원할 경우
//    @OneToMany(mappedBy = "music")
//    List<Post> posts = new ArrayList<>();
	
}
