package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.PostDTO;
import com.example.demo.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService service;
	
	@GetMapping("/home")
	public void main() {
		
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes) {
		int no = service.register(dto);
		
		return "redirect:/post/login";
		
	}
	
	@GetMapping("/list")
	public void list(Model model) {
		List<PostDTO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	@GetMapping("/post")
	public void read(@RequestParam(name = "no") int no, Model model) {
		PostDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}
}
