package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import com.example.demo.dto.PostDTO;
import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository repository;

	//등록
	@Override
	public int register(PostDTO dto) {
		
		Post entity = dtoToEntity(dto);
		repository.save(entity);
		int newNo = entity.getNo();
		
		return newNo;
	}

	@Override
	public List<PostDTO> getList() {
		List<Post> result = repository.findAll();
		List<PostDTO> list = new ArrayList<>();
		list = result.stream()
				.map(entity -> entityToDto(entity))
				.collect(Collectors.toList());
		return list;
	}
	
	
//	@Override
//	public Page<PostDTO> getList(int pageNumber) {
//		
//		int pageNum = (pageNumber == 0) ? 0 : pageNumber -1;
//		Pageable pageable = PageRequest.of(pageNum, 12, Sort.by("no").descending());
//	
//		Page<Post> entityPage = repository.findAll(pageable);
//		
//		Page<PostDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
//		return dtoPage;
//	}
//	

	@Override
	public PostDTO read(int no) {
		
		Optional<Post> result = repository.findById(no);
		
		if(result.isPresent() ) {
			Post post = result.get();
			PostDTO boardDTO = entityToDto(post);
			return boardDTO;
		} else {
		return null;
	}
	}

	@Override
	public void modify(PostDTO dto) {
		
		Optional<Post> result = repository.findById(dto.getNo());
		if(result.isPresent()) {
			Post entity = result.get();
			
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
		//	entity.setMusic_api_id(dto.getMusic_api_id());
			
			repository.save(entity);
		}
		
	}

	@Override
	public void remove(int no) {
		Optional<Post> result = repository.findById(no);
		if(result.isPresent()) {
			repository.deleteById(no);
		}
		
	}
	
}
