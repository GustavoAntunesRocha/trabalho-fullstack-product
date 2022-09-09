package br.com.trabalhofullstack.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.trabalhofullstack.product.domain.CategoryDto;
import br.com.trabalhofullstack.product.domain.ProductDto;
import br.com.trabalhofullstack.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PutMapping(value = "/create")
	public void create(@ModelAttribute ProductDto produtctDto, @RequestParam String categoryDto, @RequestParam MultipartFile file[]){
		ObjectMapper mapper = new ObjectMapper();
		CategoryDto categoryDto2;
		try {
			categoryDto2 = mapper.readValue(categoryDto, CategoryDto.class);
			productService.createFromDto(produtctDto, categoryDto2, file[0]);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GetMapping(value = "/search/all")
	public List<ProductDto> searchAll() {
		return productService.searchAllDto();
	}

}
