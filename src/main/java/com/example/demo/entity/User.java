package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Builder
@Entity
@Table(name = "tbl_users")
public class User {
	
	@Id
	@Column(length = 20, nullable = false, unique = true)
	String id;
	
	@Column(length = 20, nullable = false, unique = true)
	String name;
	
	@Column(length = 100, nullable = false)
	String password;
	
	@Column(length = 20, nullable = false)
	String role;
	
}
