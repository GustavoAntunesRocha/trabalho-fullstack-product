package br.com.trabalhofullstack.product.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public Product create(String name, float price, String description, String photoLocation,
			Set<Category> categories) {
		Product product = new Product(name, price, description, photoLocation, categories);
		repository.save(product);
		return product;
	}

	public void createFromDto(ProductDto productDto, List<CategoryDto> categoryDtos, MultipartFile imageFile) {
		try {
			String imagePath = "./src/main/resources/product-images/" + productDto.getName() + productDto.getId() + ".png";
			File file = new File(imagePath);
			Set<Category> categories = new HashSet<>();
			for (CategoryDto category : categoryDtos) {
				categories.add(categoryService.searchById(Integer.parseInt(category.getId())));
			}
			Product product = create(productDto.getName(), Float.parseFloat(productDto.getPrice()),
					productDto.getDescription(), imagePath, categories);
			for (Category category : categories) {
				categoryService.addProduct(product, category);
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(imageFile.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delete(int productId) {
		repository.deleteById(productId);
		return true;
	}

	public Optional<Product> searchById(int productId) {
		return repository.findById(productId);
	}
}
