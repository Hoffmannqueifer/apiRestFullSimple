package com.example.apiRestFull.apiRestFull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.apiRestFull.apiRestFull.repositories.ProdutoRepository;

@SpringBootApplication
public class ApiRestFullApplication {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiRestFullApplication.class, args);
	}
}
