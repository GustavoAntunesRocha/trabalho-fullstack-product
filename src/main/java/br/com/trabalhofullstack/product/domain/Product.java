package br.com.trabalhofullstack.product.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private float price;
	
	private String descripton;
	
	private String photoLocation;
	
	@OneToMany(mappedBy = "products")
	private Set<Category> categories;
	
	public Product() {}

	public Product(@NotBlank String name, @NotNull float price, String descripton, String photoLocation,
			Set<Category> categories) {
		super();
		this.name = name;
		this.price = price;
		this.descripton = descripton;
		this.photoLocation = photoLocation;
		this.categories = categories;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}
