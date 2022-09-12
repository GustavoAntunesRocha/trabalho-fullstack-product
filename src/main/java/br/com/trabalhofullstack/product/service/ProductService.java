package br.com.trabalhofullstack.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trabalhofullstack.product.domain.Category;
import br.com.trabalhofullstack.product.domain.CategoryDto;
import br.com.trabalhofullstack.product.domain.Product;
import br.com.trabalhofullstack.product.domain.ProductDto;
import br.com.trabalhofullstack.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryService categoryService;

	public Product create(String name, float price, String description, String photoLocation, Category category) {
		if (category == null) {
			return null;
		}
		Product product = new Product(name, price, description, photoLocation, category);
		repository.save(product);
		return product;
	}

	public Product createFromDto(ProductDto productDto) {
		Category category = categoryService.searchById(Integer.parseInt(productDto.getCategoriesDto().getId()));
		if (category == null) {
			return null;
		}
		Product product = create(productDto.getName(), Float.parseFloat(productDto.getPrice()),
				productDto.getDescription(), productDto.getPhotoLocation(), category);

		return product;

	}

	public Product edit(Product product, ProductDto productDto) {
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(Float.parseFloat(productDto.getPrice()));
		product.setPhotoLocation(productDto.getPhotoLocation());
		product.setCategories(categoryService.searchById(Integer.parseInt(productDto.getCategoriesDto().getId())));
		repository.save(product);
		return product;
	}

	public boolean delete(int productId) {
		repository.deleteById(productId);
		return true;
	}

	public Optional<Product> searchById(int productId) {
		return repository.findById(productId);
	}

	public Iterable<Product> searchAll() {
		return repository.findAll();
	}

	public List<ProductDto> searchAllDto() {
		Iterable<Product> products = searchAll();
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = new ProductDto(Integer.toString(product.getId()), product.getName(),
					Float.toString(product.getPrice()), product.getDescription());

			CategoryDto categoryDto = new CategoryDto(Integer.toString(product.getCategory().getId()),
					product.getCategory().getName());
			productDto.setPhotoLocation(product.getPhotoLocation());
			productDto.setCategoriesDto(categoryDto);
			productDtoList.add(productDto);
		}
		return productDtoList;
	}

	public List<Product> searchByCategoryId(int categoryId) {
		Category category = categoryService.searchById(categoryId);
		return repository.searchByCategory(category);
	}

	public Product fromDto(ProductDto productDto) {
		return new Product(productDto.getName(), Float.parseFloat(productDto.getPrice()), productDto.getDescription(),
				productDto.getPhotoLocation(),
				categoryService.searchById(Integer.parseInt(productDto.getCategoriesDto().getId())));
	}
}
