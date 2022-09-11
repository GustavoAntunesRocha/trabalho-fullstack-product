package br.com.trabalhofullstack.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.trabalhofullstack.product.domain.Category;
import br.com.trabalhofullstack.product.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	List<Product> searchByCategory(Category category);

}
