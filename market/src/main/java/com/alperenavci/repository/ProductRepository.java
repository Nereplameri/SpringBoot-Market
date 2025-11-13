package com.alperenavci.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alperenavci.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String name);

}
