package com.example.demo.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.PostDTO;
import com.example.demo.service.PostService;

@SpringBootTest
public class PostServiceTest1 {

	
	@Autowired
	PostService service;
	
	@Test
	public void 게시물등록() {
		PostDTO dto = PostDTO.builder()
				.title("wndhrrkxdms skf")
				.content("dk ahffk")
				.writer("aa")
				.build();
		int no = service.register(dto);
		System.out.println("새로운 게시물 번호 : " + no);
	}
	
	@Test
	public void 게시물목록조회() {
		List<PostDTO> list = service.getList();
		for(PostDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 게시물수정() {
		PostDTO dto = service.read(2);
		dto.setContent("hell");
		dto.setTitle("sun");
		service.modify(dto);
	}
	
	@Test 
	public void 게시물삭제() {
		service.remove(4);
	}
}
