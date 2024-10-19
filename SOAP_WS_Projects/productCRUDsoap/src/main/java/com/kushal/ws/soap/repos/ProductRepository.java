package com.kushal.ws.soap.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.ws.soap.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
