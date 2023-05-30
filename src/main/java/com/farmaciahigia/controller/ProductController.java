package com.farmaciahigia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.farmaciahigia.model.Product;
import com.farmaciahigia.repository.ProductRepository;

@RestController
@RequestMapping(path = "/products", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductRepository repository;

    ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    Map<Long, Product> res = new HashMap<Long, Product>();

    @GetMapping("/save")
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

    @GetMapping("/all")
    List<Product> getAll() {
        List<Product> productDb = repository.findAll();

        return productDb;
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<String,Object>();
        String message;


        /**
         * body: {
         * message: String,
         * content: Array || Object
         * }
         */

         // Express
        // res.status(200).send({
        //     message,
        //     productRes
        // }).end();

        Product productRes = repository.findById(id);

        if (productRes == null) {
            result.put("message", "Produto não encontrado");
            result.put("content", new Object());
        }

        result.put("message", "Produto encontrado com sucesso!");
        result.put("content", productRes);

        // Product product = new Product();

        // product.setId(productRes.getId());
        // product.setDescription(productRes.getDescription());

        // res.put(product.getId(), product);
        // // res.put(0, message);

        return ResponseEntity
        .status(200)
        .body(result);
    }

    @GetMapping("/1")
    public ResponseEntity<Object> getRes() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}