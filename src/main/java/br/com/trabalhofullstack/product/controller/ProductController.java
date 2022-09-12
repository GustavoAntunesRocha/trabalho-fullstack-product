package br.com.trabalhofullstack.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.trabalhofullstack.product.domain.CategoryDto;
import br.com.trabalhofullstack.product.domain.Product;
import br.com.trabalhofullstack.product.domain.ProductDto;
import br.com.trabalhofullstack.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Product> create(@ModelAttribute ProductDto produtctDto, @RequestParam String categoryDto, @RequestParam MultipartFile file[]){
		ObjectMapper mapper = new ObjectMapper();
		CategoryDto categoryDto2;
		try {
			categoryDto2 = mapper.readValue(categoryDto, CategoryDto.class);
			return ResponseEntity.ok(productService.createFromDto(produtctDto, categoryDto2, file[0]));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping(value = "/search/all")
	public ResponseEntity<List<ProductDto>> searchAll() {
		return ResponseEntity.ok(productService.searchAllDto());
	}
	
	@GetMapping(value = "/search/category/{id}")
	public ResponseEntity<List<Product>> searchByCategoryId(@PathVariable int id){
		List<Product> products = productService.searchByCategoryId(id);
		if(products.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(products);
	}
	
	@PutMapping
	public ResponseEntity<Product> edit(@RequestBody ProductDto productDto){
		Optional<Product> product = productService.searchById(Integer.parseInt(productDto.getId()));
		return ResponseEntity.ok(productService.edit(product.get(), productDto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable int id){
		return ResponseEntity.ok(productService.delete(id));
	}

}
