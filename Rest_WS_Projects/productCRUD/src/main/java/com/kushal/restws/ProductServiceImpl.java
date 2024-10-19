package com.kushal.restws;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kushal.restws.entities.Product;
import com.kushal.restws.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;
	
	@Override
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		
		return repository.findById(id).get();
		// In above findby method returns us the 
		//Optional Object of Product to get the Product we use get() method
	}

	@Override
	public Response createProduct(Product product) {
		Product savedProduct = repository.save(product);
		
		return Response.ok(savedProduct).build();
	}

	@Override
	public Response updateProduct(Product product) {
		 Product updatedProduct = repository.save(product);// In this Spring automatic indetifies if product exist it will update it.
		return Response.ok(updatedProduct).build();
	}

	@Override
	public Response deleteProduct(Product product) {
		repository.delete(product);
		Product deletedProduct = repository.findById(product.getId()).get();
		if(null ==deletedProduct)
		{
			return Response.ok().build(); 
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

}
