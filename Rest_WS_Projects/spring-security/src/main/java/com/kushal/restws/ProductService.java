package com.kushal.restws;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.kushal.restws.model.Product;

@Path(value="/productservie")
public interface ProductService {
	
	@GET
	@Path(value="/products")
	List<Product> getProducts();
	
	@POST
	@Path(value="/products")
	long addProduct(Product product);

}
