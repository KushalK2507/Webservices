package com.kushal.restws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.PathVariable;

import com.kushal.restws.entities.Product;

@Consumes("application/json")
@Produces("application/json")
@Path("/productService")
public interface ProductService {

	@GET
	@Path("/products")
	List<Product> getProducts();
	
	@Path("/products/{id}")
	@GET
	Product getProduct(@PathParam (value = "id")int id);
	
	@Path("/products")
	@POST
	Response createProduct(Product product);
	
	@Path("/products")
	@PUT
	Response updateProduct(Product product);
	
	@Path("/products")
	@DELETE
	Response deleteProduct(Product product);
	
}
