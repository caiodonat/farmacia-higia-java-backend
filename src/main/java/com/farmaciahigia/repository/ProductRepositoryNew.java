package com.farmaciahigia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farmaciahigia.dao.ProductDao;
import com.farmaciahigia.model.Product;

@Service
public class ProductRepositoryNew {

	private final ProductDao repository;

	public ProductRepositoryNew(ProductDao repository) {
		this.repository = repository;
	}

	public Product selectById(Integer id) {
		Product p = repository.findById(id);
		System.out.println(p.toString());
		return p;
	}

	public List<Product> selectAll() {
		return repository.findAll();
	}

	public List<Product> selectByType(String type) {
		return repository.findAll()
				.stream()
				.filter(p -> (p.getType() != null &&
						p.getType().contains(type)))
				.toList();
	}

	public Product create(Product product) {
		Product p = repository.save(product);
		p = repository.findById(p.getId()).get();

		return p;
	}

	public Product updateProductImgById(Integer id, String newUrl) {
		Product p = repository.findById(id);
		p.setImgUrl(newUrl);
		p = repository.save(p);

		return p;
	}

	public Product updateProduct(Long id, Product newP) {
		Product oldP = repository.findById(id).get();

		Product p = oldP;

		p.setType(newP.getType());
		p.setDescription(newP.getDescription());
		p.setEan(newP.getEan());
		p.setValue(newP.getValue());
		p.setSaleFee(newP.getSaleFee());
		p.setImgUrl(newP.getImgUrl());

		p = repository.save(p);

		return p;
	}

}