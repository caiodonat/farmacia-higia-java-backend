package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmaciahigia.model.Customer;
import com.farmaciahigia.repository.CustomerRepository;
import com.farmaciahigia.schemas.customer.CustomerCore;
import com.farmaciahigia.schemas.customer.CustomerLogin;
import com.farmaciahigia.schemas.customer.CustomerResponseError;
import com.farmaciahigia.schemas.customer.CustomerResponseSuccess;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping(path = "/customers", produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	private final CustomerRepository repo;
	Map<String, Object> res = new HashMap<String, Object>();
	
	CustomerController(CustomerRepository repository) {
		this.repo = repository;
	}
	
	// --
	
	@Operation(summary = "Create a new Customer", tags = { "Customer" })
	// @RequestBody
	@ApiResponses({
		@ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = CustomerResponseSuccess.class), mediaType = "application/json")
		}),
		@ApiResponse(responseCode = "400", content = {
			@Content(schema = @Schema(implementation = CustomerResponseError.class), mediaType = "application/json")
		})
	})
	@PostMapping("/")
	ResponseEntity<?> create(@RequestBody CustomerCore req) {
		try {
			Customer reqCustomer = new Customer(req);
			
			if (reqCustomer.errors().size() != 0) {
				
				res.put("message", "Dados inválidos:");
				res.put("content", reqCustomer.errors());
				
				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			// uniques [cpf, email] is available ?
			
			System.out.println("1: " + req.toString());
			System.out.println("2: " + reqCustomer.toString());
			reqCustomer.setPasswordCrypt(); // MOVE to repo.save();
			System.out.println("3: " + reqCustomer.toString());
			
			// Customer newCustomer = new Customer( repo.save(reqCustomer));
			Customer newCustomer = repo.save(reqCustomer);

			System.out.println(newCustomer );//+ json3);
			
			res.put("message", "Usuário cadastrado com sucesso");
			// res.put("content", json);
			res.put("content", newCustomer);
			
			
			repo.delete(newCustomer);
			
			return ResponseEntity
			.status(201)
			// .header("token", "Bearer JWT")
			.body(res);
			
		} catch (Exception e) {
			res.put("message", "Não foi possível finalizar requisição:");
			res.put("errors", e.getMessage());
			
			return ResponseEntity
			.status(500)
			.body(res);
		}
	}
	
	@Operation(summary = "Get all Customer", tags = { "Customer" })
	@GetMapping("/all")
	ResponseEntity<?> getAll() {
		try {
			
			List<Customer> customers = repo.findAll();
			
			return ResponseEntity
			.status(200)
			// .header("token", "Bearer JWT")
			.body(customers);
			
		} catch (Exception e) {
			System.out.println(e);
			res.put("message", "Falha ao processar sua requisição");
			res.put("content", e.getMessage());
			
			return ResponseEntity
			.status(500)
			.body(res);
		}
	}
	
	@Operation(summary = "Get a Customer", tags = { "Customer" })
	@GetMapping("/{id}") // 54
	ResponseEntity<?> getById(@PathVariable String id) {
		try {
			Customer productRes = repo.findById(Integer.parseInt(id));
			
			if (productRes == null) {
				res.put("message", "Clientes não encontrado");
				res.put("content", null);
				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			res.put("message", "Clientes encontrado com sucesso!");
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
	
	@Operation(summary = "Authorize Customer", tags = { "Customer" })
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json")
		}),
		@ApiResponse(responseCode = "500", content = {
			@Content(schema = @Schema())
		})
	})
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody CustomerLogin reqCustomer) {
		try {
			
			Customer dbCustomer = repo.findByEmail(reqCustomer.getEmail());
			if (dbCustomer == null) {
				
				res.put("message", "Cliente não encontrado:");
				res.put("content", reqCustomer.getEmail());
				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			boolean passMatch = bCrypt.matches(
			reqCustomer.getPassword(),
			dbCustomer.getPassword()
			);
			
			System.out.println(
			reqCustomer.getPassword() + '\n' +
			dbCustomer.getPassword()
			);
			
			if (!passMatch) {
				res.put("message", "Senha incorreta");
				res.put("content", reqCustomer.getEmail());
				return ResponseEntity
				.status(400)
				.body(res);
			}
			
			res.put("message", "Login realizado com sucesso!");
			res.put("token", "Bearer JWT");
			
			return ResponseEntity
			.status(200)
			// .header("token", "Bearer JWT")
			.body(res);
			
		} catch (Exception e) {
			System.out.println(e);
			res.put("message", "Não foi possível finalizar requisição:");
			res.put("content", e.getMessage());
			
			return ResponseEntity
			.status(500)
			.body(res);
		}
	}
}