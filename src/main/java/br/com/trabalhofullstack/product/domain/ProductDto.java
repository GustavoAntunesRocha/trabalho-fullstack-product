package br.com.trabalhofullstack.product.domain;

public class ProductDto {

	
	private String id;
	
	private String name;
	
	private String price;
	
	private String description;
	
	private String photoLocation;
	
	private CategoryDto categoryDto; 

	public ProductDto(String id, String name, String price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripton) {
		this.description = descripton;
	}

	public String getPhotoLocation() {
		return photoLocation;
	}

	public void setPhotoLocation(String photoLocation) {
		this.photoLocation = photoLocation;
	}

	public CategoryDto getCategoriesDto() {
		return this.categoryDto;
	}

	public void setCategoriesDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

}
