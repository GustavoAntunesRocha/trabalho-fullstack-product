package br.com.trabalhofullstack.product.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.trabalhofullstack.product.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
