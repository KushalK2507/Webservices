package com.kushal.ws.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kushal.ws.soap.model.Product;
import com.kushal.ws.soap.repos.ProductRepository;

@Service
public class ProductWSImpl implements ProductWS {

	@Autowired
	ProductRepository repository;

	@Override
	public List<Product> getProducts() {

		return repository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		return repository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product) {
		return repository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return repository.save(product);
	}

}
