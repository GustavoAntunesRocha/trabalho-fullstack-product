package br.com.trabalhofullstack.product.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public Product create(String name, float price, String description, String photoLocation, Category category) {
		Product product = new Product(name, price, description, photoLocation, category);
		repository.save(product);
		return product;
	}

	public void createFromDto(ProductDto productDto, CategoryDto categoryDto, MultipartFile imageFile) {
		try {
			String imagePath = "./src/main/resources/product-images/" + productDto.getName() + productDto.getId()
					+ ".png";
			File file = new File(imagePath);

			create(productDto.getName(), Float.parseFloat(productDto.getPrice()), productDto.getDescription(),
					imagePath, categoryService.searchById(Integer.parseInt(categoryDto.getId())));

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

	public Iterable<Product> searchAll() {
		return repository.findAll();
	}

	public List<ProductDto> searchAllDto() {
		Iterable<Product> products = searchAll();
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : products) {
			ProductDto productDto = new ProductDto(Integer.toString(product.getId()), product.getName(),
					Float.toString(product.getPrice()), product.getDescripton());

			CategoryDto categoryDto = new CategoryDto(Integer.toString(product.getCategory().getId()), product.getCategory().getName());

			productDto.setCategoriesDto(categoryDto);
			productDtoList.add(productDto);
		}
		return productDtoList;
	}
}
