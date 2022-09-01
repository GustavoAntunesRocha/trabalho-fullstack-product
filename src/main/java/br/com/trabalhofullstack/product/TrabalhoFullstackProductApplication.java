package br.com.trabalhofullstack.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import br.com.trabalhofullstack.product.service.CategoryService;

@SpringBootApplication
public class TrabalhoFullstackProductApplication {
	
	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoFullstackProductApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void categoryInit() {
		categoryService.create("Comida");
		categoryService.create("Bebida");
	}

}
