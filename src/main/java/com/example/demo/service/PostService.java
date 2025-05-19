package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;


import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;


public interface PostService {

	//게시물 등록
	int register(PostDTO dto);
	
	//목록조회1
	List<PostDTO> getList();
	
	//목록조회2
	//Page<PostDTO> getList(int pageNumber);
	
	//상세조회
	PostDTO read(int no);
	
	//수정
	void modify(PostDTO dto);
	
	//삭제
	void remove(int no);
	
	default Post dtoToEntity(PostDTO dto) {
		Post entity = Post.builder()
				.no(dto.getNo())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return entity;
				
	}
	
	default PostDTO entityToDto(Post entity) {

		PostDTO dto = PostDTO.builder()
				.no(entity.getNo())
				.title(entity.getTitle())
				.content(entity.getContent())
				.regDate(entity.getRegDate()) 
				.writer(entity.getWriter())
				.build();

		return dto;
	
}
}
