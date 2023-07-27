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
import com.farmaciahigia.service.CustomerService;
import com.farmaciahigia.repository.CustomerRepository;
import com.farmaciahigia.schemas.customer.CustomerCore;
import com.farmaciahigia.schemas.customer.CustomerResponseError;
import com.farmaciahigia.schemas.customer.CustomerResponseSuccess;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Tuple;

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

				infoRes.put("message", "Dados inválidos:");
				infoRes.put("content", reqCustomer.errors());

				return ResponseEntity
						.status(400)
						.body(infoRes);
			}

			// uniques [cpf, email] is available ?

			reqCustomer.setPasswordCrypt(reqCustomer.getPassword()); // MOVE to repo.save();

			Customer newCustomer = repo.save(new Customer(reqCustomer));

			return ResponseEntity
					.status(201)
					// .header("token", "Bearer JWT")
					.body(newCustomer);

		} catch (Exception e) {
			infoRes.put("message", "Não foi possível finalizar requisição:");
			infoRes.put("errors", e.getMessage());

			return ResponseEntity
					.status(400)
					.body(infoRes);
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
			infoRes.put("message", "Falha ao processar sua requisição");
			infoRes.put("content", e.getMessage());

			return ResponseEntity
					.status(500)
					.body(infoRes);
		}
	}

	@Operation(summary = "Get a Customer", tags = { "Customer" })
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

	@Operation(summary = "Authorize Customer", tags = { "Customer" })
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Customer.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody Customer reqCustomer) {
		try {

			Customer dbCustomer = repo.findByEmail(reqCustomer.getEmail());
			if (dbCustomer == null) {

				infoRes.put("message", "Cliente não encontrado:");
				infoRes.put("content", reqCustomer.getEmail());
				return ResponseEntity
						.status(400)
						.body(infoRes);
			}

			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			boolean passMatch = bCrypt.matches(
					reqCustomer.getPassword(),
					dbCustomer.getPassword());

			if (!passMatch) {
				infoRes.put("message", "Senha incorreta");
				infoRes.put("content", reqCustomer.getEmail());
				return ResponseEntity
						.status(400)
						.body(infoRes);
			}

			infoRes.put("message", "Login realizado com sucesso!");
			infoRes.put("token", "Bearer JWT");

			return ResponseEntity
					.status(200)
					// .header("token", "Bearer JWT")
					.body(infoRes);

		} catch (Exception e) {
			System.out.println(e);
			infoRes.put("message", "Não foi possível finalizar requisição:");
			infoRes.put("content", e.getMessage());

			return ResponseEntity
					.status(500)
					.body(infoRes);
		}
	}
}