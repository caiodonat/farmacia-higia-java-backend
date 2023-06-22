package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farmaciahigia.model.Product;
import com.farmaciahigia.repository.ProductRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductController {

	private final ProductRepository repository;
	Map<String, Object> res = new HashMap<String, Object>();

	ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	@Operation(summary = "Create a new Product", tags = { "Product" })
	@PostMapping("/")
	String create() {

		Product product = new Product(
				"drug",
				"dipirona",
				"123123123123",
				8.54,
				11.0d);

		repository.save(product);
		return product.toString();
	}

	@Operation(summary = "Get all Products", tags = { "Product" })
	@GetMapping("/all")
	ResponseEntity<?> getAll() {
		try {

			List<Product> repo = repository.findAll();

			return ResponseEntity
				.status(200)
				.body(Map.of(
					"message", "Produto encontrado com sucesso!",
					"content", repo
				));

		} catch (Exception e) {
			return ResponseEntity
				.status(500)
				.body(Map.of(
					"message", "Falha ao processar sua requisição",
					"content", null
				));
		}
	}

	@Operation(summary = "Get a Product", tags = { "Product" })
	@GetMapping("/{id}") // 54
	ResponseEntity<?> getById(@PathVariable String id) {
		try {
			Product productRes = repository.findById(Integer.parseInt(id));

			if (productRes == null) {
				res.put("message", "Produto não encontrado");
				res.put("content", null);
				return ResponseEntity
						.status(400)
						.body(res);
			}

			res.put("message", "Produto encontrado com sucesso!");
			res.put("content", productRes);

			return ResponseEntity
					.status(200)
					.body(res);
		} catch (Exception e) {

			res.put("message", "Falha ao processar sua requisição!");
			res.put("content", id);
			return ResponseEntity
					.status(500)
					.body(res);
		}
	}

}