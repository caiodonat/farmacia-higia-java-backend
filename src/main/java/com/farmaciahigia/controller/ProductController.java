package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.farmaciahigia.model.Customer;
import com.farmaciahigia.model.Product;
import com.farmaciahigia.repository.ProductRepository;
import com.farmaciahigia.schemas.product.ProductCore;

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
	Product create(@RequestBody ProductCore req) {

		Product newProduct = new Product(req);

		if (newProduct.errors().isEmpty()) {
			System.out.println("isValid");

			newProduct = repository.save(newProduct);
		} else {
			System.out.println("is not Valid");

		}

		return newProduct;
	}

	@Operation(summary = "Get all Products", tags = { "Product" })
	@GetMapping("/all")
	ResponseEntity<?> getAll() {
		try {
			List<Product> repo = repository.findAll();

			res.put("message", "Requisição processada com sucesso");
			res.put("content", repo);

			return ResponseEntity
					.status(200)
					.body(repo);

		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Map.of(
							"message", "Falha ao processar sua requisição",
							"content", null));
		}
	}

	@Operation(summary = "Get a Product", tags = { "Product" })
	@GetMapping("/{id}")
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


	@Operation(summary = "Get a Product by type", tags = { "Product" })
	@GetMapping("/type/{type}")
	ResponseEntity<?> getByType(@PathVariable String type) {
		try {

			System.out.println(type);
			Product productRes = repository.selectByValue(100.0);

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
			
			return ResponseEntity
					.status(500)
					.body(res);
		}
	}

}