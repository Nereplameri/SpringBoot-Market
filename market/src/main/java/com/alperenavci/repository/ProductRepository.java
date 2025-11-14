package com.alperenavci.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alperenavci.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByName(String name);
	
	Optional<Product> findByBarcode(String barcode);
	
	@Query(value = "from Product")
	Page<Product> findAllPageable(Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")
    Page<Product> findAllByBrand(@Param("brandId") Long brandId, Pageable pageable);

}
