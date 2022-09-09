package br.com.trabalhofullstack.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabalhofullstack.product.domain.Category;
import br.com.trabalhofullstack.product.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repository;
	
	public Category create(String name) {
		Category category = new Category(name);
		repository.save(category);
		return category;
	}
	
	public boolean save(Category category) {
		repository.save(category);
		return true;
	}
	
	public boolean delete(int categoryId) {
		repository.deleteById(categoryId);
		return true;
	}
	
	public Category searchById(int id) {
		return repository.findById(id);
	}
	
	public List<Category> searchAll() {
		return repository.findAll();
	}
	
}
