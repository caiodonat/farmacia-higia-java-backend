package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmaciahigia.model.Product;
import com.farmaciahigia.repository.ProductRepositoryNew;
import com.farmaciahigia.schemas.customer.CustomerResponseSuccess;
import com.farmaciahigia.schemas.product.ProductCore;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductController {
	
	private final ProductRepositoryNew repository;
	
	ProductController(ProductRepositoryNew repository) {
		this.repository = repository;
	}
	
	Map<String, Object> res = new HashMap<String, Object>();
	
	@Operation(summary = "Create a new Product", tags = { "Product" })
	@PostMapping("/")
	ResponseEntity<?> create(@RequestBody ProductCore req) {
		try {
			Product newProduct = new Product(req);
			
			if (!newProduct.errors().isEmpty()) {
				System.out.println("isValid");
				
				res.put("message", "Dados inválidos:");
				res.put("errors", "");

				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			newProduct = repository.create(newProduct);
			
			res.put("message", "Produto criado com sucesso!");
			res.put("content", newProduct);
			
			return ResponseEntity
			.status(201)
			.body(res);
			
		} catch (Exception e) {
			
			res.put("message", "Falha ao processar sua requisição:");
			res.put("errors", e.getMessage());
			
			return ResponseEntity
			.status(500)
			.body(res);
		}
	}
	
	@Operation(summary = "Get all Products", tags = { "Product" })
	@GetMapping("/all")
	ResponseEntity<?> getAll() {
		try {
			List<Product> repo = repository.selectAll();
			
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
			Product productRes = repository.selectById(Integer.parseInt(id));
			
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
			List<Product> productRes = repository.selectByType(type);
			
			if (productRes == null) {
				res.put("message", "Nenhum Produto encontrado...");
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
			res.put("errors", e.getMessage());
			
			return ResponseEntity
			.status(500)
			.body(res);
		}
	}
	
	@Operation(summary = "Get a Product", tags = { "Product" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json")
			})
	})
	@PutMapping("/{id}/img")
	ResponseEntity<?> updateProductImg(@PathVariable Integer id, @RequestParam String new_url) {
		try {
			Product productRes = repository.selectById(id);
			
			if (productRes == null) {
				res.put("message", "Produto não encontrado");
				res.put("errors", null);

				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			Product newProduct = repository.updateProductImgById(id, new_url);

			res.put("message", "Produto encontrado com sucesso!");
			res.put("content", newProduct);
			
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