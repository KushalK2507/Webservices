package com.kushal.ws.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.kushal.ws.soap.model.Product;

@WebService
public interface ProductWS {

	@WebMethod
	public List<Product> getProducts();

	@WebMethod
	public Product getProduct(int id);

	@WebMethod
	public Product createProduct(Product product);

	@WebMethod
	public Product updateProduct(Product product);

}
