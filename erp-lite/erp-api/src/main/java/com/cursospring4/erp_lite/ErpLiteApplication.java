package com.cursospring4.erp_lite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents.CatalogDocument;
import com.cursospring4.erp_lite.infrastructure.persistence.mongo.repositories.CatalogRepository;


@SpringBootApplication
public class ErpLiteApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ErpLiteApplication.class, args);
	}

	@Autowired
	private CatalogRepository catalogRepository;

	@Override
	public void run(String... args) throws Exception {
		catalogRepository.findAll().stream()
				.map(CatalogDocument::getName)
				.forEach(System.out::println);
	}
}
