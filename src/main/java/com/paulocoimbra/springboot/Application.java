package com.paulocoimbra.springboot;

import com.paulocoimbra.springboot.domain.Category;
import com.paulocoimbra.springboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(1, "Informática");
		Category cat2 = new Category(2, "Escritório");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));

	}
}
