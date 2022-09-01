package br.com.trabalhofullstack.product.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotEmpty
	private String name;
	
	@ManyToMany
	@JoinTable(name="product_category",
	 joinColumns=@JoinColumn(name="categoryId"),
	 inverseJoinColumns=@JoinColumn(name="productId"))
	private Set<Product> products;
	
	public Category() {}

	public Category(@NotEmpty String name, Set<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
