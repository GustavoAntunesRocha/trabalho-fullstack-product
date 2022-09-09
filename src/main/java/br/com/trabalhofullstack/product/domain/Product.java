package br.com.trabalhofullstack.product.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Product() {}

	public Product(@NotBlank String name, @NotNull float price, String descripton, String photoLocation,
			Category category) {
		super();
		this.name = name;
		this.price = price;
		this.descripton = descripton;
		this.photoLocation = photoLocation;
		this.category = category;
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

	public Category getCategory() {
		return category;
	}

	public void setCategories(Category category) {
		this.category = category;
	}

}
