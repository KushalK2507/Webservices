package com.kushal.restws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kushal.restws.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private List<Product> products=new ArrayList<>();
	long id = 123;

	public ProductServiceImpl() {
		init();
	}

	void init() {
			Product product = new Product();
			product.setId(++id);
			product.setDescription("Learning Web Services");
			products.add(product);
			
	}

	@Override
	public List<Product> getProducts() {

		return products;
	}

	@Override
	public long addProduct(Product product) {
		product.setId(++id);
		products.add(product);
		return product.getId();
	}

}
