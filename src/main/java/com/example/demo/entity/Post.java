package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tbl_post")
//게시글
public class Post extends Cdate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no;
	
	@Column(length = 50, nullable = false)
	String title;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String content;
	
	@Column(length = 50, nullable = false)
	String writer;
	
	String music_api_id;
	//이거는 어케 해야 할지를 모르겠네
	
	
//	@CreatedDate
//	LocalDateTime createdAt;
	//필드 상속
	
	
	
}
