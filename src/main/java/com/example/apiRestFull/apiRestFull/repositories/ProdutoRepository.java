package com.example.apiRestFull.apiRestFull.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiRestFull.apiRestFull.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel,Long>{

}
