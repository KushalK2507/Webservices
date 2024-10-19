package com.kushal.restws.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kushal.restws.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
