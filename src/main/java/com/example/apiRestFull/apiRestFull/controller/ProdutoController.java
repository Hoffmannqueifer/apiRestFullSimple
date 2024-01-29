package com.example.apiRestFull.apiRestFull.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiRestFull.apiRestFull.model.ProdutoModel;
import com.example.apiRestFull.apiRestFull.repositories.ProdutoRepository;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@GetMapping("/produtos")
	public ResponseEntity<List<ProdutoModel>> getAllProdutos(){
		List<ProdutoModel> produtosList = produtoRepository.findAll();
		if(produtosList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			for(ProdutoModel produto : produtosList) {
				long id = produto.getIdProduto();
				produto.add(linkTo(methodOn(ProdutoController.class).getOneProduto(id)).withSelfRel());
			}
		}
		return new ResponseEntity<List<ProdutoModel>>(produtosList, HttpStatus.OK);
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<ProdutoModel> getOneProduto(@PathVariable(value="id") long id){
		Optional<ProdutoModel> produtoById = produtoRepository.findById(id);
		if(!produtoById.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			produtoById.get().add(linkTo(methodOn(ProdutoController.class).getAllProdutos()).withRel("Lista de Produtos"));
			return new ResponseEntity<ProdutoModel>(produtoById.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/produtos")
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoModel produtoModel){
		return new ResponseEntity<ProdutoModel>(produtoRepository.save(produtoModel), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<ProdutoModel> deleteProduto(@PathVariable(value="id") long id){
		Optional<ProdutoModel> produtoById = produtoRepository.findById(id);
		if(!produtoById.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			produtoRepository.delete(produtoById.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}
