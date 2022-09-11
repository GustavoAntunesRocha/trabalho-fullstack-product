package br.com.trabalhofullstack.product.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryDto {

	@NotNull
	@NotEmpty
	private String id;
	
	private String name;
	
	public CategoryDto() {}
	

	public CategoryDto(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
