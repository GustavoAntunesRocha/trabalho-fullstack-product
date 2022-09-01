package br.com.trabalhofullstack.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.trabalhofullstack.product.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

	List<Category> findAll();
	
	Category findById(int id);
}
