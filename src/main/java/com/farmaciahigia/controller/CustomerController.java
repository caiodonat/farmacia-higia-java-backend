package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.farmaciahigia.service.CustomerService;
import com.farmaciahigia.repository.CustomerRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "/customers", produces = "application/json")
@CrossOrigin(origins = "*")
public class CustomerController {

	private final CustomerRepository repo;
	Map<String, Object> infoRes = new HashMap<String, Object>();

	CustomerController(CustomerRepository repository) {
		this.repo = repository;
	}

	// --

	@Operation(summary = "Create a new Customer", tags = { "Customer" })
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/")
	ResponseEntity<?> create(@RequestBody Customer reqCustomer) {
		try {

			CustomerService contract = new CustomerService(reqCustomer);

			if (!contract.isValid()) {

				infoRes.put("message", "Dados inválidos:");
				infoRes.put("content", contract.getErros());

				return ResponseEntity
						.status(400)
						.body(infoRes);
			}

			contract.cryptPassword(); // MOVE to repo.save();

			Customer customerSave = repo.save(new Customer(contract));

			return ResponseEntity
					.status(201)
					// .header("token", "Bearer JWT")
					.body(customerSave);

		} catch (Exception e) {
			System.out.println(e);
			infoRes.put("message", "Não foi possível finalizar requisição:");
			infoRes.put("content", e.getMessage());

			return ResponseEntity
					.status(500)
					.body(infoRes);

			// return ResponseEntity.status(500)
			// .body(e.getMessage());
		}
	}

	// --

	@GetMapping("/all")
	ResponseEntity<?> getAll() {
		try {

			List<Customer> customers = repo.findAll();

			return ResponseEntity
					.status(200)
					.body(Map.of(
							"message", "Clientes encontrado com sucesso!",
							"content", customers));

		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Map.of(
							"message", "Falha ao processar sua requisição",
							"content", null));
		}
	}

	@GetMapping("/{id}") // 54
	ResponseEntity<?> getById(@PathVariable String id) {
		try {
			Customer productRes = repo.findById(Integer.parseInt(id));

			if (productRes == null) {
				infoRes.put("message", "Clientes não encontrado");
				infoRes.put("content", null);
				return ResponseEntity
						.status(400)
						.body(infoRes);
			}

			infoRes.put("message", "Clientes encontrado com sucesso!");
			infoRes.put("content", productRes);

			return ResponseEntity
					.status(200)
					.body(infoRes);
		} catch (Exception e) {

			infoRes.put("message", "Falha ao processar sua requisição!");
			infoRes.put("content", id);
			return ResponseEntity
					.status(500)
					.body(infoRes);
		}
	}

	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody Map<String, Object> req) {
		try {
			Customer customer = new Customer();

			customer.setEmail(String.valueOf(req.get("email")));

			Customer query = repo.findByEmail(customer.getEmail());

			if (query == null) {
				return ResponseEntity
						.status(400)
						.body(Map.of(
								"message", "Cliente não encontrado!",
								"content", customer.getEmail()));
			}

			customer.setPassword(String.valueOf(req.get("password")));

			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			boolean passMatch = bCrypt.matches(customer.getPassword(), query.getPassword());

			if (!passMatch) {
				// if (!customer.getPassword().equals(query.getPassword())) {
				return ResponseEntity
						.status(400)
						.body(Map.of(
								"message", "Senha incorreta!",
								"content", customer.getEmail()));
			}

			return ResponseEntity
					.status(200)
					.body(Map.of(
							"message", "Login realizado com sucesso!",
							"content", passMatch,
							"token", "JWT"));

		} catch (Exception e) {
			return ResponseEntity
					.status(500)
					.body(Map.of(
							"message", "Falha ao processar sua requisição",
							"content", null));
		}
	}
}