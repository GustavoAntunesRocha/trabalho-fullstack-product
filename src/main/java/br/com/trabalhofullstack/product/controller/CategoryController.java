package br.com.trabalhofullstack.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trabalhofullstack.product.domain.Category;
import br.com.trabalhofullstack.product.domain.CategoryDto;
import br.com.trabalhofullstack.product.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> searchAll(){
		return ResponseEntity.ok(categoryService.searchAll());
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto){
		return ResponseEntity.ok(categoryService.create(categoryDto.getName()));
	}
	
	@DeleteMapping
	public ResponseEntity<Category> delete(@RequestBody CategoryDto categoryDto){
		categoryService.delete(Integer.parseInt(categoryDto.getId()));
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	public ResponseEntity<Category> edit(@RequestBody CategoryDto categoryDto){
		Category category = categoryService.searchById(Integer.parseInt(categoryDto.getId()));
		category.setName(categoryDto.getName());
		categoryService.save(category);
		return ResponseEntity.ok().build();
	}
}
